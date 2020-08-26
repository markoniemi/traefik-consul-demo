package example.user.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import example.repository.user.User;
import example.user.service.UserClient;
import lombok.extern.log4j.Log4j2;

/**
 * Spring security AuthenticationProvider which authenticates using
 * UserRepository.
 */
@Log4j2
@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {
    @Resource
    UserClient userService;

    /**
     * Authenticate using UserRepository.
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        log.debug("authenticate {}", authentication.getName());
        User user = userService.findByUsername(authentication.getName());
        return authenticateUser(user, authentication);
    }

    private Authentication authenticateUser(User user, Authentication authentication) {
        if (user != null && authentication.getCredentials().equals(user.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
                    authorities);
        } else {
            return null;
            // throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
