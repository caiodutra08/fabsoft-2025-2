package br.univille.fabsoft_backend.api.dto;

import lombok.Data;

@Data
public class ExerciseDTO {
    private String id;
    private String name;
    private String video;
    private String thumbnail;
}