package br.univille.fabsoft_backend.api.dto;

import br.univille.fabsoft_backend.domain.enums.ExerciseDays;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private Integer age;
    private Integer weight;
    private Integer height;
    private String gender;
    private ExerciseDays exerciseDays;
}