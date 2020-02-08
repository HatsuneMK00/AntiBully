package xyz.makise.antibully.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/*
* 资源服务器 在这里配置哪些url需要认证
*
* */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("res1");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        TODO: client对应的authority还是没搞懂是什么 这边只配置一个client然后根据用户的role进行权限管理
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/register").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .cors();
    }
}
