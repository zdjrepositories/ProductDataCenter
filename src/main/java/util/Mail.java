package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 邮件发送工具类
 *
 */
public class Mail{
    private static String _USER_NAME_ = "361406445@qq.com";//邮箱用户名
    private static String _PASSWORD_ = "aeymnoioitjubgfj";//授权码
    private static String _SMTP_ADDRESS_ = "smtp.qq.com";//
    private final static int _SMTP_PORT_SSL_ = 25;
    private static String from = "361406445@qq.com";

    private static String subject;
    private static String toAddress;

    /**
     * 初始化方法，获取配置信息
     */
    public Mail(){
        super();
        Properties properties=new Properties();
        InputStream inputStream=Object.class.getResourceAsStream("/mail.properties");
        InputStreamReader inputStreamReader=null;

        try { inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            properties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSubject(properties.getProperty("subject"));
        setToAddress(properties.getProperty("toAddress"));
    }

    /**
     * 发送邮件方法
     * @param content
     */
    public static void sendMail( String content){
        Email email = new SimpleEmail();
        email.setHostName(_SMTP_ADDRESS_);
        email.setSmtpPort(_SMTP_PORT_SSL_);
        email.setAuthenticator(new DefaultAuthenticator(_USER_NAME_, _PASSWORD_));
        //email.setSSLOnConnect(true);
        email.setSSLOnConnect(true);
        try {
            email.setFrom(from);
            email.setSubject(Mail.getSubject());
            email.setMsg(content);
            email.addTo(Mail.getToAddress());
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static String getFrom() {
        return from;
    }

    public static void setFrom(String from) {
        Mail.from = from;
    }

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        Mail.subject = subject;
    }

    public static String getToAddress() {
        return toAddress;
    }

    public static void setToAddress(String toAddress) {
        Mail.toAddress = toAddress;
    }
}