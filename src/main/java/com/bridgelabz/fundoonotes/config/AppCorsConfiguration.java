package com.bridgelabz.fundoonotes.config;


	import org.springframework.boot.web.servlet.FilterRegistrationBean;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.web.cors.CorsConfiguration;
	import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
	import org.springframework.web.filter.CorsFilter;

	@Configuration
	public class AppCorsConfiguration {
	    @Bean
	    public FilterRegistrationBean corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("http://localhost:3000");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
//	        config.addExposedHeader("*");
//	        config.getMaxAge();
	        source.registerCorsConfiguration("/**", config);
	        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	        bean.setOrder(0);
	        return bean;
	    }
	    
	   
}
	
