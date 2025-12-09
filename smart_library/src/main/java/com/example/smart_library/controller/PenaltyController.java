package com.example.smart_library.controller;

import com.example.smart_library.dto.penalty.PenaltyRequest;
import com.example.smart_library.dto.penalty.PenaltyResponse;
import com.example.smart_library.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/penalties")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PenaltyController {

    private final PenaltyService penaltyService;

    @GetMapping
    public ResponseEntity<List<PenaltyResponse>> getAllPenalties() {
        List<PenaltyResponse> penalties = penaltyService.findAll();
        return ResponseEntity.ok(penalties);
    }

    @PostMapping
    public ResponseEntity<PenaltyResponse> createPenalty(@RequestBody PenaltyRequest penaltyRequest) {
        PenaltyResponse savedPenalty = penaltyService.save(penaltyRequest);
        return new ResponseEntity<>(savedPenalty, HttpStatus.CREATED);
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
