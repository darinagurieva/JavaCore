package stream_api;

import java.util.Comparator;
import java.util.List;

public class Solution {

    public List<Course> getUniqueCourses(List<Student> studentList) {

        return studentList.stream()
            .map(Student::getAllCourses)
            .flatMap(List::stream)
            .distinct()
            .toList();
    }

    public List<Student> getTopOfCurios(List<Student> studentList) {

        return studentList.stream()
            .sorted(Comparator.comparingInt(student -> student.getAllCourses().size()))
            .limit(3)
            .toList();
    }

    public List<Student> filterByCourse(List<Student> studentList, Course course) {

        return studentList.stream()
            .filter(student -> student.getAllCourses().contains(course))
            .toList();
    }
}
