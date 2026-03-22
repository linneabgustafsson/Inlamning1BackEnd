package com.linnea.Inlamning1BackEnd.mapper;

import com.linnea.Inlamning1BackEnd.dto.*;
import com.linnea.Inlamning1BackEnd.entity.Address;
import com.linnea.Inlamning1BackEnd.entity.Member;

public final class MemberMapper {

    private MemberMapper() {}

    public static MemberAllDataDto toMemberAllDataDto(Member member) {
        return  new MemberAllDataDto(member.getId(),
                member.getFirstName(), member.getLastName(),
                member.getAddress(),
                member.getEmail(), member.getPhoneNumber(),
                member.getDateOfBirth());
    }

    public static MemberWithoutDateOfBirthDto toMemberWithoutBirthDateDto(Member member) {
        return new MemberWithoutDateOfBirthDto(member.getId(),
                member.getFirstName(), member.getLastName(),
                member.getAddress(),
                member.getEmail(), member.getPhoneNumber());
    }

    public static Member fromMemberCreateDto(MemberCreateDto memberCreateDTO) {
        return new Member(memberCreateDTO.firstName(), memberCreateDTO.lastName(),
                memberCreateDTO.address(), memberCreateDTO.email(),
                memberCreateDTO.phoneNumber(), memberCreateDTO.dateOfBirth());
    }

    public static void applyUpdatePutDto(Member member, MemberUpdatePutDto memberUpdatePutDto) {
        member.setFirstName(memberUpdatePutDto.firstName());
        member.setLastName(memberUpdatePutDto.lastName());
        member.setAddress(memberUpdatePutDto.address());
        member.setEmail(memberUpdatePutDto.email());
        member.setPhoneNumber(memberUpdatePutDto.phoneNumber());
        member.setDateOfBirth(memberUpdatePutDto.dateOfBirth());
    }

    public static void applyUpdatePatchDto(Member member, MemberUpdatePatchDto memberUpdatePatchDto) {

        if(memberUpdatePatchDto.firstName() != null)   {
            member.setFirstName(memberUpdatePatchDto.firstName());
        }

        if(memberUpdatePatchDto.lastName() != null)   {
            member.setLastName(memberUpdatePatchDto.lastName());
        }

        if(memberUpdatePatchDto.address() != null)   {

           Address address = member.getAddress();

           if(memberUpdatePatchDto.address().getStreet() != null)   {
               address.setStreet(memberUpdatePatchDto.address().getStreet());
           }

           if(memberUpdatePatchDto.address().getPostalCode() != null)   {
               address.setPostalCode(memberUpdatePatchDto.address().getPostalCode());
           }

           if(memberUpdatePatchDto.address().getCity() != null)   {
               address.setCity(memberUpdatePatchDto.address().getCity());
           }
        }

        if(memberUpdatePatchDto.email() != null)   {
            member.setEmail(memberUpdatePatchDto.email());
        }

        if(memberUpdatePatchDto.phoneNumber() != null)   {
            member.setPhoneNumber(memberUpdatePatchDto.phoneNumber());
        }

        if(memberUpdatePatchDto.dateOfBirth() != null)   {
            member.setDateOfBirth(memberUpdatePatchDto.dateOfBirth());
        }
    }
}
