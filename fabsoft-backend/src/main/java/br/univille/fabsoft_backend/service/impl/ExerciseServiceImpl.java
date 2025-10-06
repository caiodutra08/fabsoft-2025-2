package br.univille.fabsoft_backend.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fabsoft_backend.api.dto.ExerciseDTO;
import br.univille.fabsoft_backend.domain.entity.Exercise;
import br.univille.fabsoft_backend.repository.ExerciseRepository;
import br.univille.fabsoft_backend.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public List<ExerciseDTO> getAll() {
        return exerciseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExerciseDTO getById(UUID id) {
        return exerciseRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public ExerciseDTO save(ExerciseDTO exerciseDTO) {
        Exercise exercise = new Exercise();
        BeanUtils.copyProperties(exerciseDTO, exercise);
        exercise = exerciseRepository.save(exercise);
        return convertToDTO(exercise);
    }

    @Override
    public ExerciseDTO update(UUID id, ExerciseDTO exerciseDTO) {
        return exerciseRepository.findById(id)
                .map(existingExercise -> {
                    BeanUtils.copyProperties(exerciseDTO, existingExercise, "id");
                    Exercise updatedExercise = exerciseRepository.save(existingExercise);
                    return convertToDTO(updatedExercise);
                })
                .orElse(null);
    }

    @Override
    public void delete(UUID id) {
        exerciseRepository.deleteById(id);
    }

    private ExerciseDTO convertToDTO(Exercise exercise) {
        ExerciseDTO exerciseDTO = new ExerciseDTO();
        BeanUtils.copyProperties(exercise, exerciseDTO);
        exerciseDTO.setId(exercise.getId().toString());
        return exerciseDTO;
    }
}