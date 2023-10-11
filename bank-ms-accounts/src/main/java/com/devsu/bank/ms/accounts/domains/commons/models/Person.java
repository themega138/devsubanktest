package com.devsu.bank.ms.accounts.domains.commons.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Person extends AbstractEntity{
    private UUID uuid;
    private String name;
    private Gender gender;
    private Integer age;
    private String personalId;
    private String address;
    private String phone;
}
