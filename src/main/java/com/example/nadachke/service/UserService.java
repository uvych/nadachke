package com.example.nadachke.service;

<<<<<<< HEAD
=======

>>>>>>> 6d368e73be017864a95af81c6d16a857f3a3258d
import com.example.nadachke.domain.Role;
import com.example.nadachke.domain.User;
import com.example.nadachke.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;
    private MailSender mailSender;

    @Autowired
    public UserService(UserRepo userRepo,MailSender mailSender) {
        this.mailSender = mailSender;
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if(!user.getEmail().isEmpty()){
                String message = String.format(
                        "Hello, %s! \n" +
                                "Welcome to Nadachke. Please , visit next link: http://localhost:8080/activate/%s",
                        user.getUsername(),
                        user.getActivationCode());
                mailSender.send(user.getEmail(), "Activation code" , message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }
<<<<<<< HEAD

=======
        
>>>>>>> 6d368e73be017864a95af81c6d16a857f3a3258d
        user.setActive(true);
        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }
}
