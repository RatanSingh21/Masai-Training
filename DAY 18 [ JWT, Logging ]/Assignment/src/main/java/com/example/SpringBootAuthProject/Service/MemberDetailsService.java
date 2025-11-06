package com.example.SpringBootAuthProject.Service;

import com.example.SpringBootAuthProject.Entity.Member;
import com.example.SpringBootAuthProject.Entity.MemberDetails;
import com.example.SpringBootAuthProject.Repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = repo.findByUserId(username);
        if (member==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new MemberDetails(member);
    }
}
