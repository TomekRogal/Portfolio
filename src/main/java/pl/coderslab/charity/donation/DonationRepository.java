package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.user.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
@Query("SELECT SUM(d.quantity) FROM Donation d")
Integer selectBoxes();
@Query("SELECT COUNT(*) FROM Donation")
Integer selectDonations();
    @Query("SELECT DISTINCT d FROM Donation d JOIN FETCH d.categories WHERE d.user =?1")
    List<Donation> findByUser(User user);
}
