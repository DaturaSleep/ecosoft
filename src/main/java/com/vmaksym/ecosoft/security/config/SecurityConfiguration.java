package com.vmaksym.ecosoft.security.config;

import com.vmaksym.ecosoft.entities.UserRole;
import com.vmaksym.ecosoft.security.ApplicationUserDetailsService;
import com.vmaksym.ecosoft.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationUserDetailsService applicationUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailsService);
    }

    public SecurityConfiguration(ApplicationUserDetailsService applicationUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()

                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .antMatchers("/pupil").hasAnyAuthority(UserRole.PUPIL.roleName, UserRole.ADMIN.roleName)
                .antMatchers("/pupil/**").hasAuthority(UserRole.ADMIN.roleName)
                .antMatchers("/teacher").hasAnyAuthority(UserRole.TEACHER.roleName, UserRole.ADMIN.roleName)
                .antMatchers("/teacher/**").hasAuthority(UserRole.ADMIN.roleName)
                .antMatchers("/*").hasAnyAuthority(UserRole.ADMIN.roleName, UserRole.PUPIL.roleName, UserRole.TEACHER.roleName)
                .anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}