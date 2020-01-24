package pl.coderslab.charity.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import javax.validation.Valid;



@Controller
public class DonationController {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DonationRepository donationRepository;

    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository){
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping(path = "/donation", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("donation", new Donation());
        return "donationForm";
    }

    @RequestMapping(path = "/donation", method = RequestMethod.POST)
    public String addDonation(@ModelAttribute @Valid Donation donation, Model model, BindingResult result){

        donationRepository.save(donation);

        return "index";
    }
}
