package com.ywz.nkcommunity;

import com.ywz.nkcommunity.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author YWZ
 * @date 2022/11/7 - 21:09
 */

@SpringBootTest
public class MailTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendMail(){

        mailClient.sendMail("ywz82465@163.com","Test","test001");
    }

    @Test
    public void testSendHtmlMail(){
        Context context = new Context();
        context.setVariable("username","ywz");
        String content = templateEngine.process("/mail/demo",context);
        mailClient.sendMail("ywz82465@163.com","Test02",content);
    }


}
