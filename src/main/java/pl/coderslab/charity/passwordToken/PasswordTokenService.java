package pl.coderslab.charity.passwordToken;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.verificationToken.VerificationToken;

import java.util.UUID;

@Service
public class PasswordTokenService{
    private final PasswordTokenRepository passwordTokenRepository;

    public PasswordTokenService(PasswordTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }

    public String generateTokenForUser (User user) {
        String token = UUID.randomUUID().toString();
        PasswordToken passToken = new PasswordToken();
        passToken.setToken(token);
        passToken.setUser(user);
        passwordTokenRepository.save(passToken);
        return token;
    }
}
