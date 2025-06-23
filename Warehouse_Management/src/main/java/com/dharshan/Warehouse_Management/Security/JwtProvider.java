package com.dharshan.Warehouse_Management.Security;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	
	private static final Logger logger=LoggerFactory.getLogger(JwtProvider.class);
	
//	@Value("${app.SECRET_KEY}") application.properties 
	// this is from Goggle 256 bit secret key generator
	
	//private  String SECRET_KEY_STRING ;
	private final String SECRET_KEY_STRING="PNv44TBqzbWRxCfFHI2LK6DEfaeMwyrz";
	
	private  SecretKey SECRET_KEY=Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	
//	@Value("${app.jwtExpirationMs}")
	private  int jwtExpirationMs=3600000;
	
//	//Constructor
//	public JwtProvider(SecretKey SECRET_KEY,int jwtExpirationMs) {
//		this.SECRET_KEY=SECRET_KEY;
//		this.jwtExpirationMs=jwtExpirationMs;
//	}
	//Generating Token 
	public String generateToken(Authentication authentication) {
		UserDetailsImpl userprincipal=(UserDetailsImpl)authentication.getPrincipal();
		
		return Jwts.builder()
				.subject(userprincipal.getUsername())
				.claim("roles",userprincipal.getAuthorities().stream()
							.map(GrantedAuthority::getAuthority)
							.collect(Collectors.toList()))
				.claim("user_id", userprincipal.getId().intValue())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+ jwtExpirationMs))
				.signWith(SECRET_KEY,Jwts.SIG.HS256)
				.compact();
	}
	
	//Validation Token
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser()
			.verifyWith(SECRET_KEY)
			.build()
			.parseSignedClaims(authToken);
			
			return true;
			
		}
		catch(JwtException | IllegalArgumentException e) {
			logger.error("JWT Validation Error:", e.getMessage());
		}
		return false;
	}
	//Get Username from token
	public String getUsernameFromToken(String token) {
		
		return Jwts.parser()
				.verifyWith(SECRET_KEY)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	
	

}
