# graphql-customer

http://localhost:8082/graphql

```
query {
    customers(page: 3) {
        id
        firstName
        lastName
    }
    customer(id: "asd") {
        firstName
    }
}
```

```
mutation {
    createCustomer(input: {firstName: "Don", lastName: "Kijot"}) {
        customer {
            firstName
        }
    }
}
```