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
 * @Description: ����Email
 * @author: �°�����
 * @date: 2015-1-12 ����9:42:56
 *
 */
public class SendMailUtils {
    /**
     * �����ʼ�
     */
    public static void send(String subject, String content){
        //ʹ��JavaMail�����ʼ���5������
        //1������session
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
        //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
        session.setDebug(true);
        try {
            //2��ͨ��session�õ�transport����
            Transport ts = session.getTransport();
            //3��ʹ��������û��������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
            ts.connect(PropertyUtil.getProperty("mail.host"), PropertyUtil.getProperty("mail.username"), PropertyUtil.getProperty("mail.password"));
            //4�������ʼ�
            Message message = SendMailUtils.createSimpleMail(session,content,subject);
            //5�������ʼ�
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @Method: createSimpleMail
     * @Description: ����һ��ֻ�����ı����ʼ�
     * @Anthor:�°�����
     *
     * @param
     * @return
     * @throws Exception
     */
    private static MimeMessage createSimpleMail(Session session,String content,String subject)
            throws Exception {
        //�����ʼ�����
        MimeMessage message = new MimeMessage(session);
        //ָ���ʼ��ķ�����
        message.setFrom(new InternetAddress(PropertyUtil.getProperty("mail.from")));
        //ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(PropertyUtil.getProperty("mail.to")));
        //�ʼ��ı���
        message.setSubject(subject);
        // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
        Multipart mainPart = new MimeMultipart();
        // ����һ������HTML���ݵ�MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // ����HTML����
        html.setContent(content, "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        // ��MiniMultipart��������Ϊ�ʼ�����
        message.setContent(mainPart);
        return message;
    }

}
