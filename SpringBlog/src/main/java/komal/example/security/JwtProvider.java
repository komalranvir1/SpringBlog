package komal.example.security;



import java.security.Key;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import komal.example.entity.User;

@Service
public class JwtProvider {
	
	private Key key;	
	
	 @PostConstruct
	    public void init() {
		 key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	 }

	
	public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(principal.getUserName())
				.signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
				.compact();
	}
	



}
