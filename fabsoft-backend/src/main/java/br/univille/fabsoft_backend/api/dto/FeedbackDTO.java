package br.univille.fabsoft_backend.api.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private String executedExerciseId;
    private Integer rating;
    private String feedback;
}