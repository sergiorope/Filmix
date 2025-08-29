package com.Filmix;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
public class FilmixApplication {
	
	@Value("${security.jwt.secret-key}")
	private String key;

	public static void main(String[] args) {
		SpringApplication.run(FilmixApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("http://127.0.0.1:5500")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                    .allowedHeaders("*") 
	                    .allowCredentials(true);
	        }
	    };
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> {}) 
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/usuario/login").permitAll()
	            .requestMatchers("/peliculas").permitAll()
	            .requestMatchers("/graphiql").permitAll()
	            .anyRequest().authenticated() 
	        )
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}

    
    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
                final String authHeader = request.getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    try {
                        Claims user = Jwts.parser()
                                .setSigningKey(key)
                                .parseClaimsJws(token)
                                .getBody();
                        
                        System.out.println("wdwdw");


                        if (user != null) {
                        	
                            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                            org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);
                            
                        }
                    } catch (Exception e) {
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }
	
	
	
	


}
