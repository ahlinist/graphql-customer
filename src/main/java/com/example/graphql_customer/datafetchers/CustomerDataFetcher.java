package com.example.graphql_customer.datafetchers;

import com.example.graphql_customer.codegen.types.CreateCustomerInput;
import com.example.graphql_customer.codegen.types.CreateCustomerResponse;
import com.example.graphql_customer.codegen.types.Customer;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class CustomerDataFetcher {

    @DgsQuery
    public List<Customer> customers(@InputArgument Integer page) {
        System.out.println(page);
        return List.of(
                Customer.newBuilder()
                        .id(UUID.randomUUID().toString())
                        .firstName("Mickey")
                        .lastName("Mouse")
                        .build(),
                Customer.newBuilder()
                        .id(UUID.randomUUID().toString())
                        .firstName("Winnie")
                        .lastName("The Pooh")
                        .build()
        );
    }

    @DgsQuery
    public Customer customer(@InputArgument String id) {
        System.out.println(id);

        return Customer.newBuilder()
                .id(UUID.randomUUID().toString())
                .firstName("Winnie")
                .lastName("The Pooh")
                .build();
    }

    @DgsMutation
    public CreateCustomerResponse createCustomer(@InputArgument CreateCustomerInput input) {
        System.out.println(input);

        return CreateCustomerResponse.newBuilder()
                .customer(
                        Customer.newBuilder()
                                .firstName(input.getFirstName())
                                .lastName(input.getLastName())
                                .build()
                )
                .build();
    }
}
