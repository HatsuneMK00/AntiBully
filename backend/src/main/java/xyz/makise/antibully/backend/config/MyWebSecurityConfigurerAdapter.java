package xyz.makise.antibully.backend.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import xyz.makise.antibully.backend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    @Autowired
    AccessDecisionManager accessDecisionManager;

//    覆写这个函数 提供一个符合要求的UserService类
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    覆写这个类 在配置时使用我们自己写的两个类
//    在拦截了一个url后 这个url需要什么权限
//    用户是否会被放行
//    这些逻辑由我们继承的类里的方法决定
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/register").permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                .formLogin().loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("{\"status\":\"error\",\"msg\":\"");
                        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
                            stringBuffer.append("用户名或密码错误");
                        } else {
                            stringBuffer.append("登陆失败");
                        }
                        stringBuffer.append("\"}");
                        out.write(stringBuffer.toString());
                        out.flush();
//                        这是从response里拿出来的流 没必要关了吧？
//                        out.close();
                    }
                }).successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("{\"status\":\"success\",\"msg\":\"");
                stringBuffer.append("登陆成功");
                stringBuffer.append("\"}");
                out.write(stringBuffer.toString());
                out.flush();
//                out.close();
            }
        }).and().logout().permitAll().and().csrf().disable();
    }
}
