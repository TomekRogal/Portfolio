package pl.coderslab.charity.user;

public interface UserService {

    User findByUserName(String name);
    User findByEmail(String email);

    void saveUser(User user);
}