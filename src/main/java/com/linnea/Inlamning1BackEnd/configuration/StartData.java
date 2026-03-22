package com.linnea.Inlamning1BackEnd.configuration;

import com.linnea.Inlamning1BackEnd.entity.Address;
import com.linnea.Inlamning1BackEnd.entity.Member;
import com.linnea.Inlamning1BackEnd.repository.AddressRepository;
import com.linnea.Inlamning1BackEnd.repository.AppUserRepository;
import com.linnea.Inlamning1BackEnd.repository.MemberRepository;
import com.linnea.Inlamning1BackEnd.security.AppUser;
import com.linnea.Inlamning1BackEnd.security.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class StartData implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;
    private final AppUserRepository appUserRepository;


    public StartData(AddressRepository addressRepository, MemberRepository memberRepository,
                     AppUserRepository appUserRepository) {
        this.addressRepository = addressRepository;
        this.memberRepository = memberRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Address> addressList = new ArrayList<>();

        Address address1 = new Address("Gula vägen 2", "857 41", "Sundsvall");
        addressList.add(address1);

        Address address2 = new Address("Gröna vägen 58", "855 30","Matfors");
        addressList.add(address2);

        Address address3 = new Address("Lila vägen 177", "855 92", "Kovland");
        addressList.add(address3);

        addressRepository.saveAll(addressList);

        //**********************************************************
        List<Member> membersList = new ArrayList<>();

        Member member1 = new Member("Eja", "Bylund", address1, "eja@gmail.com",
                        "0731187290", LocalDate.of(1975, 04, 18));
        membersList.add(member1);

        Member member2 = new Member("Emma", "Hagman", address1, "emma@gmail.com",
                        "0705579250", LocalDate.of(1965, 01, 16));
        membersList.add(member2);

        Member member3 = new Member("Sara", "Stenlund", address2, "sara@gmail.com",
                        "0701129870", LocalDate.of(1995, 11,05));
        membersList.add(member3);

        Member member4 = new Member("Jennie", "Larsson", address3, "jennie@hotmail.com",
                        "0736685027", LocalDate.of(2005, 07, 15));
        membersList.add(member4);

        Member member5 = new Member("Lars", "Svensson", address3, "lasse@hotmail.com",
                        "0769925870", LocalDate.of(2006, 12, 24));
        membersList.add(member5);

        memberRepository.saveAll(membersList);

        //**********************************************************
        List<AppUser> appUserList = new ArrayList<>();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        AppUser appUser1 = new AppUser("Eja_user", passwordEncoder.encode("Eja_password"),
                Set.of(Role.ADMIN, Role.MEMBER), member1);
        appUserList.add(appUser1);

        AppUser appUser2 = new AppUser("Emma_user", passwordEncoder.encode("Emma_password"),
                Set.of(Role.MEMBER), member2);
        appUserList.add(appUser2);

        AppUser appUser4 = new AppUser("Jennie_user", passwordEncoder.encode("Jennie_password"),
                Set.of(Role.ADMIN, Role.MEMBER), member4);
        appUserList.add(appUser4);

        AppUser appUser3 = new AppUser("Sara_user", passwordEncoder.encode("Sara_password"),
                Set.of(Role.MEMBER), member3);
        appUserList.add(appUser3);

        AppUser appUser5 = new AppUser("Lars_user", passwordEncoder.encode("Lars_password"),
                Set.of(Role.MEMBER), member5);
        appUserList.add(appUser5);

        appUserRepository.saveAll(appUserList);
    }
}
