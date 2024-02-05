package com.project.DockerCompose2.rest;


import com.project.DockerCompose2.entity.Student;
import com.project.DockerCompose2.service.SService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class RestStudent {

    private final SService service;

    @Autowired
    public RestStudent(SService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> all() {

        List<Student> all = service.all();

        if (all.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> all2(@PathVariable Integer id) {

        Optional<Student> student = service.findById(id);

        return student.map(s -> ResponseEntity.ok().body(s))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping()
    public ResponseEntity<String> all2(@RequestBody Student student) {

        service.save(student);
        return ResponseEntity.ok().body("Student created successfully");
    }





}
