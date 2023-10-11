package com.devsu.bank.ms.clients.domains.commons.models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends AbstractEntity {
    private UUID uuid;
    private String name;
    private Gender gender;
    private Integer age;
    private String personalId;
    private String address;
    private String phone;
}
