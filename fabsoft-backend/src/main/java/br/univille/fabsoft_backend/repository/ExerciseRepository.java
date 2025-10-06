package br.univille.fabsoft_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.fabsoft_backend.domain.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

}