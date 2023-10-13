package com.devsu.bank.ms.clients.domains.commons.models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public class Person extends AbstractEntity {
    private String name;
    private Gender gender;
    private Integer age;
    private String personalId;
    private String address;
    private String phone;
}
