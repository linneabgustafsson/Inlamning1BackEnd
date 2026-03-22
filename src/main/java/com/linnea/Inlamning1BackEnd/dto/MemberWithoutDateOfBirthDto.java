package com.linnea.Inlamning1BackEnd.dto;

import com.linnea.Inlamning1BackEnd.entity.Address;

public record MemberWithoutDateOfBirthDto(Long id,
                                          String firstName, String lastName,
                                          Address address,
                                          String email, String phoneNumber) {
}
