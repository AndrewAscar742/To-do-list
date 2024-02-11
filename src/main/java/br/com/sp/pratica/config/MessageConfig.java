package br.com.sp.pratica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "messageExceptions.properties", value = "classpath:messageExceptions.properties",
encoding = "UTF-8")
public class MessageConfig {

}
