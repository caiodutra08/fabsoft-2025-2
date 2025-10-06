package br.univille.fabsoft_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.fabsoft_backend.domain.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
