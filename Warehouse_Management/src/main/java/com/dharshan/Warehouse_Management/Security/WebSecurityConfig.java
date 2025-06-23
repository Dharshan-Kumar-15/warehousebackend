package com.dharshan.Warehouse_Management.Security;


import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{
	
	
	private  final UserDetailsServiceImpl userDetailsService;
	
	private final JWTAuthEntryPoint unauthorizedHandler;
	
	private final JwtTokenFilter jwtTokenFilter;
	
	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,JWTAuthEntryPoint unauthorizedHandler,JwtTokenFilter jwtTokenFilter) {
		this.userDetailsService=userDetailsService;
		this.unauthorizedHandler=unauthorizedHandler;
		this.jwtTokenFilter=jwtTokenFilter;
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
		
		authprovider.setUserDetailsService(userDetailsService);
		authprovider.setPasswordEncoder(passwordEncoder());
		
		return authprovider;
		
	}
	
	@Bean
	public  AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws  Exception {
		
		return authConfig.getAuthenticationManager();
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.authorizeHttpRequests(auth -> 
		   auth.requestMatchers("/auth/**").permitAll()
		   .requestMatchers("/admin/getproduct").hasAnyRole("ADMIN","BUYER","WAREHOUSE")
		   .requestMatchers("/admin/getproduct/**").hasAnyRole("ADMIN","BUYER","WAREHOUSE")
		   .requestMatchers("/admin/**").hasAnyRole("ADMIN")
		   .requestMatchers("/buyer/orders").hasAnyRole("BUYER","TRANSPORTATION")
		   .requestMatchers("/buyer/**").hasAnyRole("BUYER")
		   .requestMatchers("/warehouse/**").hasAnyRole("WAREHOUSE")
		   .requestMatchers("/transport/**").hasAnyRole("TRANSPORTATION")
		   .anyRequest().authenticated()
		   );
		
		
		http.authenticationProvider(authenticationProvider());
		
		http.addFilterBefore(jwtTokenFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
//        configuration.setAllowedHeaders(Arrays.asList(
//            "Authorization", 
//            "Content-Type",
//            "X-Requested-With",
//            "Accept",
//            "X-XSRF-TOKEN"
//        ));
//        configuration.setExposedHeaders(Arrays.asList(
//            "Authorization",
//            "xsrf-token"
//        ));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
	@Bean
     CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://warehousefrontend-production.up.railway.app"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	
	

}
