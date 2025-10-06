package br.univille.fabsoft_backend.config.seeder;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.univille.fabsoft_backend.domain.entity.Customer;
import br.univille.fabsoft_backend.domain.entity.ExecutedExercise;
import br.univille.fabsoft_backend.domain.entity.Exercise;
import br.univille.fabsoft_backend.domain.enums.ExerciseDays;
import br.univille.fabsoft_backend.domain.enums.ExerciseDifficulty;
import br.univille.fabsoft_backend.repository.CustomerRepository;
import br.univille.fabsoft_backend.repository.ExecutedExerciseRepository;
import br.univille.fabsoft_backend.repository.ExerciseRepository;

@Configuration
public class DataSeeder {
    @Bean
    public CommandLineRunner initDatabase(
            CustomerRepository customerRepository, 
            ExerciseRepository exerciseRepository,
            ExecutedExerciseRepository executedExerciseRepository) {
        
        return args -> {
            if (customerRepository.count() == 0) {
                seedCustomers(customerRepository);
            }
            
            if (exerciseRepository.count() == 0) {
                seedExercises(exerciseRepository);
            }
            
            if (executedExerciseRepository.count() == 0 && customerRepository.count() > 0 && exerciseRepository.count() > 0) {
                seedExecutedExercises(executedExerciseRepository, customerRepository, exerciseRepository);
            }
        };
    }

    private void seedCustomers(CustomerRepository customerRepository) {
        List<Customer> customers = Arrays.asList(
            createCustomer("John Doe", 28, 75, 180, "Male", ExerciseDays.FOUR_DAYS),
            createCustomer("Jane Smith", 24, 60, 165, "Female", ExerciseDays.FIVE_DAYS),
            createCustomer("Bob Johnson", 35, 85, 175, "Male", ExerciseDays.THREE_DAYS),
            createCustomer("Alice Brown", 22, 55, 160, "Female", ExerciseDays.MORE_THAN_FIVE)
        );
        
        customerRepository.saveAll(customers);
    }

    private Customer createCustomer(String name, Integer age, Integer weight, Integer height, String gender, ExerciseDays exerciseDays) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(age);
        customer.setWeight(weight);
        customer.setHeight(height);
        customer.setGender(gender);
        customer.setExerciseDays(exerciseDays);
        return customer;
    }
    
    private void seedExercises(ExerciseRepository exerciseRepository) {
        List<Exercise> exercises = Arrays.asList(
            createExercise("Push-ups", "https://example.com/videos/push-ups.mp4", "https://example.com/thumbnails/push-ups.jpg"),
            createExercise("Squats", "https://example.com/videos/squats.mp4", "https://example.com/thumbnails/squats.jpg"),
            createExercise("Lunges", "https://example.com/videos/lunges.mp4", "https://example.com/thumbnails/lunges.jpg"),
            createExercise("Planks", "https://example.com/videos/planks.mp4", "https://example.com/thumbnails/planks.jpg"),
            createExercise("Burpees", "https://example.com/videos/burpees.mp4", "https://example.com/thumbnails/burpees.jpg")
        );
        
        exerciseRepository.saveAll(exercises);
    }

    private Exercise createExercise(String name, String video, String thumbnail) {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setVideo(video);
        exercise.setThumbnail(thumbnail);
        return exercise;
    }
    
    private void seedExecutedExercises(
            ExecutedExerciseRepository executedExerciseRepository, 
            CustomerRepository customerRepository, 
            ExerciseRepository exerciseRepository) {
        
        List<Customer> customers = customerRepository.findAll();
        List<Exercise> exercises = exerciseRepository.findAll();
        
        if (customers.isEmpty() || exercises.isEmpty()) {
            return;
        }
        
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            
            for (int j = 0; j < 2 && j < exercises.size(); j++) {
                Exercise exercise = exercises.get((i + j) % exercises.size());
                
                ExecutedExercise executedExercise = new ExecutedExercise();
                executedExercise.setCustomer(customer);
                executedExercise.setExercise(exercise);
                executedExercise.setDuration(30 + (j * 10));
                executedExercise.setRating(j + 3);
                executedExercise.setFeedback("Sample feedback " + (j + 1));
                executedExercise.setDifficulty(j % 2 == 0 ? ExerciseDifficulty.THREE : ExerciseDifficulty.FOUR);
                
                executedExerciseRepository.save(executedExercise);
            }
        }
    }
}