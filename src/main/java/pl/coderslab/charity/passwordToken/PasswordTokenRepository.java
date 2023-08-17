package pl.coderslab.charity.passwordToken;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.user.User;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {

    PasswordToken findByToken(String token);

    PasswordToken findByUser(User user);
}
