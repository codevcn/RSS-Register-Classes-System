package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("mongo")
public class MongoController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public String home() {
        System.out.println();
        System.out.println();
        System.out.println();

        // delete
        customerRepository.deleteAll();

        // create
        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Smith"));

        // read (fetch all customers)
        System.out.println("--------------------------------");
        System.out.println(">>> Customers found with findAll():");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println("--------------------------------");

        System.out.println();

        // read (fetch an individual customer)
        System.out.println("--------------------------------");
        System.out.println(">>> Customer found with findByFirstName('Alice'):");
        Customer customer1 = customerRepository.findByFirstName("Alice");
        System.out.println(customer1);
        System.out.println("--------------------------------");

        System.out.println();

        System.out.println("--------------------------------");
        System.out.println(">>> Customers found with findByLastName('Smith'):");
        for (Customer customer3 : customerRepository.findByLastName("Smith")) {
            System.out.println(customer3);
        }
        System.out.println("--------------------------------");

        System.out.println();

        // update
        customer1.setFirstName("VCN yuricon");
        customerRepository.save(customer1);
        System.out.println("--------------------------------");
        System.out.println(">>> Customer found with findByFirstName('VCN yuricon'):");
        Customer customer2 = customerRepository.findByFirstName("VCN yuricon");
        System.out.println(customer2);
        System.out.println("--------------------------------");

        return ">>> Mongo Okay";
    }

}