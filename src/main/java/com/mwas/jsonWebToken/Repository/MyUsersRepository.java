package com.mwas.jsonWebToken.Repository;

import com.mwas.jsonWebToken.Models.MyUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUsersRepository extends JpaRepository<MyUsers,Integer> {

    Optional<MyUsers> findByUsername(String username);
}
