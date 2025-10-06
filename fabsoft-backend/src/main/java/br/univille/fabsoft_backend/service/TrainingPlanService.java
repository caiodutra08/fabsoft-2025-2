package br.univille.fabsoft_backend.service;

import java.util.UUID;

import br.univille.fabsoft_backend.api.dto.TrainingPlanDTO;

public interface TrainingPlanService {
    TrainingPlanDTO generateTrainingPlan(UUID customerId);
}