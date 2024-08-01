package org.twspring.quiz2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.quiz2.Api.ApiResponse;
import org.twspring.quiz2.Model.Teacher;
import org.twspring.quiz2.Service.TeacherService;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity getTeachers() {
        if (teacherService.getTeachers().isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No teachers found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @GetMapping("/get/by_id/{id}")
    public ResponseEntity getTeacher(@PathVariable int id) {

        if(teacherService.getTeacher(id)==null){
            return ResponseEntity.status(404).body(new ApiResponse("No teacher found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeacher(id));
    }

    @GetMapping("/get/by_min_salary/{salary}")
    public ResponseEntity getTeachersByMinSalary(@PathVariable double salary) {
        if(teacherService.getTeachersByMinSalary(salary).isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No teachers found"));
        }
        return ResponseEntity.status(200).body(teacherService.getTeachersByMinSalary(salary));
    }

    //
    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher, Errors errors) {
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body("Teacher added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable int id, @Valid @RequestBody Teacher teacher, Errors errors) {
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = teacherService.updateTeacher(id,teacher);
        if(isUpdated){
            return ResponseEntity.status(201).body("Teacher updated successfully");
        }
        return ResponseEntity.status(404).body(new ApiResponse("No teacher found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable int id) {
        boolean isDeleted = teacherService.deleteTeacher(id);
        if(isDeleted){
            return ResponseEntity.status(201).body("Teacher deleted successfully");
        }
        return ResponseEntity.status(404).body(new ApiResponse("No teacher found"));
    }
}
