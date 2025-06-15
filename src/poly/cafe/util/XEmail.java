/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author baoha
 */
public class XEmail {
     public static void sendOTP(String toEmail, String otp) throws MessagingException, UnsupportedEncodingException {
        final String fromEmail = "khanhdgtv00309@gmail.com"; // Gmail của bạn
        final String password = "eyxh lzou lixb kndu";  // Mật khẩu ứng dụng

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP server
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Tạo nội dung mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Hệ Thống Quản Lý Quán"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã xác thực OTP");
            message.setText("Mã OTP của bạn là: " + otp + "\nVui lòng không chia sẻ với người khác!");

            // Gửi
            Transport.send(message);
            System.out.println(" Gửi OTP thành công đến: " + toEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}
