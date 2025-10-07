package br.univille.fabsoft_backend.service.impl;

import java.util.List;
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
    public ExecutedExerciseDTO getById(Long id) {
        return executedExerciseRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }
    
    @Override
    public List<ExecutedExerciseDTO> getByCustomerId(Long customerId) {
        return executedExerciseRepository.findByCustomerId(customerId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExecutedExerciseDTO save(ExecutedExerciseDTO executedExerciseDTO) {
        ExecutedExercise executedExercise = new ExecutedExercise();
        // Manual mapping to avoid any BeanUtils issues
        executedExercise.setDuration(executedExerciseDTO.getDuration());
        executedExercise.setRating(executedExerciseDTO.getRating());
        executedExercise.setFeedback(executedExerciseDTO.getFeedback());
        executedExercise.setDifficulty(executedExerciseDTO.getDifficulty());
        
        if (executedExerciseDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(executedExerciseDTO.getCustomerId()).orElse(null);
            executedExercise.setCustomer(customer);
        }
        
        if (executedExerciseDTO.getExerciseId() != null) {
            Exercise exercise = exerciseRepository.findById(executedExerciseDTO.getExerciseId()).orElse(null);
            executedExercise.setExercise(exercise);
        }
        
        // ID is not set - will be auto-generated
        executedExercise = executedExerciseRepository.save(executedExercise);
        return convertToDTO(executedExercise);
    }

    @Override
    public ExecutedExerciseDTO update(Long id, ExecutedExerciseDTO executedExerciseDTO) {
        return executedExerciseRepository.findById(id)
                .map(existingExecutedExercise -> {
                    BeanUtils.copyProperties(executedExerciseDTO, existingExecutedExercise, "id", "customerId", "exerciseId");
                    
                    if (executedExerciseDTO.getCustomerId() != null) {
                        Customer customer = customerRepository.findById(executedExerciseDTO.getCustomerId()).orElse(null);
                        existingExecutedExercise.setCustomer(customer);
                    }
                    
                    if (executedExerciseDTO.getExerciseId() != null) {
                        Exercise exercise = exerciseRepository.findById(executedExerciseDTO.getExerciseId()).orElse(null);
                        existingExecutedExercise.setExercise(exercise);
                    }
                    
                    ExecutedExercise updatedExecutedExercise = executedExerciseRepository.save(existingExecutedExercise);
                    return convertToDTO(updatedExecutedExercise);
                })
                .orElse(null);
    }
    
    @Override
    public ExecutedExerciseDTO updateFeedback(Long id, FeedbackDTO feedbackDTO) {
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
    public void delete(Long id) {
        executedExerciseRepository.deleteById(id);
    }

    private ExecutedExerciseDTO convertToDTO(ExecutedExercise executedExercise) {
        ExecutedExerciseDTO executedExerciseDTO = new ExecutedExerciseDTO();
        BeanUtils.copyProperties(executedExercise, executedExerciseDTO);
        
        if (executedExercise.getCustomer() != null) {
            executedExerciseDTO.setCustomerId(executedExercise.getCustomer().getId());
        }
        
        if (executedExercise.getExercise() != null) {
            executedExerciseDTO.setExerciseId(executedExercise.getExercise().getId());
            executedExerciseDTO.setExerciseName(executedExercise.getExercise().getName());
        }
        
        return executedExerciseDTO;
    }
}