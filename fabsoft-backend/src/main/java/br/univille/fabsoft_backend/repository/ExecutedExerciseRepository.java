package br.univille.fabsoft_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.fabsoft_backend.domain.entity.ExecutedExercise;

@Repository
public interface ExecutedExerciseRepository extends JpaRepository<ExecutedExercise, Long> {
  List<ExecutedExercise> findByCustomerId(Long customerId);
}