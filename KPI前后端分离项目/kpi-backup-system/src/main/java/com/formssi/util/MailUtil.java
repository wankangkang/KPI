/**
 * FileName:MailUtil.java
 * @Description:TODO
 * ALL rights Reserved,Designed By SP
 * CopyRight:CopyRight(C)2017-2018
 * @author:Bill
 * @version:V1.0
 * Createdate:2018年1月1日下午4:55:43
 ********************************
 *Modifycation History:
 *date:2018年1月1日
 *Author:
 *Version:
 *Description:
 */
package com.formssi.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.formssi.bean.Account;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @author Bill
 *
 */
public class MailUtil {

	public static boolean send_qqmail(String strMail, String strTitle, String strText){
        boolean bret = false;
        try
        {
        	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";//SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            Properties props = new Properties();

            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.starttls.enable", "true");//使用 STARTTLS安全连接

            //你自己的邮箱
            props.put("mail.user", "1043386358@qq.com"); 
            //你开启pop3/smtp时的验证码
            props.put("mail.password", "qbtbmkybngnqbdda");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.debug", "true");      //开启调试模式
            props.put("mail.transport.protocol", "SMTP");     // 发送邮件协议名称
            props.put("mail.smtp.ssl.socketFactory", sf);
            props.put("mail.smtp.ssl.socketFactory.port", "465");


            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            mailSession.setDebug(true);  
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            //InternetAddress to = new InternetAddress(strMail); 
            message.addRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(strMail));
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(props.getProperty("mail.user")));
           // message.setRecipient(RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(strTitle);

            // 设置邮件的内容体
            message.setContent(strText, "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);
            bret = true;
        }
        catch (AddressException e) {
             e.printStackTrace();
        }
        catch (MessagingException e) {
             e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bret;
    }
	
	//未解决网易邮箱554拦截的问题
	@SuppressWarnings("unused")
	private static boolean send_163mail(String strMail, String strTitle, String strText){
        boolean bret = false;
        try
        {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.163.com");

            // 发件人的账号
            props.put("mail.user", "billsendmail@163.com");
            //发件人的密码
            props.put("mail.password", "Billsendmail123"); 

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            mailSession.setDebug(true);  
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            // 设置收件人
           // InternetAddress to = new InternetAddress(strMail); 
            //message.setRecipient(RecipientType.TO, to);
            message.addRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(strMail));
            message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(props.getProperty("mail.user")));
            // 设置邮件标题
            message.setSubject(strTitle);

            // 设置邮件的内容体
            message.setContent(strText, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message, message.getAllRecipients());
            bret = true;
        }
        catch (AddressException e) {
             e.printStackTrace();
        }
        catch (MessagingException e) {
             e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bret;
    }
	
	public static void main(String[] args) {
		Account account = new Account();
		account.setAccountName("smp");
		System.out.println("开始发送");
		String emailText = "<body><p>您好," + account.getAccountName() + ":</p><br/><p>尊敬的" + account.getAccountName() + "组,您已成功注册项目组名为<Strong>" + account.getAccountName() + "</Strong>的账号</p></body>";
        if (send_qqmail("zhengxunsen@formssi.com", "深圳四方精创资讯股份有限公司港中银项目组注册", emailText))
            System.out.println("QQ邮件发送成功");

       /*if (send_163mail("1043386358@qq.com", "账号注册", "<body><p>您的账号已注册成功</p></body>"))
            System.out.println("网易邮件发送成功");*/
	}
}
