package pl.coderslab.charity.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.email.MailService;

import pl.coderslab.charity.passwordToken.PasswordToken;
import pl.coderslab.charity.passwordToken.PasswordTokenRepository;
import pl.coderslab.charity.verificationToken.VerificationToken;
import pl.coderslab.charity.verificationToken.VerificationTokenRepository;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.UUID;


@SessionAttributes("loggedUser")
@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordTokenRepository passwordTokenRepository;

    public UserController(UserService userService, UserRepository userRepository, MailService mailService, VerificationTokenRepository verificationTokenRepository, PasswordTokenRepository passwordTokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;

        this.mailService = mailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordTokenRepository = passwordTokenRepository;
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
    public String addProcess  (@RequestParam String password2, @Valid User user, BindingResult bindingResult, Model model) {
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
        String token = UUID.randomUUID().toString();
        userService.saveUser(user);
        VerificationToken userToken = new VerificationToken();
        userToken.setToken(token);
        userToken.setUser(user);
        verificationTokenRepository.save(userToken);
        try {
            mailService.sendMail(user.getEmail(),"Potwierdzenie rejestracji","Link do potwierdzenia rejestracji: " +
                    "<br> http://localhost:8080/registrationConfirm?token=" + token,true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/login";
    }
    @GetMapping("/registrationConfirm")
    public String registerConfirm(Model model,@RequestParam String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken != null){
            User user = verificationToken.getUser();
            user.setEnabled(1);
            userRepository.save(user);
            verificationTokenRepository.delete(verificationToken);
        }
        return "redirect:/login";
    }
    @GetMapping("/rememberPassword")
    public String rememberPassword(){
        return "/password/remember";
    }
    @PostMapping("/rememberPassword")
    public String rememberPasswordConfirm(@RequestParam String email, Model model){
        User user = userRepository.findByEmail(email);
        if(user==null){
            model.addAttribute("email","failed");
            return "/password/remember";
        }
        String token = UUID.randomUUID().toString();
        PasswordToken passToken = new PasswordToken();
        passToken.setToken(token);
        passToken.setUser(user);
        passwordTokenRepository.save(passToken);
        try {
            mailService.sendMail(user.getEmail(),"Reset hasła","Link do zmiany hasła: " +
                    "<br> http://localhost:8080/passwordReset?token=" + token,true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "/password/confirm";
    }



}
