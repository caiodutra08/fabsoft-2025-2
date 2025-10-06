package br.univille.fabsoft_backend.service;

import java.util.List;

import br.univille.fabsoft_backend.api.dto.CustomerDTO;

public interface CustomerService {
    List<CustomerDTO> getAll();
    CustomerDTO getById(Long id);
    CustomerDTO save(CustomerDTO customer);
    CustomerDTO update(Long id, CustomerDTO customer);
    void delete(Long id);
}