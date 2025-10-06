package br.univille.fabsoft_backend.service;

import java.util.List;
import java.util.UUID;
import br.univille.fabsoft_backend.api.dto.ExecutedExerciseDTO;
import br.univille.fabsoft_backend.api.dto.FeedbackDTO;

public interface ExecutedExerciseService {
    List<ExecutedExerciseDTO> getAll();
    ExecutedExerciseDTO getById(UUID id);
    List<ExecutedExerciseDTO> getByCustomerId(UUID customerId);
    ExecutedExerciseDTO save(ExecutedExerciseDTO executedExercise);
    ExecutedExerciseDTO update(UUID id, ExecutedExerciseDTO executedExercise);
    ExecutedExerciseDTO updateFeedback(UUID id, FeedbackDTO feedbackDTO);
    void delete(UUID id);
}