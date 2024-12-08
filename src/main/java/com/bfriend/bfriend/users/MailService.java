package com.bfriend.bfriend.users;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailAuthenticationNumberService mailAuthenticationNumberService;

    public MailService(JavaMailSender javaMailSender, MailAuthenticationNumberService mailAuthenticationNumberService) {
        this.javaMailSender = javaMailSender;
        this.mailAuthenticationNumberService = mailAuthenticationNumberService;
    }

    public MimeMessage createMail(String email, String authenticationNumber) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom("chae02yo@gmail.com");
            mimeMessageHelper.setSubject("[밥친구] 이메일 인증 번호 발송");
            mimeMessageHelper.setText(authenticationNumber);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return mimeMessage;
    }

    public String sendMail(String email) {
        String authenticationNumber = mailAuthenticationNumberService.createAuthenticationNumber();
        MimeMessage mimeMessage = createMail(email, authenticationNumber);

        javaMailSender.send(mimeMessage);
        mailAuthenticationNumberService.saveAuthenticationNumberToRedis(email, authenticationNumber);

        return "success";
    }
}
