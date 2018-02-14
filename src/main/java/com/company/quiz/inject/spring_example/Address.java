package com.company.quiz.inject.spring_example;

public class Address {
    private String street;
    private int houseNum;

    public Address(String street, int houseNum) {
        this.street = street;
        this.houseNum = houseNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNum=" + houseNum +
                '}';
    }
}
