package com.gurkanguldas.personsevice.bean;

import com.gurkanguldas.personsevice.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorAwareBean
{
    @Bean
    public AuditorAware<String> auditorAware()
    {
        return new AuditorAwareImpl();
    }
}
