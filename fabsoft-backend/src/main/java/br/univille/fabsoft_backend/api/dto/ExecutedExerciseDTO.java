package br.univille.fabsoft_backend.api.dto;

import lombok.Data;
import br.univille.fabsoft_backend.domain.enums.ExerciseDifficulty;

@Data
public class ExecutedExerciseDTO {
    private String id;
    private String exerciseId;
    private String exerciseName;
    private String customerId;
    private Integer duration;
    private Integer rating;
    private String feedback;
    private ExerciseDifficulty difficulty;
}