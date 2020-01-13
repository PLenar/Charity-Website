package pl.coderslab.charity.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entities.Donation;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.Collection;
import java.util.stream.Collectors;


@Controller
public class DonationController {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository){
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
    }


//    @ModelAttribute("categories")
//    public Collection<String> getCategories() {
//        return categoryRepository.findAll().stream()
//                .map(category -> category.getName())
//                .collect(Collectors.toList());
//    }

    @ModelAttribute("donation")
    public Donation getDonation(){
        return new Donation();
    }

    @RequestMapping("/donation")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "donationForm";
    }
}
