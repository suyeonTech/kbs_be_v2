package com.bfriend.bfriend.users;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public MimeMessage createMail(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom("chae02yo@gmail.com");
            mimeMessageHelper.setSubject("[밥친구] 이메일 인증 번호 발송");
            mimeMessageHelper.setText(createAuthenticationNumber());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return mimeMessage;
    }

    public String sendMail(String email) {
        MimeMessage mimeMessage = createMail(email);
        javaMailSender.send(mimeMessage);

        return "success";
    }

    public String createAuthenticationNumber() {
        SecureRandom secureRandom = new SecureRandom();
        int AuthenticationNumber = 100000 + secureRandom.nextInt(900000);

        return String.valueOf(AuthenticationNumber);
    }
}
