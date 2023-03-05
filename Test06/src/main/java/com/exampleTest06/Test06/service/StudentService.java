package com.exampleTest06.Test06.service;

import com.exampleTest06.Test06.model.Student;
import com.exampleTest06.Test06.repository.IStudentRpository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


@Service
public class StudentService {
    @Autowired
    private IStudentRpository iStudentRpository;
    public JSONObject addStudent(JSONObject jsonObject) {

        JSONObject errorList = new JSONObject();
        Student newStudent = new Student();
        if(!jsonObject.has("firstName")){
            errorList.put("errorMessage","firstName is missing");
        }
        if(!jsonObject.has("lastName")){
            errorList.put("errorMessage","lastName is missing");
        }
        if(!jsonObject.has("age")){
            errorList.put("errorMessage","age is missing");
        }
        if(errorList.has("errorMessage")){
            return errorList;
        }
        if(!jsonObject.has("isUpdate")){
            newStudent.setFirstName(jsonObject.getString("firstName"));
            newStudent.setLastName(jsonObject.getString("lastName"));
            newStudent.setAge(jsonObject.getInt("age"));
            newStudent.setActive(true);
            newStudent.setAdmissionDate(new Date(System.currentTimeMillis()));
            iStudentRpository.save(newStudent);
        }
        return errorList;
    }

    public List<Student> getStudentByName(String name) {
        return iStudentRpository.findByFirstNameAndActive(name,true);
    }

    public List<Student> getStudentByLastName(String name) {
        return iStudentRpository.findByLastNameAndActive(name,true);
    }

    public List<Student> getStudentsByAgeFirstNameSorted(Integer age){
        return iStudentRpository.findByAgeAndActive(age,true);
    }

    public List<Student> getStudentsByName(String name){
        return iStudentRpository.findByName(name);
    }


    public boolean deleteStudentById(Integer id) {
        boolean isPresent = iStudentRpository.findById(id).isPresent();
        if(isPresent){
            iStudentRpository.deleteStudent(id,false);
            return true;
        }
        return false;

    }

    public JSONObject updateStudent(JSONObject jsonObject) {

        JSONObject errorList = new JSONObject();
        if(!jsonObject.has("id")){
            errorList.put("errorMessage","id is missing");
        }
        if(!jsonObject.has("firstName")){
            errorList.put("errorMessage","firstName is missing");
        }
        if(!jsonObject.has("lastName")){
            errorList.put("errorMessage","lastName is missing");
        }
        if(!jsonObject.has("age")){
            errorList.put("errorMessage","age is missing");
        }
        Integer id = jsonObject.getInt("id");
        List<Student> byIdAndActive = iStudentRpository.findByIdAndActive(id, true);
        if(byIdAndActive.isEmpty()){
            errorList.put("errorMessage","No student present with id - "+id);
        }
         if(errorList.has("errorMessage")){
             return errorList;
         }else{
             String firstName = jsonObject.getString("firstName");
             String lastName = jsonObject.getString("lastName");
             Integer age = jsonObject.getInt("age");
             Date admissionDate = new Date(System.currentTimeMillis());
             iStudentRpository.updateStudent(id,firstName,lastName,age,admissionDate);
             return errorList;
         }

    }

    public List<Student> getAllStudents() {
        return iStudentRpository.findAll();
    }
}
