package com.example.graphql_customer.datafetchers;

import com.example.graphql_customer.codegen.types.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CustomerDataFetcherTest {

    private CustomerDataFetcher customerDataFetcher;

    @BeforeEach
    void setUp() {
        customerDataFetcher = new CustomerDataFetcher();
    }

    @Test
    void shouldReturnCustomers() {
        //given
        Integer page = 35;
        List<Customer> expected = List.of(
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

        //when
        List<Customer> actual = customerDataFetcher.customers(page);

        //then
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }
}
