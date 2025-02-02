package com.sto.bid.util;

import com.sto.bid.model.entity.CustomerEntity;
import com.sto.bid.model.rest.Customer;
import com.sto.bid.model.rest.request.CustomerRequest;

public class CustomerMapper {
    public static CustomerEntity dtoToEntity(CustomerRequest customerRequest) {
        return CustomerEntity.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .middleName(customerRequest.getMiddleName())
                .phone(customerRequest.getPhone()).build();
    }

    public static Customer entityToDto(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .middleName(customerEntity.getMiddleName())
                .phone(customerEntity.getPhone()).build();
    }
}
