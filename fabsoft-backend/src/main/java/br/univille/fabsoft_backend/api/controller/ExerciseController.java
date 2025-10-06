package br.univille.fabsoft_backend.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.fabsoft_backend.api.dto.ExerciseDTO;
import br.univille.fabsoft_backend.service.ExerciseService;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;
    
    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> getAll() {
        List<ExerciseDTO> exercises = exerciseService.getAll();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getById(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            ExerciseDTO exercise = exerciseService.getById(uuid);
            
            if (exercise != null) {
                return new ResponseEntity<>(exercise, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping
    public ResponseEntity<ExerciseDTO> create(@RequestBody ExerciseDTO exerciseDTO) {
        ExerciseDTO createdExercise = exerciseService.save(exerciseDTO);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> update(@PathVariable("id") String id, @RequestBody ExerciseDTO exerciseDTO) {
        try {
            UUID uuid = UUID.fromString(id);
            ExerciseDTO updatedExercise = exerciseService.update(uuid, exerciseDTO);
            
            if (updatedExercise != null) {
                return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            ExerciseDTO exercise = exerciseService.getById(uuid);
            
            if (exercise != null) {
                exerciseService.delete(uuid);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}