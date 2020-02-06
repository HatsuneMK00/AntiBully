package xyz.makise.antibully.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.makise.antibully.backend.entity.User;
import xyz.makise.antibully.backend.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService {
    final
    UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        这边要封装roles 因为getAttributes需要用到里面的Roles 不封装的话就没有了
        User user = userMapper.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        user.setRoles(userMapper.getRolesOfUserByUserId(user.getUserId()));
        return user;
    }

    public int registerUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(user.getPassword());
        user.setPassword(encodedPwd);
        user.setMoney(0);
        return userMapper.addNewUser(user);
    }
}
