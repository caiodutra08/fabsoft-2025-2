package br.univille.fabsoft_backend.service;

import java.util.List;
import java.util.UUID;
import br.univille.fabsoft_backend.api.dto.CustomerDTO;

public interface CustomerService {
    List<CustomerDTO> getAll();
    CustomerDTO getById(UUID id);
    CustomerDTO save(CustomerDTO customer);
    CustomerDTO update(UUID id, CustomerDTO customer);
    void delete(UUID id);
}