package cn.bj.king.util;

import cn.bj.king.config.MailServerConfig;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class MailUtilTest {
    MailServerConfig config = null;
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("email_config");
    String to = "hailong.liu@0071515.com";
    String emailContent = "测试邮件";
    @Before
    public void initConfit() {
        config = new MailServerConfig();
        //邮件配置
        config.setHostName(resourceBundle.getString("email.hostname"));
        config.setPassword(resourceBundle.getString("email.password"));
        config.setSmtpPort(Integer.parseInt(resourceBundle.getString("email.smtpport")));
        config.setUserName(resourceBundle.getString("email.username"));
        config.setUseSSLOnConnect(Boolean.parseBoolean(resourceBundle.getString("email.usessl")));
    }

    @Test
    public void sendTextEmail() throws Exception {
        MailUtil.sendTextEmail(config, resourceBundle.getString("email.from"), to, "库存不足预警", emailContent);
    }

    @Test
    public void sendEmailWithAttachment() throws Exception {
        MailAttachment attachment=new MailAttachment();
        attachment.setFileName("email_config");
        attachment.setAttachment(new File("E:\\workspace\\king\\src\\main\\resources\\email_config.properties"));
        MailUtil.sendEmailWithAttachment(config, resourceBundle.getString("email.from"), to, "库存不足预警", emailContent,attachment);
    }

    @Test
    public void sendEmailWithAttachment1() throws Exception {
       String p= resourceBundle.getLocale().getDisplayName();
        System.out.println(p);
    }

    @Test
    public void sendHtmlEmailWithAttachment() throws Exception {
    }

    @Test
    public void sendHtmlEmailWithAttachment1() throws Exception {
    }

}