package vn.iotstar.utils;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class SendMail {

    // Thông tin tài khoản email gửi
    private final String username = "your_email@gmail.com"; // đổi thành email của bạn
    private final String password = "your_app_password";  // mật khẩu ứng dụng (App Password nếu dùng Gmail)

    public void sendMail(String toEmail, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");             // bật xác thực
        props.put("mail.smtp.starttls.enable", "true");  // bật STARTTLS
        props.put("mail.smtp.host", "smtp.gmail.com");   // server SMTP Gmail
        props.put("mail.smtp.port", "587");              // cổng SMTP

        // Dùng jakarta.mail.Authenticator
        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Mail đã được gửi tới " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
