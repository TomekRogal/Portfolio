package pl.coderslab.charity.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@SessionAttributes("loggedUser")
@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String addProcess(@RequestParam String password2, @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if(userService.findByUserName(user.getUsername()) != null || userService.findByEmail(user.getEmail()) != null)
        {
            model.addAttribute("register","failed");
            user.setUsername("");
            user.setPassword("");
            return "register";
        }
        if(!user.getPassword().equals(password2)){
            model.addAttribute("pass","failed");
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal CurrentUser customUser, Model model){
        if (customUser != null) {
            model.addAttribute("loggedUser", userRepository.findById(customUser.getUser().getId()).get());
        }
        return "admin";
    }

}
