package br.univille.fabsoft_backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.fabsoft_backend.api.dto.TrainingPlanDTO;
import br.univille.fabsoft_backend.service.TrainingPlanService;

@RestController
@RequestMapping("/api/v1/training-plans")
public class TrainingPlanController {

    @Autowired
    private TrainingPlanService trainingPlanService;
    
    @GetMapping("/generate/{customerId}")
    public ResponseEntity<TrainingPlanDTO> generateTrainingPlan(@PathVariable("customerId") Long customerId) {
        TrainingPlanDTO trainingPlan = trainingPlanService.generateTrainingPlan(customerId);
        
        if (trainingPlan != null) {
            return new ResponseEntity<>(trainingPlan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}