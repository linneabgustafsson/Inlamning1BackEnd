package com.linnea.Inlamning1BackEnd.dto;

import com.linnea.Inlamning1BackEnd.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record MemberUpdatePatchDto(
        @Size(max = 50) String firstName,
        @Size(max = 50) String lastName,
        Address address,
        @Size(max = 50) @Email String email,
        @Size(max = 10) String phoneNumber,
        LocalDate dateOfBirth)  {

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
