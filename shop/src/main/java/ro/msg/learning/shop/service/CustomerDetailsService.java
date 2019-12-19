package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    public final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer user = customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user"));

        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities());
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(){

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DOCTOR"));

        return grantedAuthorities;
    }

    @Transactional
    public Customer loadCurrentUser(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerRepository.findCustomerByUsername(username).orElseThrow(CustomerNotFoundException::new);
    }
}
