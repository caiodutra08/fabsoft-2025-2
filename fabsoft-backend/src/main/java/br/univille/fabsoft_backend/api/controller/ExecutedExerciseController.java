package br.univille.fabsoft_backend.api.controller;

import java.util.List;

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

import br.univille.fabsoft_backend.api.dto.ExecutedExerciseDTO;
import br.univille.fabsoft_backend.api.dto.FeedbackDTO;
import br.univille.fabsoft_backend.service.ExecutedExerciseService;

@RestController
@RequestMapping("/api/v1/executed-exercises")
public class ExecutedExerciseController {

    @Autowired
    private ExecutedExerciseService executedExerciseService;
    
    @GetMapping
    public ResponseEntity<List<ExecutedExerciseDTO>> getAll() {
        List<ExecutedExerciseDTO> executedExercises = executedExerciseService.getAll();
        return new ResponseEntity<>(executedExercises, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExecutedExerciseDTO> getById(@PathVariable("id") Long id) {
        ExecutedExerciseDTO executedExercise = executedExerciseService.getById(id);
        
        if (executedExercise != null) {
            return new ResponseEntity<>(executedExercise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ExecutedExerciseDTO>> getByCustomerId(@PathVariable("customerId") Long customerId) {
        List<ExecutedExerciseDTO> executedExercises = executedExerciseService.getByCustomerId(customerId);
        return new ResponseEntity<>(executedExercises, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ExecutedExerciseDTO> create(@RequestBody ExecutedExerciseDTO executedExerciseDTO) {
        ExecutedExerciseDTO createdExecutedExercise = executedExerciseService.save(executedExerciseDTO);
        return new ResponseEntity<>(createdExecutedExercise, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExecutedExerciseDTO> update(@PathVariable("id") Long id, @RequestBody ExecutedExerciseDTO executedExerciseDTO) {
        ExecutedExerciseDTO updatedExecutedExercise = executedExerciseService.update(id, executedExerciseDTO);
        
        if (updatedExecutedExercise != null) {
            return new ResponseEntity<>(updatedExecutedExercise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}/feedback")
    public ResponseEntity<ExecutedExerciseDTO> updateFeedback(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {
        ExecutedExerciseDTO updatedExecutedExercise = executedExerciseService.updateFeedback(id, feedbackDTO);
        
        if (updatedExecutedExercise != null) {
            return new ResponseEntity<>(updatedExecutedExercise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        ExecutedExerciseDTO executedExercise = executedExerciseService.getById(id);
        
        if (executedExercise != null) {
            executedExerciseService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}