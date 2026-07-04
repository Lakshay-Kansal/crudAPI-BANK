package com.lakshay.CRUDbank.controller;

import com.lakshay.CRUDbank.model.Customer;
import com.lakshay.CRUDbank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long Id){
        return customerService.getCustomerById(Id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Customer> updateCustomerById(
            @RequestBody Customer customer,@PathVariable Long Id){
        try{
            return ResponseEntity.ok(customerService.updateCustomer(customer,Id));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long Id){
        try{
            customerService.deleteCustomer(Id);
            return ResponseEntity.ok("Customer deleted successfully");
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
