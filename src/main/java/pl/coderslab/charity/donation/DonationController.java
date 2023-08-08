package pl.coderslab.charity.donation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.CategoryRepository;
import pl.coderslab.charity.institution.InstitutionRepository;

import javax.validation.Valid;

@Controller
public class DonationController {
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/donation")
    public String add(Model model) {
        Donation donation = new Donation();
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("donation",donation);
        return "form";
    }
    @PostMapping("/donation")
    public String addProcess(@Valid Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        donationRepository.save(donation);
        return "redirect:/";
    }

}
