package com.example.latihanuts.controller;

import com.example.latihanuts.model.Candidate;
import com.example.latihanuts.repository.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/candidates")
@RestController
public class CandidateController {
    @Autowired
    private CandidateRepo candidateRepo;

    @GetMapping
    public List<Candidate> getAll(){
        return candidateRepo.findAll();
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id){
        Candidate candidate = candidateRepo.findById(id).orElse(null);
        if(candidate != null){
            return candidate;
        }else{
            return "Kandidat dengan ID " + id + "Tidak ada";
        }
    }

    @PostMapping
    public Candidate create(@RequestBody Candidate candidate){
        return candidateRepo.save(candidate);
    }

    @PutMapping("/{id}")
    public String editById(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate existingCandidate = candidateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Kandidat dengan ID " + id + " tidak ditemukan"));

        // Update properties yang diubah
        existingCandidate.setName(candidate.getName());
        existingCandidate.setNumber(candidate.getNumber());
        existingCandidate.setBatch(candidate.getBatch());

        // Simpan perubahan ke dalam repository
        candidateRepo.save(existingCandidate);

        return "Kandidat berhasil diperbarui.";
    }


    // hapus kandidat berdasarkan ID
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        candidateRepo.deleteById(id);
        return "Kandidat berhasil dihapus.";
    }
}
