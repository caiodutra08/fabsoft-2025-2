package br.univille.fabsoft_backend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fabsoft_backend.api.dto.ExecutedExerciseDTO;
import br.univille.fabsoft_backend.api.dto.FeedbackDTO;
import br.univille.fabsoft_backend.domain.entity.Customer;
import br.univille.fabsoft_backend.domain.entity.ExecutedExercise;
import br.univille.fabsoft_backend.domain.entity.Exercise;
import br.univille.fabsoft_backend.repository.CustomerRepository;
import br.univille.fabsoft_backend.repository.ExecutedExerciseRepository;
import br.univille.fabsoft_backend.repository.ExerciseRepository;
import br.univille.fabsoft_backend.service.ExecutedExerciseService;

@Service
public class ExecutedExerciseServiceImpl implements ExecutedExerciseService {

    @Autowired
    private ExecutedExerciseRepository executedExerciseRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public List<ExecutedExerciseDTO> getAll() {
        return executedExerciseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExecutedExerciseDTO getById(UUID id) {
        return executedExerciseRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }
    
    @Override
    public List<ExecutedExerciseDTO> getByCustomerId(UUID customerId) {
        return executedExerciseRepository.findByCustomerId(customerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExecutedExerciseDTO save(ExecutedExerciseDTO executedExerciseDTO) {
        ExecutedExercise executedExercise = new ExecutedExercise();
        BeanUtils.copyProperties(executedExerciseDTO, executedExercise, "customerId", "exerciseId");
        
        if (executedExerciseDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(UUID.fromString(executedExerciseDTO.getCustomerId())).orElse(null);
            executedExercise.setCustomer(customer);
        }
        
        if (executedExerciseDTO.getExerciseId() != null) {
            Exercise exercise = exerciseRepository.findById(UUID.fromString(executedExerciseDTO.getExerciseId())).orElse(null);
            executedExercise.setExercise(exercise);
        }
        
        executedExercise = executedExerciseRepository.save(executedExercise);
        return convertToDTO(executedExercise);
    }

    @Override
    public ExecutedExerciseDTO update(UUID id, ExecutedExerciseDTO executedExerciseDTO) {
        return executedExerciseRepository.findById(id)
                .map(existingExecutedExercise -> {
                    BeanUtils.copyProperties(executedExerciseDTO, existingExecutedExercise, "id", "customerId", "exerciseId");
                    
                    if (executedExerciseDTO.getCustomerId() != null) {
                        Customer customer = customerRepository.findById(UUID.fromString(executedExerciseDTO.getCustomerId())).orElse(null);
                        existingExecutedExercise.setCustomer(customer);
                    }
                    
                    if (executedExerciseDTO.getExerciseId() != null) {
                        Exercise exercise = exerciseRepository.findById(UUID.fromString(executedExerciseDTO.getExerciseId())).orElse(null);
                        existingExecutedExercise.setExercise(exercise);
                    }
                    
                    ExecutedExercise updatedExecutedExercise = executedExerciseRepository.save(existingExecutedExercise);
                    return convertToDTO(updatedExecutedExercise);
                })
                .orElse(null);
    }
    
    @Override
    public ExecutedExerciseDTO updateFeedback(UUID id, FeedbackDTO feedbackDTO) {
        return executedExerciseRepository.findById(id)
                .map(existingExecutedExercise -> {
                    existingExecutedExercise.setRating(feedbackDTO.getRating());
                    existingExecutedExercise.setFeedback(feedbackDTO.getFeedback());
                    ExecutedExercise updatedExecutedExercise = executedExerciseRepository.save(existingExecutedExercise);
                    return convertToDTO(updatedExecutedExercise);
                })
                .orElse(null);
    }

    @Override
    public void delete(UUID id) {
        executedExerciseRepository.deleteById(id);
    }

    private ExecutedExerciseDTO convertToDTO(ExecutedExercise executedExercise) {
        ExecutedExerciseDTO executedExerciseDTO = new ExecutedExerciseDTO();
        BeanUtils.copyProperties(executedExercise, executedExerciseDTO);
        
        executedExerciseDTO.setId(executedExercise.getId().toString());
        
        if (executedExercise.getCustomer() != null) {
            executedExerciseDTO.setCustomerId(executedExercise.getCustomer().getId().toString());
        }
        
        if (executedExercise.getExercise() != null) {
            executedExerciseDTO.setExerciseId(executedExercise.getExercise().getId().toString());
            executedExerciseDTO.setExerciseName(executedExercise.getExercise().getName());
        }
        
        return executedExerciseDTO;
    }
}