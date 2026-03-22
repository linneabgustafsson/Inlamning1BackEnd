package com.linnea.Inlamning1BackEnd.dto;

import com.linnea.Inlamning1BackEnd.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MemberCreateDto(
        @NotBlank @Size(max = 50) String firstName,
        @NotBlank @Size(max = 50) String lastName,
        Address address,
        @NotBlank @Size(max = 50) @Email String email,
        @NotBlank @Size(max = 10) String phoneNumber,
        LocalDate dateOfBirth) {
}
