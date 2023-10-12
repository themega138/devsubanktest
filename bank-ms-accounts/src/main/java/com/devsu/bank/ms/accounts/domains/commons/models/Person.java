package com.devsu.bank.ms.accounts.domains.commons.models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends AbstractEntity {
    private UUID uid;
    private String name;
    private Gender gender;
    private Integer age;
    private String personalId;
    private String address;
    private String phone;
}
