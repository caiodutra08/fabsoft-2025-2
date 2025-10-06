package br.univille.fabsoft_backend.service;

import java.util.List;

import br.univille.fabsoft_backend.api.dto.ExerciseDTO;

public interface ExerciseService {
    List<ExerciseDTO> getAll();
    ExerciseDTO getById(Long id);
    ExerciseDTO save(ExerciseDTO exercise);
    ExerciseDTO update(Long id, ExerciseDTO exercise);
    void delete(Long id);
}