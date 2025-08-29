package br.univille.fabsoft_backend.domain.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Exercise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;
  private String name;
  private String video;
  private String thumbnail;
}
