package com.project.DockerCompose2.rest;

import com.project.DockerCompose2.entity.Teacher;
import com.project.DockerCompose2.service.TService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
public class RestTeacher {

    private final TService service;

    @Autowired
    public RestTeacher(TService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> allTeachers = service.all();
        if (allTeachers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allTeachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id) {
        Optional<Teacher> teacher = service.findById(id);
        return teacher.map(t -> ResponseEntity.ok().body(t))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher) {
        service.save(teacher);
        return ResponseEntity.ok("Teacher created successfully");
    }

}