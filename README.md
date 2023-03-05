# Test06
## Frameworks and language used:
-  Spring Boot
-  Java
-  SQL database
## Data Flow


* **Model** :
  There is a one Model class in this application.
    1. Student Model Class.


* **Controller** :

  There is One Controller class in this application.

    1. Student Controller Class.



**Functions/Endpoints used in User Controller Class.**
- To add student
- http://localhost:8080/api/v1/student/addStudent


- To get student by age
- http://localhost:8080/api/v1/student/addStudent


- To get student by first name
- http://localhost:8080/api/v1/student/getByFirstName?name=Sonu


- To get student by last name
- http://localhost:8080/api/v1/student/getByLastName?name=Tupkar


- To update student details
- http://localhost:8080/api/v1/student/updateStudentDetails


- To get all students
- http://localhost:8080/api/v1/student/getAllStudents


- To delete student by id
- http://localhost:8080/api/v1/student/deleteById?id=1


* **Service** :

    * In this application there is one service class .
        * StudntService Service Class
       


* **Util/Validation** :
    * In this application we are validating student data.
    * The appropriate suggestions about changes in data is given by this methods.
       

* **Repository**

    * In this application there is one Repository class.
        * IStudentRepository
    

* **Database**

    * I have used SQL Database to store the data.

## Project Summary

In this application by using various RestApi we can add Student, we can associate multiple
attributes to a perticular Student.
Fetching data of student based on student first name and last name as well as age is possible.
We are using query by method and native queries to intract with the databse.
We are using soft deleting concept here.





  













  