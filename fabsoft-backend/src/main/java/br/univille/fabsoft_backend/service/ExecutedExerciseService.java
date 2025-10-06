package br.univille.fabsoft_backend.service;

import java.util.List;

import br.univille.fabsoft_backend.api.dto.ExecutedExerciseDTO;
import br.univille.fabsoft_backend.api.dto.FeedbackDTO;

public interface ExecutedExerciseService {
    List<ExecutedExerciseDTO> getAll();
    ExecutedExerciseDTO getById(Long id);
    List<ExecutedExerciseDTO> getByCustomerId(Long customerId);
    ExecutedExerciseDTO save(ExecutedExerciseDTO executedExercise);
    ExecutedExerciseDTO update(Long id, ExecutedExerciseDTO executedExercise);
    ExecutedExerciseDTO updateFeedback(Long id, FeedbackDTO feedbackDTO);
    void delete(Long id);
}