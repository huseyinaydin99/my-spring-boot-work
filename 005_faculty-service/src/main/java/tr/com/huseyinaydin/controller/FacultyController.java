package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.CourseRequestDTO;
import tr.com.huseyinaydin.dto.CourseResponseDTO;
import tr.com.huseyinaydin.dto.ServiceResponse;
import tr.com.huseyinaydin.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
@RequestMapping("/faculty-service")
public class FacultyController {

    @Autowired
    private FacultyService service;

    @PostMapping("/addNewCourse")
    public ServiceResponse<CourseResponseDTO> addNewCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return service.addNewCourseToDashboard(courseRequestDTO);
    }

    @GetMapping("/allCourses")
    public ServiceResponse<List<CourseResponseDTO>> viewAllCourses() {
        return service.fetchAllCourses();
    }

    @GetMapping("/getCourse/{id}")
    public ServiceResponse<CourseResponseDTO> getCourseById(@PathVariable Integer id) {
        return service.findCourseById(id);
    }

    @GetMapping("/getCourse/request")
    public ServiceResponse<CourseResponseDTO> getCourseByIdRequestParam(@RequestParam(required = false) Integer courseId) {
        return service.findCourseByIdUsingRequestParam(courseId);
    }

    @PutMapping("/updateCourse/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO requestDTO) {
        service.updateCourseInDashboard(courseId, requestDTO);
        return service.findCourseById(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public String deleteCourse(@PathVariable int courseId) {
        service.deleteCourseFromDashboard(courseId);
        return courseId + " Id'li kurs silindi.";
    }
}