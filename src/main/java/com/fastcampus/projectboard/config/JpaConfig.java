package com.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing  // 자동 auditing 기능
@Configuration  // configuration bean이 됨
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("jhseo");  // TODO: 스프링 시큐리티로 인증 기능 붙이게 될 때, 수정하기
    }

}
