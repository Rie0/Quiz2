package org.twspring.quiz2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.quiz2.Api.ApiResponse;
import org.twspring.quiz2.Model.Student;
import org.twspring.quiz2.Service.StudentService;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudents() {
        if (studentService.getStudents().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No students found"));
        }
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @GetMapping("/get/by_name/{name}")
    public ResponseEntity getStudentsByName(@PathVariable String name) {
        if (studentService.getStudentByName(name)==null){
            return ResponseEntity.status(404).body(new ApiResponse("No students found"));
        }
        return ResponseEntity.status(200).body(studentService.getStudentByName(name));
    }

    @GetMapping("get/by_major/{major}")
    public ResponseEntity getStudentsByMajor(@PathVariable String major) {
        if (studentService.getStudentsByMajor(major).isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("No students found"));
        }
        return ResponseEntity.status(200).body(studentService.getStudentsByMajor(major));
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ApiResponse("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable int id,@Valid @RequestBody Student student, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdated = studentService.updateStudent(id, student);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
    }
}
