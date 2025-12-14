package com.example.smart_library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    public void sendLateReturnNotification(String toEmail, String userName, String bookTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("smartlibrary@smartlibrary.com");
        message.setTo(toEmail);
        message.setSubject("Gecikmiş Kitap İadesi Uyarısı!");
        message.setText("Sayın " + userName + ",\n\n" +
                "'" + bookTitle + "' isimli kitabın iade süresi geçmiştir.\n" +
                "Lütfen en kısa sürede iade ediniz, aksi takdirde günlük ceza işlemeye devam edecektir.\n\n" +
                "Akıllı Kütüphane Sistemi");

        mailSender.send(message);
        System.out.println("Mail gönderildi: " + toEmail);
    }
}