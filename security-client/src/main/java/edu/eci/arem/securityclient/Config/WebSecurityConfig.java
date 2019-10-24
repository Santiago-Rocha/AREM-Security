package edu.eci.arem.securityclient.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.eci.arem.securityclient.Services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    //Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  //recursos permitidios 
            .antMatchers("/","/index").permitAll()
            .antMatchers("/calc").access("hasRole('ADMIN')") //recurso solo accesible por un usuario ADMIN
                .anyRequest().authenticated()
                .and()
            .formLogin() //configuracion de login
                .loginPage("/login") 
                .permitAll()
                .defaultSuccessUrl("/calc")
                .failureUrl("/index?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();
    }


    BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * Este metodo crea el encriptor de contraseñas que usa BCrypt como funcion de hash
     * @return un ecriptor de contraseñas 
     */	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
}