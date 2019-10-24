package edu.eci.arem.securityclient.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.eci.arem.securityclient.Entities.Authority;
import edu.eci.arem.securityclient.Repositories.UserRepository;

/**
 * @author Santiago Rocha - ECI
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
        edu.eci.arem.securityclient.Entities.User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        //Mapear nuestra lista de Authority con la de spring security 
        for (Authority authority : appUser.getAuthority()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantList.add(grantedAuthority);
        }

        //Mapear nuestra lista de Authority con la de spring security 
        UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
        return user;
    }
}