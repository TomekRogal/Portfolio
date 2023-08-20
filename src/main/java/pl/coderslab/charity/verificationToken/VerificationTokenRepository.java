package pl.coderslab.charity.verificationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.User;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}

