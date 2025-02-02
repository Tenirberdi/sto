package com.sto.repair.util;

import com.sto.repair.model.Customer;
import com.sto.repair.model.entity.CustomerEntity;

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
