package xyz.makise.antibully.backend.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
//            当前请求需要的权限
//            从源码看到 在我们自己写的方法中 最后调用了createList方法生成一个ConfigAttribute的Collection
//            这个函数根据String数组生成对应的Collection
//            因此一个ConfigAttribute就对应之前我们写的一个String
//            这边对这个ConfigAttribute的Collection进行了迭代 没有问题
            String attribute = configAttribute.getAttribute();

//            如果是需要登录的权限 则检查一下是否有登录
            if ("ROLE_LOGIN".equals(attribute)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else {
                    return;
                }
            }

//            用户当前拥有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
//                只要用户拥有所需权限中的其中任意一项权限就放行
//                假如某个请求需要权限A,B,C
//                用户拥有权限B,D
//                在外循环检查到B的时候 用户会被放行
                if (authority.getAuthority().equals(attribute)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不够");
    }

//    下面两个函数都从return false 改成了 return true
//    源码上看 函数在org.springframework.security.access.intercept中的AbstractSecurityInterceptor中被使用
//    并且是一句assert为true的Assert语句中使用的 必须改成return true;
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
