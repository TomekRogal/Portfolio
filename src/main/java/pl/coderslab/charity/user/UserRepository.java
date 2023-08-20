package pl.coderslab.charity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.role.Role;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles WHERE ?1 MEMBER OF u.roles")
    List<User> findAllUsersWithRole(Role role);
}