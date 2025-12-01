package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    /**
     * Initialize the list of students only once to be called
     * by all the endpoints
     */
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/students" to return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    /**
     * Ensure that the variable studentId matches the parameter
     * name in the method
     * @param studentId
     * @return
     */
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if (studentId >= theStudents.size() || (studentId < 0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    /**
     * Add an exception handler to catch StudentNotFoundException
     * @param exc
     * @return ResponseEntity<StudentErrorResponse
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
