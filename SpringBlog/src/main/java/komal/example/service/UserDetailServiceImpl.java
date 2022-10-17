package komal.example.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import komal.example.repository.UserRepo;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      komal.example.entity.User user = userRepo.findByUserName(userName).orElseThrow(()-> 
       new UsernameNotFoundException("no user found" +userName));
		return new User(user.getUserName(),user.getPassword(), true, true, true, true, getAuhorities("ROLE_USER"));
	}


	private Collection<? extends GrantedAuthority> getAuhorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
	}

}
