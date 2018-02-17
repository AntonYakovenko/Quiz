package com.company.inject.spring_example;

public class User {
    private Address address;

    public User() {}

    public User(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "address=" + address +
                '}';
    }
}
