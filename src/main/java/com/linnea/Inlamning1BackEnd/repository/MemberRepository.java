package com.linnea.Inlamning1BackEnd.repository;

import com.linnea.Inlamning1BackEnd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberByDateOfBirth(LocalDate dateOfBirth);
    boolean existsMemberByDateOfBirthAndIdNot(LocalDate dateOfBirth, Long id);
}
