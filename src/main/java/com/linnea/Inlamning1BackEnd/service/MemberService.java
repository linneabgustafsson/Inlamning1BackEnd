package com.linnea.Inlamning1BackEnd.service;

import com.linnea.Inlamning1BackEnd.dto.*;

import java.util.List;

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
