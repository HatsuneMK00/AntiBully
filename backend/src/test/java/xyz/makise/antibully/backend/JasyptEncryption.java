package xyz.makise.antibully.backend;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class JasyptEncryption {
    @Autowired
    StringEncryptor encryptor;


    @Test
    public void jacketEncrypt() {

//        用于执行生成密码加密的test方法
    }
}
