package com.sto.bid.service.impl;

import com.sto.bid.exception.EntityNotFoundException;
import com.sto.bid.exception.UniqueConstraintError;
import com.sto.bid.model.entity.CustomerEntity;
import com.sto.bid.model.rest.Customer;
import com.sto.bid.model.rest.request.CustomerRequest;
import com.sto.bid.repository.CustomerRepository;
import com.sto.bid.service.CustomerService;
import com.sto.bid.util.CustomerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public Long createCustomer(CustomerRequest customerRequest) {
        customerRequest.setPhone(customerRequest.getPhone().replaceAll("\\s", ""));
        if (customerRepository.existsByPhone(customerRequest.getPhone())) {
            throw new UniqueConstraintError();
        }

        CustomerEntity customerEntity = CustomerMapper.dtoToEntity(customerRequest);

        customerRepository.save(customerEntity);
        return customerEntity.getId();
    }

    @Override
    public Customer getCustomer(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
        return CustomerMapper.entityToDto(customerEntity);
    }
}
