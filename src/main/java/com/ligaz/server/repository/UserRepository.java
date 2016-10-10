package com.ligaz.server.repository;

import com.ligaz.server.entity.Role;
import com.ligaz.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findUsersByRole(Role role);
}
