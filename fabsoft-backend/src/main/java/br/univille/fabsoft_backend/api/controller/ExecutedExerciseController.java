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
    public ResponseEntity<ExecutedExerciseDTO> getById(@PathVariable("id") String id) {
        try {
            UUID uuid = UUID.fromString(id);
            ExecutedExerciseDTO executedExercise = executedExerciseService.getById(uuid);
            
            if (executedExercise != null) {
                return new ResponseEntity<>(executedExercise, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ExecutedExerciseDTO>> getByCustomerId(@PathVariable("customerId") String customerId) {
        try {
            UUID uuid = UUID.fromString(customerId);
            List<ExecutedExerciseDTO> executedExercises = executedExerciseService.getByCustomerId(uuid);
            return new ResponseEntity<>(executedExercises, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping
    public ResponseEntity<ExecutedExerciseDTO> create(@RequestBody ExecutedExerciseDTO executedExerciseDTO) {
        ExecutedExerciseDTO createdExecutedExercise = executedExerciseService.save(executedExerciseDTO);
        return new ResponseEntity<>(createdExecutedExercise, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ExecutedExerciseDTO> update(@PathVariable("id") String id, @RequestBody ExecutedExerciseDTO executedExerciseDTO) {
        try {
            UUID uuid = UUID.fromString(id);
            ExecutedExerciseDTO updatedExecutedExercise = executedExerciseService.update(uuid, executedExerciseDTO);
            
            if (updatedExecutedExercise != null) {
                return new ResponseEntity<>(updatedExecutedExercise, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}/feedback")
    public ResponseEntity<ExecutedExerciseDTO> updateFeedback(@PathVariable("id") String id, @RequestBody FeedbackDTO feedbackDTO) {
        try {
            UUID uuid = UUID.fromString(id);
            ExecutedExerciseDTO updatedExecutedExercise = executedExerciseService.updateFeedback(uuid, feedbackDTO);
            
            if (updatedExecutedExercise != null) {
                return new ResponseEntity<>(updatedExecutedExercise, HttpStatus.OK);
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
            ExecutedExerciseDTO executedExercise = executedExerciseService.getById(uuid);
            
            if (executedExercise != null) {
                executedExerciseService.delete(uuid);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}