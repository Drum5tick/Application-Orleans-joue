package com.wildcodeschool.festivalorleansjoue.config;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wildcodeschool.festivalorleansjoue.Sept2019JavaOrleansFestivalOrleansJoueApplication;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${app.upload.dir}")
	public String uploadDir;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	String currentPath = Sept2019JavaOrleansFestivalOrleansJoueApplication.class.getProtectionDomain().getCodeSource()
			.getLocation().getPath();

	String baseDir = Paths.get(currentPath).getParent().getParent().getParent().toString().replaceFirst("file:", "");


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + baseDir + uploadDir + File.separator);

	}
	

	@Bean("baseDir")
	public String getBaseDir() {
		return baseDir.toString();
	}
	


}
