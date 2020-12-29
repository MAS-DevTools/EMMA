/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.configurations;

import mas.emma.data.models.CustomSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author nlmsc
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomSetting CustomSetting;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("revrestahc").password("{noop}Novi123").roles("ADMIN");

    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
//                .and().formLogin().loginPage("/")
//                .failureUrl("/").loginProcessingUrl("/")
//                .permitAll().and().logout()
//                .logoutSuccessUrl("/");
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
