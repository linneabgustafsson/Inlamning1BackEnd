package com.linnea.Inlamning1BackEnd.controller;

import com.linnea.Inlamning1BackEnd.dto.MemberAllDataDto;
import com.linnea.Inlamning1BackEnd.dto.MemberUpdatePutDto;
import com.linnea.Inlamning1BackEnd.dto.MemberWithoutDateOfBirthDto;
import com.linnea.Inlamning1BackEnd.service.MemberService;
import com.linnea.Inlamning1BackEnd.service.MemberServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypages/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberWithoutDateOfBirthDto> getMembers() {
        return memberService.findAllWithoutDateOfBirth();
    }

    @PutMapping("/{id}")
    public MemberAllDataDto updateMember(@PathVariable Long id,
                                         @RequestBody @Valid MemberUpdatePutDto memberUpdatePutDto) {

        return memberService.updatePutMember(id, memberUpdatePutDto);
    }
}
