package com.gen.signatureverification.config;

import com.gen.signatureverification.services.impl.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
}
