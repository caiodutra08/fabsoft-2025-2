package br.univille.fabsoft_backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.fabsoft_backend.domain.entity.ExecutedExercise;

@Repository
public interface ExecutedExerciseRepository extends JpaRepository<ExecutedExercise, UUID> {
  List<ExecutedExercise> findByCustomerId(UUID customerId);
}