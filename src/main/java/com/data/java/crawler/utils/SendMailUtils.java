package com.data.java.crawler.utils;

import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * @author: 孤傲苍狼
 * @date: 2015-1-12 下午9:42:56
 *
 */
public class SendMailUtils {
    /**
     * 发送邮件
     */
    public static void send(String subject, String content){
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Properties prop = new Properties();
        prop.setProperty("mail.host", PropertyUtil.getProperty("mail.host"));
        prop.setProperty("mail.transport.protocol",PropertyUtil.getProperty("mail.transport.protocol"));
        prop.setProperty("mail.smtp.auth",PropertyUtil.getProperty("mail.smtp.auth"));
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        PropertyUtil.getProperty("mail.username"),
                        PropertyUtil.getProperty("mail.password")
                );
            }
        });
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        try {
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect(PropertyUtil.getProperty("mail.host"), PropertyUtil.getProperty("mail.username"), PropertyUtil.getProperty("mail.password"));
            //4、创建邮件
            Message message = SendMailUtils.createSimpleMail(session,content,subject);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     * @Anthor:孤傲苍狼
     *
     * @param
     * @return
     * @throws Exception
     */
    private static MimeMessage createSimpleMail(Session session,String content,String subject)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(PropertyUtil.getProperty("mail.from")));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(PropertyUtil.getProperty("mail.to")));
        //邮件的标题
        message.setSubject(subject);
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // 设置HTML内容
        html.setContent(content, "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        // 将MiniMultipart对象设置为邮件内容
        message.setContent(mainPart);
        return message;
    }

}
