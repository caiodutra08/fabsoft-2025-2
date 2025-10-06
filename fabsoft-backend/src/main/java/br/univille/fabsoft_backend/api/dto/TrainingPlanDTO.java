package br.univille.fabsoft_backend.api.dto;

import java.util.List;
import lombok.Data;

@Data
public class TrainingPlanDTO {
    private String customerId;
    private String planName;
    private List<ExerciseDTO> exercises;
}