package pl.coderslab.charity.donation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.email.MailService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;
import pl.coderslab.charity.user.CurrentUser;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserRepository;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@SessionAttributes("loggedUser")
@Controller
public class DonationController {
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final MailService mailService;
    private final UserRepository userRepository;

    private final TemplateEngine templateEngine;


    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, MailService mailService, DonationService donationService, UserRepository userRepository1, TemplateEngine templateEngine) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.mailService = mailService;
        this.userRepository = userRepository1;
        this.templateEngine = templateEngine;
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
        User user = userRepository.findById(customUser.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("loggedUser", user);
        Donation donation = new Donation();
        donation.setUser(user);
        model.addAttribute("donation", donation);
        return "form";
    }

    @PostMapping("/donation")
    public String addProcess(@AuthenticationPrincipal CurrentUser customUser, @Valid Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        donationRepository.save(donation);
        Context context = new Context();
        context.setVariable("donation", donation);
        String text = templateEngine.process("emailTemplate", context);
        try {
            mailService.sendMail(customUser.getUser().getEmail(), "Podsumowanie darowizny", text, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "confirmation";
    }

    @GetMapping("/donation/all")
    public String all(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = userRepository.findById(customUser.getUser().getId()).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("loggedUser", user);
        model.addAttribute("donations", donationRepository.findByUser(user));
        return "donation/all";
    }

}
