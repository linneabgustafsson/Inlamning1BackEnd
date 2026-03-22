package com.linnea.Inlamning1BackEnd.service;

import com.linnea.Inlamning1BackEnd.dto.*;
import com.linnea.Inlamning1BackEnd.entity.Member;
import com.linnea.Inlamning1BackEnd.exception.MemberNotAuthorizedException;
import com.linnea.Inlamning1BackEnd.exception.NotUniqueDateOfBirthException;
import com.linnea.Inlamning1BackEnd.exception.MemberNotFoundException;
import com.linnea.Inlamning1BackEnd.mapper.MemberMapper;
import com.linnea.Inlamning1BackEnd.repository.AppUserRepository;
import com.linnea.Inlamning1BackEnd.repository.MemberRepository;
import com.linnea.Inlamning1BackEnd.security.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AppUserRepository appUserRepository;

    public MemberServiceImpl(MemberRepository memberRepository, AppUserRepository appUserRepository) {

        this.memberRepository = memberRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberAllDataDto> findAll() {
        return memberRepository.findAll().stream()
                .map(MemberMapper::toMemberAllDataDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberWithoutDateOfBirthDto> findAllWithoutDateOfBirth() {
        return memberRepository.findAll().stream()
                .map(MemberMapper::toMemberWithoutBirthDateDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberAllDataDto findById(Long id) {
        return memberRepository.findById(id)
                .map(MemberMapper::toMemberAllDataDto)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Override
    @Transactional
    public MemberAllDataDto create(MemberCreateDto memberCreateDTO) {
        Member entity = MemberMapper.fromMemberCreateDto(memberCreateDTO);

        if(memberRepository.existsMemberByDateOfBirth(entity.getDateOfBirth())) {
            throw new NotUniqueDateOfBirthException();
        }

        Member saved = memberRepository.save(entity);
        return MemberMapper.toMemberAllDataDto(saved);
    }

    @Override
    @Transactional
    public MemberAllDataDto updatePutAdmin(Long id, MemberUpdatePutDto memberUpdatePutDto) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));

        if(memberRepository.existsMemberByDateOfBirthAndIdNot(memberUpdatePutDto.getDateOfBirth(), entity.getId())) {
            throw new NotUniqueDateOfBirthException();
        }

        MemberMapper.applyUpdatePutDto(entity, memberUpdatePutDto);
        Member updated = memberRepository.save(entity);
        return MemberMapper.toMemberAllDataDto(updated);
    }

    @Override
    @Transactional
    public MemberAllDataDto updatePutMember(Long id, MemberUpdatePutDto memberUpdatePutDto) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));

        String currentUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        AppUser appUser = appUserRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Det finns ingen medlem med avändarnamn " + currentUsername));

        if (!entity.getId().equals(appUser.getMember().getId())) {

           throw new MemberNotAuthorizedException();
        }

        if(memberRepository.existsMemberByDateOfBirthAndIdNot(memberUpdatePutDto.getDateOfBirth(), entity.getId())) {
            throw new NotUniqueDateOfBirthException();
        }

        MemberMapper.applyUpdatePutDto(entity, memberUpdatePutDto);
        Member updated = memberRepository.save(entity);
        return MemberMapper.toMemberAllDataDto(updated);
    }

    @Override
    @Transactional
    public MemberAllDataDto updatePatch(Long id, MemberUpdatePatchDto memberUpdatePatchDto) {
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));

     if(memberRepository.existsMemberByDateOfBirthAndIdNot(memberUpdatePatchDto.getDateOfBirth(), entity.getId())) {
            throw new NotUniqueDateOfBirthException();
        }

        MemberMapper.applyUpdatePatchDto(entity, memberUpdatePatchDto);
        Member updated = memberRepository.save(entity);
        return MemberMapper.toMemberAllDataDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        appUserRepository.deleteById(memberId);
        memberRepository.deleteById(memberId);
    }
}
