package org.twspring.quiz2.Service;


import org.springframework.stereotype.Service;
import org.twspring.quiz2.Model.Student;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<Student>();

    //Get All
    public ArrayList<Student> getStudents() {
        return students;
    }
    //Get a student by name
    public Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    //Get students by major
    public ArrayList<Student> getStudentsByMajor(String major) {
        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                foundStudents.add(student);
            }
        }
        return foundStudents;
    }

    //Add
    public void addStudent(Student student) {
        students.add(student);
    }
    //Update
    public boolean updateStudent(int id ,Student student) {
       for (int i = 0; i < students.size(); i++) {
           if (students.get(i).getId() == id) {
               students.set(i, student);
               return true;
           }
       }
       return false;
    }

    //delete
    public boolean deleteStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }


}
