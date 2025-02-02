package com.sto.check.util;

import com.sto.check.model.Customer;
import com.sto.check.model.entity.CustomerEntity;

public class CustomerMapper {
    public static CustomerEntity dtoToEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .middleName(customer.getMiddleName())
                .phone(customer.getPhone()).build();
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
