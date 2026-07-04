package com.lakshay.CRUDbank.service;

import com.lakshay.CRUDbank.model.Customer;
import com.lakshay.CRUDbank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long Id){
        return customerRepository.findById(Id);
    }

    public Customer updateCustomer(Customer updatedCustomer , Long Id){
        return customerRepository.findById(Id)
                .map(existing->{
                    existing.setFirstName(updatedCustomer.getFirstName());
                    existing.setLastName(updatedCustomer.getLastName());
                    existing.setPhoneNumber(updatedCustomer.getPhoneNumber());
                    existing.setEmail(updatedCustomer.getEmail());
                    return customerRepository.save(existing);
                })
                .orElseThrow(()-> new RuntimeException("Customer NOT found with ID :" + Id));
    }

    public void deleteCustomer(Long Id){
        if(!customerRepository.existsById(Id)){
            throw new RuntimeException("Customer NOT found with ID :" + Id);
        }
        customerRepository.deleteById(Id);
    }
}
