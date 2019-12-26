//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.bj.king.util;

import cn.bj.king.config.MailServerConfig;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class MailUtil {
    public MailUtil() {
    }

    public static void sendTextEmail(MailServerConfig config, String from, String to, String subject, String msg) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(config.getHostName());
        email.setSmtpPort(config.getSmtpPort());
        email.setSSLOnConnect(config.isUseSSLOnConnect());
        email.setAuthentication(config.getUserName(), config.getPassword());
        email.setFrom(from);
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }

    public static void sendEmailWithAttachment(MailServerConfig config, String from, String to, String subject, String msg, MailAttachment attachments) throws EmailException {
        sendEmailWithAttachment(config, from, to, subject, msg, new MailAttachment[]{attachments});
    }

    public static void sendEmailWithAttachment(MailServerConfig config, String from, String to, String subject, String msg, MailAttachment[] attachments) throws EmailException {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(config.getHostName());
        email.setSmtpPort(config.getSmtpPort());
        email.setSSLOnConnect(config.isUseSSLOnConnect());
        email.setAuthentication(config.getUserName(), config.getPassword());
        email.addTo(to);
        email.setFrom(from);
        email.setSubject(subject);
        email.setMsg(msg);
        if (attachments != null && attachments.length > 0) {
            MailAttachment[] var7 = attachments;
            int var8 = attachments.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                MailAttachment a = var7[var9];
                EmailAttachment attachment = new EmailAttachment();
                attachment.setDisposition("attachment");
                attachment.setPath(a.getAttachment().getAbsolutePath());
                if (a.getFileName() != null) {
                    attachment.setName(a.getFileName());
                } else {
                    attachment.setName(a.getAttachment().getName());
                }

                email.attach(attachment);
            }
        }

        email.send();
    }

    public static String sendHtmlEmailWithAttachment(MailServerConfig config, String from, String to, String subject, String msg, EmailAttachment[] attachments) throws EmailException {
        return sendHtmlEmailWithAttachment(config, from, to, (String)null, subject, msg, attachments);
    }

    public static String sendHtmlEmailWithAttachment(MailServerConfig config, String from, String to, String cc, String subject, String msg, EmailAttachment[] attachments) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setHostName(config.getHostName());
        email.setSmtpPort(config.getSmtpPort());
        email.setSSLOnConnect(config.isUseSSLOnConnect());
        email.setAuthentication(config.getUserName(), config.getPassword());
        email.addTo(to);
        email.setFrom(from);
        int i;
        if (cc != null && cc.trim() != "") {
            String[] ccArr = cc.split(",");

            for(i = 0; i < ccArr.length; ++i) {
                email.addCc(ccArr[i]);
            }
        }

        email.setSubject(subject);
        email.setHtmlMsg(msg);
        if (attachments != null && attachments.length > 0) {
            EmailAttachment[] var12 = attachments;
            i = attachments.length;

            for(int var10 = 0; var10 < i; ++var10) {
                EmailAttachment emailAttachment = var12[var10];
                email.attach(emailAttachment);
            }
        }

        String result = email.send();
        return result;
    }
}
