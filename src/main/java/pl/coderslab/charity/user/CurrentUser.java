package pl.coderslab.charity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final pl.coderslab.charity.user.User user;

    public CurrentUser(String email, String password, boolean enabled, boolean nonLocked,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.charity.user.User user) {
        super(email,password,enabled,true,true,nonLocked,authorities);
//        super(email, password, authorities);
        this.user = user;
    }

    public pl.coderslab.charity.user.User getUser() {
        return user;
    }
}
