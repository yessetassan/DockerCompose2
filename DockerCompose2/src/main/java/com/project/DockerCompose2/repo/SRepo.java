package com.project.DockerCompose2.repo;


import com.project.DockerCompose2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SRepo extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.id = ?1")
    Optional<Student> findById(Integer id);
}
