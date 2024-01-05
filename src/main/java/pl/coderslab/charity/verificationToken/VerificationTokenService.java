package pl.coderslab.charity.verificationToken;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.User;

import java.util.UUID;

@Service
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public String generateTokenForUser (User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken userToken = new VerificationToken();
        userToken.setToken(token);
        userToken.setUser(user);
        verificationTokenRepository.save(userToken);
        return token;
    }
}
