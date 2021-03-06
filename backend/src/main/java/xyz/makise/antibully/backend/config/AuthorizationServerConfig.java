package xyz.makise.antibully.backend.config;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/*
* 认证服务器配置
* 本来这个认证服务器和资源服务器可以是分开的两个服务器 这边就先弄在一块了
* */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenStore tokenStore;
    @Autowired
    ClientDetailsService clientDetailsService;
    @Autowired
    AuthorizationServerTokenServices tokenServices;

    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(tokenStore);
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        感觉上配置多个client是在需要使用多种oauth2.0模式的时候用的
//        这里只配置一个client 通过用户的role来进行权限划分
//        因此authorities和scopes的值有些奇怪 但是前段已经写好了这里就不改了
        clients.inMemory()
                .withClient("client_1")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("ADMIN")
                .resourceIds("res1")
                .scopes("ADMIN");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        这边还有很多其他的配置 如tokenService UserDetailsService也没有配
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices);
    }
}
