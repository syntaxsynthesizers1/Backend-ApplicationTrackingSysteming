package com.group4.ApplicationTrackingSytem.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ATSEmailServiceImplementation implements ATSEmailService{
//    private final JavaMailSender javaMailSender;
//    @Value("${spring.mail.username}")
//    private String sender;
//
//    @Value("${spring.mail.from.name}")
//    private String senderName;
    @Override
    public String sendSimpleMail(EmailRequest emailRequest) {
//        try {
////            MimeMessage message = javaMailSender.createMimeMessage();
////            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            String fromAddress = senderName + " <" + sender + ">";
//            helper.setFrom(fromAddress); // Set the "From" name and email address
//            helper.setTo(emailRequest.getTo());
//            helper.setSubject(emailRequest.getSubject());
//            helper.setText(emailRequest.getBody(), true);  // Set the second parameter to true for HTML content
//
////            javaMailSender.send(message);
//            System.out.println("working");
//            return "Mail Sent Successfully...";
//        } catch (Exception e) {
//            System.out.println("not working");
//            System.out.println(e);
//            return "Error while Sending Mail";
//        }
        return "";
    }
}
