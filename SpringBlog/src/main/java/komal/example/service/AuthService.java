package komal.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import komal.example.entity.LoginRequest;
import komal.example.entity.RegisterRequest;
import komal.example.entity.User;
import komal.example.repository.UserRepo;
import komal.example.security.JwtProvider;

@Service
public class AuthService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder  passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;
	
	public void signUp(@RequestBody RegisterRequest registerRequest) {
     User user = new User();
     user.setUserName(registerRequest.getUserName());
     user.setPassword(encodePassword(registerRequest.getPassword()));
     user.setEmail(registerRequest.getEmail());
     userRepo.save(user);
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	
	public String login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getUserName(),
				loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return jwtProvider.generateToken(authenticate);
	}
}
