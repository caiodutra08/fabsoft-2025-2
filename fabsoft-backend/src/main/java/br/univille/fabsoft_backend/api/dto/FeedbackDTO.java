package br.univille.fabsoft_backend.api.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
    private Long executedExerciseId;
    private Integer rating;
    private String feedback;
}