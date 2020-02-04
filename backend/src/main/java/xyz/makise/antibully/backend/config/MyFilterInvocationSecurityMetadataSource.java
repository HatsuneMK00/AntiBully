package xyz.makise.antibully.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.makise.antibully.backend.entity.Resource;
import xyz.makise.antibully.backend.entity.Role;
import xyz.makise.antibully.backend.service.ResourceService;

import java.util.Collection;
import java.util.List;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    ResourceService resourceService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
//    这个覆写的方法相当于提供了一个方法：为任意一个requestUrl返回该url需要的权限
//    一个比较简单的版本是为每一个url pattern设置一个权限
//    这边实现了从数据库获取url pattern
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
//  如果访问的url是login 直接放行不需要任何权限
//        这里很可能登录页的url和处理登陆请求的url不能相同 因此这里在login后面加个p
        if ("/login".equals(requestUrl)) {
            return null;
        }
//  将请求的url和所有已有的url pattern进行匹配 得到访问这个url需要的权限（Role）
        List<Resource> resources = resourceService.getAllResources();
        for (Resource resource : resources) {
//            当有一个匹配到的url pattern出现时 将对应的需要的Role添加到SecurityConfig里
//            TODO：由于一旦匹配到一个就会返回 因此需要在返回url的时候注意url pattern返回的顺序按照优先级由高到低
            if (antPathMatcher.match(resource.getUrlPattern(),requestUrl) && resource.getRoles().size() > 0) {
                List<Role> roles = resource.getRoles();
                String[] roleStrings = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleStrings[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(roleStrings);
            }
        }
//        没有匹配到的情况下全部都是需要登录权限的
//        return SecurityConfig.createList("ROLE_LOGIN");
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
//        TODO: 这里不知道是在干什么
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
