package com.example.latihanuts.repository;

import com.example.latihanuts.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<Candidate, Long> {
}
