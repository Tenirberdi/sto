package com.sto.bid.service;

import com.sto.bid.model.rest.Customer;
import com.sto.bid.model.rest.request.CustomerRequest;

public interface CustomerService {
    Long createCustomer(CustomerRequest customerRequest);
    Customer getCustomer(Long customerId);
}
