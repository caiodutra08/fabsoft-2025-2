package br.univille.fabsoft_backend.domain.entity;

import br.univille.fabsoft_backend.domain.enums.ExerciseDifficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExecutedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer duration;
    private Integer rating;
    private String feedback;
    
    @Enumerated(EnumType.STRING)
    private ExerciseDifficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
}
