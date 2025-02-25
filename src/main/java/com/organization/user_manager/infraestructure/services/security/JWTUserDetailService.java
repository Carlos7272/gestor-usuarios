package com.organization.user_manager.infraestructure.services.security;

import com.organization.user_manager.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JWTUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.userRepository.findByFullName(userName)
                .map(userEntity -> {
                    final var authorities = userEntity.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .toList();
                    return new User(userEntity.getFullName(), userEntity.getPassword(), authorities);
                }).orElseThrow(() -> new UsernameNotFoundException("User not exist"));
    }
}

