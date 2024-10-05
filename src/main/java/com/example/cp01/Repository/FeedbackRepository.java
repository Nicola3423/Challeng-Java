package com.example.cp01.Repository;

import com.example.cp01.Entidade.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
