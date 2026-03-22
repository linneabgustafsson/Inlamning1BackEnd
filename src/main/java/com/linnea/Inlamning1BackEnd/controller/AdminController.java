package com.linnea.Inlamning1BackEnd.controller;

import com.linnea.Inlamning1BackEnd.dto.MemberCreateDto;
import com.linnea.Inlamning1BackEnd.dto.MemberAllDataDto;
import com.linnea.Inlamning1BackEnd.dto.MemberUpdatePatchDto;
import com.linnea.Inlamning1BackEnd.dto.MemberUpdatePutDto;
import com.linnea.Inlamning1BackEnd.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/members")
public class AdminController {

    private final MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberAllDataDto> getMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberAllDataDto getMember(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public MemberAllDataDto updateMember(@PathVariable Long id,
                                         @RequestBody @Valid MemberUpdatePutDto memberUpdatePutDto) {
        return memberService.updatePutAdmin(id, memberUpdatePutDto);
    }

    @PatchMapping("/{id}")
    public MemberAllDataDto patchMember(@PathVariable Long id,
                                        @RequestBody @Valid MemberUpdatePatchDto memberUpdatePatchDto)  {
        return memberService.updatePatch(id, memberUpdatePatchDto);
    }

    @PostMapping
    public ResponseEntity<MemberAllDataDto> createMember(@RequestBody @Valid MemberCreateDto dto){
        MemberAllDataDto savedDTO = memberService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(savedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>("Medlemmen är nu borttagen.", HttpStatus.OK);
    }
}
