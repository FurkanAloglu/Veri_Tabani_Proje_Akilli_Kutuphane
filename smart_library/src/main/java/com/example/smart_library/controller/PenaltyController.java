package com.example.smart_library.controller;

import com.example.smart_library.dto.penalty.PenaltyResponse;
import com.example.smart_library.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/penalties")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PenaltyController {

    private final JavaMailSender mailSender;

    public void sendLateNotification(String to, String bookTitle) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("library@ktu.edu.tr");
        message.setTo(to);
        message.setSubject("Gecikmiş Kitap İadesi");
        message.setText("Sayın öğrenci, " + bookTitle + " kitabınızın iade süresi dolmuştur. Lütfen iade ediniz.");
        mailSender.send(message);
        System.out.println("Mail gönderildi: " + to);
    }

    private final PenaltyService penaltyService;

    @GetMapping
    public ResponseEntity<List<PenaltyResponse>> getAllPenalties() {
        return ResponseEntity.ok(penaltyService.findAll());
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<PenaltyResponse> payPenalty(@PathVariable UUID id) {
        return ResponseEntity.ok(penaltyService.payPenalty(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenaltyResponse> getPenaltyById(@PathVariable UUID id) {
        return penaltyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePenalty(@PathVariable UUID id) {
        penaltyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}