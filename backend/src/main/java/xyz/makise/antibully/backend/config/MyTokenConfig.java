package xyz.makise.antibully.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class MyTokenConfig {
//    TODO: 将token的存储方式从内存改为redis
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
