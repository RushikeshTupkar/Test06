package com.exampleTest06.Test06.controller;

import com.exampleTest06.Test06.model.Student;
import com.exampleTest06.Test06.service.StudentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody String student){
        JSONObject jsonObject = new JSONObject(student);
        JSONObject errorList = studentService.addStudent(jsonObject);
        if(errorList.has("errorMessage")){
            return new ResponseEntity<>(errorList.toString(), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Student saved", HttpStatus.CREATED);
        }
    }
    @GetMapping("/getByFirstName")
    public ResponseEntity<String> getStudent(@RequestParam("name") String name){
        List<Student> studentByName = studentService.getStudentByName(name);
        if(studentByName.isEmpty()){
            return new ResponseEntity<>("No student with firstName like - "+name, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(studentByName.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByLastName")
    public  ResponseEntity<String> getStudentByLastName(@RequestParam("name") String lastName){
        List<Student> studentByLastName = studentService.getStudentByLastName(lastName);
        if(studentByLastName.isEmpty()){
            return new ResponseEntity<>("No student with lasstName like - "+lastName, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(studentByLastName.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getByAge")
    public    ResponseEntity<String> getStudentByAge(@RequestParam("age") Integer age){
        List<Student> studentsByAge = studentService.getStudentsByAgeFirstNameSorted(age);
        if(studentsByAge.isEmpty()){
            return new ResponseEntity<>("No student with age like - "+age, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(studentsByAge.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam("id") Integer id){
        if(studentService.deleteStudentById(id)){
            return new ResponseEntity<>("Student deleted with id - "+id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No student is present with id - "+id,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateStudentDetails")
    public ResponseEntity<String> updateStudent(@RequestBody String student){
        JSONObject jsonObject = new JSONObject(student);
        JSONObject errorList = studentService.updateStudent(jsonObject);
        if(errorList.has("errorMessage")){
            return new ResponseEntity<>(errorList.toString(), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Student updated", HttpStatus.CREATED);
        }
    }
    
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }


}
