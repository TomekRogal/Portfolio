package pl.coderslab.charity.donation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.email.MailService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.UserRepository;


import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
@SessionAttributes("loggedUser")
@Controller
public class DonationController {
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final DonationService donationService;




    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, MailService mailService, DonationService donationService) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.donationService = donationService;
    }
    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }
    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionRepository.findAll();
    }

    @GetMapping("/donation")
    public String add(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (customUser != null) {
            model.addAttribute("loggedUser", userRepository.findById(customUser.getUser().getId()).get());
        }
        Donation donation = new Donation();
        donation.setUser(customUser.getUser());
        model.addAttribute("donation",donation);
        return "form";
    }
    @PostMapping("/donation")
    public String addProcess(@AuthenticationPrincipal CurrentUser customUser, @Valid Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        donationRepository.save(donation);
        try {
            mailService.sendMail(customUser.getUser().getEmail(),"Podsumowanie darowizny",donationService.donationInfo(donation),true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "confirmation";
    }

}
