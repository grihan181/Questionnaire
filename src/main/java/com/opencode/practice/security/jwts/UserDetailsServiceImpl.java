package com.opencode.practice.security.jwts;

import com.opencode.practice.model.User;
import com.opencode.practice.repos.UserRepositorySecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositorySecurity userRepositorySecurity;

    @Autowired
    public UserDetailsServiceImpl(UserRepositorySecurity userRepositorySecurity) {
        this.userRepositorySecurity = userRepositorySecurity;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositorySecurity.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}
