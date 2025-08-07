package com.Filmix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FilmixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmixApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // para todas las rutas
                        .allowedOrigins("http://127.0.0.1:5500") // o "*"
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
	


}
