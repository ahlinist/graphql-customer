package com.example.graphql_customer.datafetchers;

import com.example.graphql_customer.codegen.client.CustomersGraphQLQuery;
import com.example.graphql_customer.codegen.client.CustomersProjectionRoot;
import com.example.graphql_customer.codegen.types.Customer;
import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @Test
    void shouldReturnCustomers() {
        List<Customer> customers = dgsQueryExecutor.executeAndExtractJsonPathAsObject(
                getAllPostsQuery(),
                "data.customers[*]",
                new TypeRef<List<Customer>>() {});
        assertThat(customers.get(0).getFirstName()).isEqualTo("Mickey");
    }

    private String getAllPostsQuery() {
        GraphQLQueryRequest customersRequest = new GraphQLQueryRequest(
                CustomersGraphQLQuery.newRequest().page(3).build(),
                new CustomersProjectionRoot<>().firstName().lastName().id()
        );
        return customersRequest.serialize();
    }
}
