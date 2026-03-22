package com.linnea.Inlamning1BackEnd.dto;

import com.linnea.Inlamning1BackEnd.entity.Address;

import java.time.LocalDate;

public record MemberAllDataDto(Long id,
                               String firstName, String lastName,
                               Address address,
                               String email, String phoneNumber,
                               LocalDate dateOfBirth) {
}
