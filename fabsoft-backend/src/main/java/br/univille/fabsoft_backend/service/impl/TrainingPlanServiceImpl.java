package br.univille.fabsoft_backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fabsoft_backend.api.dto.CustomerDTO;
import br.univille.fabsoft_backend.api.dto.ExerciseDTO;
import br.univille.fabsoft_backend.api.dto.TrainingPlanDTO;
import br.univille.fabsoft_backend.domain.enums.ExerciseDays;
import br.univille.fabsoft_backend.service.CustomerService;
import br.univille.fabsoft_backend.service.ExerciseService;
import br.univille.fabsoft_backend.service.TrainingPlanService;

@Service
public class TrainingPlanServiceImpl implements TrainingPlanService {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ExerciseService exerciseService;
    
    @Autowired
    
    private final Random random = new Random();

    @Override
    public TrainingPlanDTO generateTrainingPlan(UUID customerId) {
        CustomerDTO customerDTO = customerService.getById(customerId);
        if (customerDTO == null) {
            return null;
        }
        
        TrainingPlanDTO trainingPlan = new TrainingPlanDTO();
        trainingPlan.setCustomerId(customerId.toString());
        
        int exercisesPerDay = determineExercisesPerDay(customerDTO.getExerciseDays());
        
        List<ExerciseDTO> allExercises = exerciseService.getAll();
        if (allExercises.isEmpty()) {
            createSampleExercises();
            allExercises = exerciseService.getAll();
        }
        
        List<ExerciseDTO> selectedExercises = selectExercises(allExercises, exercisesPerDay);
        
        String planName = generatePlanName(customerDTO);
        
        trainingPlan.setPlanName(planName);
        trainingPlan.setExercises(selectedExercises);
        
        return trainingPlan;
    }
    
    private int determineExercisesPerDay(ExerciseDays days) {
        return switch (days) {
            case THREE_DAYS -> 5;
            case FOUR_DAYS -> 4;
            case FIVE_DAYS -> 3;
            default -> 3;
        };
    }
    
    private List<ExerciseDTO> selectExercises(List<ExerciseDTO> allExercises, int count) {
        if (allExercises.size() <= count) {
            return new ArrayList<>(allExercises);
        }
        
        List<ExerciseDTO> selectedExercises = new ArrayList<>();
        List<Integer> selectedIndices = new ArrayList<>();
        
        while (selectedExercises.size() < count) {
            int index = random.nextInt(allExercises.size());
            if (!selectedIndices.contains(index)) {
                selectedIndices.add(index);
                selectedExercises.add(allExercises.get(index));
            }
        }
        
        return selectedExercises;
    }
    
    private String generatePlanName(CustomerDTO customer) {
        String intensity;
        
        if (customer.getAge() < 30) {
            intensity = "Alta Intensidade";
        } else if (customer.getAge() < 50) {
            intensity = "Média Intensidade";
        } else {
            intensity = "Baixa Intensidade";
        }
        
        String focus;
        if ("M".equalsIgnoreCase(customer.getGender())) {
            focus = "Hipertrofia";
        } else {
            focus = "Tonificação";
        }
        
        return "Plano de " + focus + " - " + intensity;
    }
    
    private void createSampleExercises() {
        List<ExerciseDTO> sampleExercises = new ArrayList<>();
        
        ExerciseDTO ex1 = new ExerciseDTO();
        ex1.setName("Supino Reto");
        ex1.setVideo("https://example.com/videos/supino_reto.mp4");
        ex1.setThumbnail("https://example.com/thumbnails/supino_reto.jpg");
        sampleExercises.add(ex1);
        
        ExerciseDTO ex2 = new ExerciseDTO();
        ex2.setName("Agachamento");
        ex2.setVideo("https://example.com/videos/agachamento.mp4");
        ex2.setThumbnail("https://example.com/thumbnails/agachamento.jpg");
        sampleExercises.add(ex2);
        
        ExerciseDTO ex3 = new ExerciseDTO();
        ex3.setName("Levantamento Terra");
        ex3.setVideo("https://example.com/videos/levantamento_terra.mp4");
        ex3.setThumbnail("https://example.com/thumbnails/levantamento_terra.jpg");
        sampleExercises.add(ex3);
        
        ExerciseDTO ex4 = new ExerciseDTO();
        ex4.setName("Desenvolvimento de Ombros");
        ex4.setVideo("https://example.com/videos/desenvolvimento.mp4");
        ex4.setThumbnail("https://example.com/thumbnails/desenvolvimento.jpg");
        sampleExercises.add(ex4);
        
        ExerciseDTO ex5 = new ExerciseDTO();
        ex5.setName("Rosca Direta");
        ex5.setVideo("https://example.com/videos/rosca_direta.mp4");
        ex5.setThumbnail("https://example.com/thumbnails/rosca_direta.jpg");
        sampleExercises.add(ex5);
        
        ExerciseDTO ex6 = new ExerciseDTO();
        ex6.setName("Tríceps Pulley");
        ex6.setVideo("https://example.com/videos/triceps_pulley.mp4");
        ex6.setThumbnail("https://example.com/thumbnails/triceps_pulley.jpg");
        sampleExercises.add(ex6);
        
        for (ExerciseDTO exercise : sampleExercises) {
            exerciseService.save(exercise);
        }
    }
}