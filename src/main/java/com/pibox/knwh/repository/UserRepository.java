package com.pibox.knwh.repository;

import com.pibox.knwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    List<User> findAllByCompanyId(Long companyId);

    @Query("" +
            "SELECT CASE WHEN COUNT(p) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM User p " +
            "WHERE p.email = ?1")
    Boolean selectExistsEmail(String email);
}
