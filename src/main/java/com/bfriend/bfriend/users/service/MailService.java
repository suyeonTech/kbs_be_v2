package com.bfriend.bfriend.users.service;

import com.bfriend.bfriend.users.dto.request.CheckAuthenticationNumberRequest;
import com.bfriend.bfriend.utils.ResponseDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailAuthenticationNumberService mailAuthenticationNumberService;

    @Value("${spring.mail.username}")
    private String emailSender;

    public MimeMessage createMail(String email, String authenticationNumber) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom(emailSender);
            mimeMessageHelper.setSubject("[밥친구] 이메일 인증 번호 발송");
            mimeMessageHelper.setText(authenticationNumber);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return mimeMessage;
    }

    @Async
    public CompletableFuture<ResponseEntity<ResponseDTO>> sendMail(String email) {
        String authenticationNumber = mailAuthenticationNumberService.create();
        MimeMessage mimeMessage = createMail(email, authenticationNumber);

        try {
            javaMailSender.send(mimeMessage);
        }
        catch (MailException e) {
            throw new RuntimeException("이메일 전송 중 오류 발생", e);
        }
        mailAuthenticationNumberService.saveToRedis(email, authenticationNumber);

        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.ok(ResponseDTO.builder().message("이메일 인증 번호 발송 성공").build());
        });
    }

    public ResponseEntity<ResponseDTO> checkAuthenticationNumber(CheckAuthenticationNumberRequest request) {
        return mailAuthenticationNumberService.checkAuthenticationNumber(request);
    }
}
