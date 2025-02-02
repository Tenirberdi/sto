package com.sto.bid.controller;

import com.sto.bid.model.rest.Customer;
import com.sto.bid.model.rest.request.CustomerRequest;
import com.sto.bid.model.rest.response.RestResponse;
import com.sto.bid.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customers")
    public RestResponse<Map<String, Long>> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return RestResponse.success(Map.of("customerId", customerService.createCustomer(customerRequest)));
    }

    @GetMapping("/customers/{id}")
    public RestResponse<Customer> getCustomer(@PathVariable Long id) {
        return RestResponse.success(customerService.getCustomer(id));
    }
}
