package br.univille.fabsoft_backend.api.dto;

import br.univille.fabsoft_backend.domain.enums.ExerciseDifficulty;
import lombok.Data;

@Data
public class ExecutedExerciseDTO {
    private Long id;
    private Long exerciseId;
    private String exerciseName;
    private Long customerId;
    private Integer duration;
    private Integer rating;
    private String feedback;
    private ExerciseDifficulty difficulty;
}