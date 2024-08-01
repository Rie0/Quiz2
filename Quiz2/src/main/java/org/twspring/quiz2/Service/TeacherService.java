package org.twspring.quiz2.Service;

import org.springframework.stereotype.Service;
import org.twspring.quiz2.Model.Teacher;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    //get all
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    //get by id
    public Teacher getTeacher(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    //get teachers by min salary

    public ArrayList<Teacher> getTeachersByMinSalary(double salary) {
        ArrayList<Teacher> foundTeachers = new ArrayList<Teacher>();
        for (Teacher teacher : teachers) {
            if (teacher.getSalary() >= salary) {
                foundTeachers.add(teacher);
            }
        }
        return foundTeachers;
    }

    //post
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    //update
    public boolean updateTeacher(int id,Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    //delete
    public boolean deleteTeacher(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }
}
