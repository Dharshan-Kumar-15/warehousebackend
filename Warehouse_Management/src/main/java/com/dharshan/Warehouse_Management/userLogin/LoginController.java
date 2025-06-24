package com.dharshan.Warehouse_Management.userLogin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dharshan.Warehouse_Management.LoginPage.JwtResponse;
import com.dharshan.Warehouse_Management.LoginPage.LoginRequest;
import com.dharshan.Warehouse_Management.LoginPage.MessageResponse;
import com.dharshan.Warehouse_Management.LoginPage.SignUpRequest;
import com.dharshan.Warehouse_Management.Security.JwtProvider;
import com.dharshan.Warehouse_Management.Security.UserDetailsImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
		
		@Autowired
		AuthenticationManager authenticationManager;
		
		@Autowired
		UserService userservice;
		
		@Autowired
		JwtProvider jwtProvider;
		//login
		@PostMapping("/signin")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
			Authentication authentication= authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));	
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String jwt=jwtProvider.generateToken(authentication);
					UserDetailsImpl userprincipal=(UserDetailsImpl)authentication.getPrincipal();
					 System.out.println("User Authorities: " + userprincipal.getAuthorities());
					 
					List<String> roles= userprincipal.getAuthorities().stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList());
					
					System.out.println("User Roles List:"+roles);
					Long user_id=userprincipal.getId().longValue();
					String username=userprincipal.getUsername().toString();
					String email=userprincipal.getEmail().toString();
					System.err.println("userID"+user_id);
					return ResponseEntity.ok(new JwtResponse(jwt,roles,user_id,username,email));
		}
		//new user
		@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signupRequest){
				userservice.registerUser(signupRequest);
				
				return ResponseEntity.ok(new MessageResponse("User Registered Successfully"));
		}
		
		@GetMapping("/getuser/{id}")
		public ResponseEntity<?> getUserId(@PathVariable Long id){
				User user=	userservice.getUser(id);
			return ResponseEntity.ok(user);
		}

}
