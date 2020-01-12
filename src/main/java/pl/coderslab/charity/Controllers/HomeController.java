package pl.coderslab.charity.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.entities.Institution;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private InstitutionRepository institutionRepository;
    
    @Autowired
    private DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository){
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> institutionsList = institutionRepository.findAll();
        model.addAttribute("institutionsList", institutionsList);
        Long numberOfInstitutions = institutionRepository.count();
        model.addAttribute("numberOfInstitutions", numberOfInstitutions);
        List<Donation> donationList = donationRepository.findAll();
        int numberOfBags = 0;
        for (Donation d:donationList){
            numberOfBags += d.getQuantity();
        }
        model.addAttribute("numberOfBags", numberOfBags);
        return "index";
    }

}
