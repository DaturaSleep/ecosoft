package com.vmaksym.ecosoft.security;

import com.vmaksym.ecosoft.repos.ApplicationUserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    final ApplicationUserRepo applicationUserRepo;

    public ApplicationUserDetailsService(ApplicationUserRepo applicationUserRepo) {
        this.applicationUserRepo = applicationUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var applicationUser = applicationUserRepo.findApplicationUserByUserName(userName);
        return new ApplicationUserDetails(applicationUser);
    }
}
