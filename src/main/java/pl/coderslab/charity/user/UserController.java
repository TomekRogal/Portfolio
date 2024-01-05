package pl.coderslab.charity.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.charity.email.MailService;

import pl.coderslab.charity.passwordToken.PasswordToken;
import pl.coderslab.charity.passwordToken.PasswordTokenRepository;
import pl.coderslab.charity.passwordToken.PasswordTokenService;
import pl.coderslab.charity.verificationToken.VerificationToken;
import pl.coderslab.charity.verificationToken.VerificationTokenRepository;
import pl.coderslab.charity.verificationToken.VerificationTokenService;

import javax.mail.MessagingException;
import javax.validation.Valid;



@SessionAttributes("loggedUser")
@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final VerificationTokenService verificationTokenService;
    private final PasswordTokenService passwordTokenService;
    private final TemplateEngine templateEngine;
    public UserController(UserService userService, UserRepository userRepository, MailService mailService, VerificationTokenRepository verificationTokenRepository, PasswordTokenRepository passwordTokenRepository, VerificationTokenService verificationTokenService, PasswordTokenService passwordTokenService, TemplateEngine templateEngine) {
        this.userService = userService;
        this.userRepository = userRepository;

        this.mailService = mailService;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.verificationTokenService = verificationTokenService;
        this.passwordTokenService = passwordTokenService;
        this.templateEngine = templateEngine;
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
        userService.saveUser(user);
        String token = verificationTokenService.generateTokenForUser(user);
        String url = "http://localhost:8080/registrationConfirm?token=" + token;
        Context context = new Context();
        context.setVariable("url", url);
        String text = templateEngine.process("tokenTemplate", context);
        try {
            mailService.sendMail(user.getEmail(),"Potwierdzenie rejestracji",text,true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/login";
    }
    @GetMapping("/registrationConfirm")
    public String registerConfirm(@RequestParam String token) {
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
        String token = passwordTokenService.generateTokenForUser(user);
        String url = "http://localhost:8080/passwordReset?token=" + token;
        Context context = new Context();
        context.setVariable("url", url);
        String text = templateEngine.process("passwordTemplate", context);
        try {
            mailService.sendMail(user.getEmail(),"Reset hasła",text,true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "/password/confirm";
    }
    @GetMapping("/passwordReset")
    public String resetPassword(@RequestParam String token, Model model){
        PasswordToken passwordToken = passwordTokenRepository.findByToken(token);
        if(passwordToken==null){
            model.addAttribute("token","failed");
            return "redirect:/login";
        }
        User user = passwordToken.getUser();
        passwordTokenRepository.delete(passwordTokenRepository.findByToken(token));
        user.setPassword(null);
        model.addAttribute("user",user);
        return "/password/reset";
    }
    @PostMapping ("/passwordReset")
    public String resetPasswordConfirm( @RequestParam String password2, @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "/password/reset";
        }
        if(!user.getPassword().equals(password2)){
            model.addAttribute("pass","failed");
            return "/password/reset";
        }
        userService.changePassword(user);
        return "/password/resetConfirm";
    }


}
