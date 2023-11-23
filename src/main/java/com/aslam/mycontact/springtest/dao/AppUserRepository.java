package com.aslam.mycontact.springtest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Repository
public interface AppUserRepository extends JpaRepository<AppUsers,Integer> {

    public Optional<AppUsers>  findByusername(String userName);

}
