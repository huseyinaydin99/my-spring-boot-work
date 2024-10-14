package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
public class CourseService {

    private List<Course> courseDatabase = new ArrayList<>();

    //kurs objesi oluşturma DB -> POST
    public Course onboardNewCourse(Course course) {
        course.setCourseId(new Random().nextInt(3756));
        courseDatabase.add(course);
        return course;
    }

    //databaseden tüm kursları oku // GET
    public List<Course> viewAllCourses() {
        return courseDatabase;
    }

    //gelen Id'ye göre oku //GET
    public Course findByCourseId(Integer courseId) {
        return courseDatabase.stream()
                .filter(course -> course.getCourseId() == courseId)
                .findFirst().orElse(null);
    }

    //gelen Id'ye göre sil //DELETE
    public void deleteCourse(int courseId) {
        Course course = findByCourseId(courseId);
        courseDatabase.remove(course);
    }

    //kursu güncelle //PUT
    public Course updateCourse(int courseId, Course course) {
        Course existingCourse = findByCourseId(courseId);
        courseDatabase.set(courseDatabase.indexOf(existingCourse), course);
        return course;
    }
}