package com.linnea.Inlamning1BackEnd.service;

import com.linnea.Inlamning1BackEnd.dto.*;

import java.util.List;

//Här i interfacet används DTO-objekt.
//Det är en separation av metoderna i repository.
//Klassen MemberRepository riktar sig mot entitetsklassen.
//Den här klassen, MemberService, riktar sig mot databasen.
//HAR JAG FATTAT DETTA RÄTT?

public interface MemberService {

    List<MemberAllDataDto> findAll();
    List<MemberWithoutDateOfBirthDto> findAllWithoutDateOfBirth();
    MemberAllDataDto findById(Long id);
    MemberAllDataDto create(MemberCreateDto memberCreateDTO);
    MemberAllDataDto updatePutAdmin(Long id, MemberUpdatePutDto memberUpdatePutDto);
    MemberAllDataDto updatePutMember(Long id, MemberUpdatePutDto memberUpdatePutDto);
    MemberAllDataDto updatePatch(Long id, MemberUpdatePatchDto memberUpdatePatchDto);
    void delete(Long id);
}
