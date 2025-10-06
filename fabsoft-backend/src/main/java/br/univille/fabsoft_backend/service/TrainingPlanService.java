package br.univille.fabsoft_backend.service;

import br.univille.fabsoft_backend.api.dto.TrainingPlanDTO;

public interface TrainingPlanService {
    TrainingPlanDTO generateTrainingPlan(Long customerId);
}