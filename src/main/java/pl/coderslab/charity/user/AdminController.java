package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.charity.role.RoleRepository;


import javax.validation.Valid;

@SessionAttributes("loggedUser")
@Controller
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;




    public AdminController(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal CurrentUser customUser, Model model){
        if (customUser != null) {
            model.addAttribute("loggedUser", userRepository.findById(customUser.getUser().getId()).get());
        }
        return "admin";
    }
    @GetMapping("/admin/all")
    public String adminall(Model model){
        model.addAttribute("admins",userRepository.findAllUsersWithRole(roleRepository.findByName("ROLE_ADMIN")));
        return "admins/all";
    }
    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        if (userRepository.findById(id).isPresent()) {
            model.addAttribute("admin", userRepository.findById(id).get());
            return "admins/edit";
        }
        return "redirect:/admin/all";
    }
    @PostMapping("/admin/edit/{id}")
    public String editProcess(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admins/edit";
        }
        userRepository.save(user);
        return "redirect:/admin/all";
    }
    @RequestMapping("/admin/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            userService.removeAdmin(user);
        }
        return "redirect:/admin/all";

    }
    @GetMapping("/admin/user/all")
    public String userall(Model model){
        model.addAttribute("users",userRepository.findAllUsersWithRole(roleRepository.findByName("ROLE_USER")));
        return "users/all";
    }
    @GetMapping("/admin/user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        if (userRepository.findById(id).isPresent()) {
            model.addAttribute("user", userRepository.findById(id).get());
            return "users/edit";
        }
        return "redirect:/admin/user/all";
    }
    @PostMapping("/admin/user/edit/{id}")
    public String editUserProcess(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userRepository.save(user);
        return "redirect:/admin/user/all";
    }
    @RequestMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        try {
            userRepository.deleteById(id);
            return "redirect:/admin/user/all";
        } catch (Exception e) {
            model.addAttribute("delete", "failed");
        }
        return "forward:/admin/user/all";
    }
    @RequestMapping("/admin/user/disable/{id}")
    public String disableUser(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            user.setNonLocked(false);
            userRepository.save(user);
        }
        return "redirect:/admin/user/all";
    }
    @RequestMapping("/admin/user/enable/{id}")
    public String enableUser(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            user.setNonLocked(true);
            userRepository.save(user);
        }
        return "redirect:/admin/user/all";
    }
    @RequestMapping("/admin/add/{id}")
    public String addAdmin(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            userService.addAdmin(user);
        }
        return "redirect:/admin/user/all";
    }
}
