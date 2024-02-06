package com.project.DockerCompose2.rest;

import com.project.DockerCompose2.entity.Student;
import com.project.DockerCompose2.entity.Teacher;
import com.project.DockerCompose2.service.SService;
import com.project.DockerCompose2.service.TService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/student_teacher")
public class RestStudentTeacher {

    private final SService sService;
    private final TService tService;

    private Optional<Student> studentOpt;
    private Optional<Teacher> teacherOpt;


    @Autowired
    public RestStudentTeacher(SService sService, TService tService) {
        this.sService = sService;
        this.tService = tService;
    }

    @GetMapping()
    public String message() {
        return "GG";
    }

    @PostMapping("/{s_id}/{t_id}")
    public ResponseEntity<String> save(@PathVariable(name = "s_id") Integer s_id,
                                       @PathVariable(name = "t_id") Integer t_id) {
        studentOpt = sService.findById(s_id);
        teacherOpt = tService.findById(t_id);

        if (absent())
            return ResponseEntity.badRequest().body("Either student or teacher doesn't exist.");

        Student student = studentOpt.get();
        Teacher teacher = teacherOpt.get();

        Set<Teacher> teachers = student.getTeachers();
        if (!teachers.contains(teacher)) {
            teachers.add(teacher);
            student.setTeachers(teachers);
            sService.save(student);
            return ResponseEntity.ok().body("Association successfully created.");
        } else {
            return ResponseEntity.badRequest().body("Association already exists.");
        }
    }

    @PostMapping("/rm/{s_id}/{t_id}")
    public ResponseEntity<String> rm(@PathVariable(name = "s_id") Integer s_id,
                                       @PathVariable(name = "t_id") Integer t_id) {
        studentOpt = sService.findById(s_id);
        teacherOpt = tService.findById(t_id);

        if (absent())
            return ResponseEntity.badRequest().body("Either student or teacher doesn't exist.");

        Student student = studentOpt.get();
        Teacher teacher = teacherOpt.get();

        Set<Teacher> teachers = student.getTeachers();
        teachers.remove(teacher);
        student.setTeachers(teachers);
        sService.save(student);

        return ResponseEntity.ok().body("Removed successfully !");
    }

    public boolean absent(){
        return !studentOpt.isPresent() || !teacherOpt.isPresent();
    }
}
