package com.aslam.mycontact.springtest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUsers,Integer> {

    public Optional<AppUsers>  findByUsername(String userName);

}
