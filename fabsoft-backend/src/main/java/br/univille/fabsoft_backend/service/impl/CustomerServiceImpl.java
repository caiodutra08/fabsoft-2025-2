package br.univille.fabsoft_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.fabsoft_backend.api.dto.CustomerDTO;
import br.univille.fabsoft_backend.domain.entity.Customer;
import br.univille.fabsoft_backend.repository.CustomerRepository;
import br.univille.fabsoft_backend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    @Override
    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    BeanUtils.copyProperties(customerDTO, existingCustomer, "id");
                    Customer updatedCustomer = customerRepository.save(existingCustomer);
                    return convertToDTO(updatedCustomer);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }
}