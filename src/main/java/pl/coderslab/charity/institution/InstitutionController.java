package pl.coderslab.charity.institution;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class InstitutionController {
    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }
    @RequestMapping("/admin/institution/all")
    public String findAll(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "institutions/all";
    }
    @GetMapping("/admin/institution/add")
    public String add(Model model) {
        model.addAttribute("institution", new Institution());
        return "institutions/add";
    }

    @PostMapping("/admin/institution/add")
    public String addProcess(@Valid Institution institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "institutions/add";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution/all";
    }
    @GetMapping("/admin/institution/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        if (institutionRepository.findById(id).isPresent()) {
            model.addAttribute("institution", institutionRepository.findById(id).get());
            return "institutions/edit";
        }
        return "redirect:/admin/institution/all";
    }

    @PostMapping("/admin/institution/edit/{id}")
    public String editProcess(@Valid Institution institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "institutions/edit";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution/all";
    }
    @RequestMapping("/admin/institution/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            institutionRepository.deleteById(id);
            return "redirect:/admin/institution/all";
        } catch (Exception e) {
            model.addAttribute("delete", "failed");
        }
        return "forward:/admin/institution/all";
    }


}
