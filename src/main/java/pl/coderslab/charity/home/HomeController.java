package pl.coderslab.charity.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationRepository;
import pl.coderslab.charity.institution.InstitutionRepository;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;


    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;

    }


    @RequestMapping("/")
    public String homeAction( Model model){
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("donationsNumber",donationRepository.selectDonations());
        model.addAttribute("boxesNumber",donationRepository.selectBoxes());

        return "index";
    }
}
