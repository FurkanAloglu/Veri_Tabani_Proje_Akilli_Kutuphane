package com.example.smart_library.service;

import org.springframework.stereotype.Service;
import com.example.smart_library.repository.PenaltyRepository;
import com.example.smart_library.model.Penalty;
import java.util.Optional;
import java.util.List;

@Service
public class PenaltyService{
    private final PenaltyRepository penaltyRepository;

    public PenaltyService(PenaltyRepository penaltyRepository){
        this.penaltyRepository=penaltyRepository;
    }

    public List<Penalty> findAll(){
        return penaltyRepository.findAll();
    }

    public Penalty save(Penalty penalty){
        return penaltyRepository.save(penalty);
    }

    public Optional<Penalty> findById(Long id){
        return penaltyRepository.findById(id);
    }

    public void deleteById(Long id){
        penaltyRepository.deleteById(id);
    }

}
