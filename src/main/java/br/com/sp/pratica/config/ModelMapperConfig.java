package br.com.sp.pratica.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean	
	public ModelMapper returnModelMapper() {
		return new ModelMapper();
	}
}
