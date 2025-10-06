package br.univille.fabsoft_backend.service;

import java.util.List;
import java.util.UUID;
import br.univille.fabsoft_backend.api.dto.ExerciseDTO;

public interface ExerciseService {
    List<ExerciseDTO> getAll();
    ExerciseDTO getById(UUID id);
    ExerciseDTO save(ExerciseDTO exercise);
    ExerciseDTO update(UUID id, ExerciseDTO exercise);
    void delete(UUID id);
}