package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CourseRequestDTO;
import tr.com.huseyinaydin.dto.CourseResponseDTO;
import tr.com.huseyinaydin.dto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
public class FacultyService {

    private final static String BASE_URL = "http://localhost:8080/";

    @Autowired
    private RestTemplate restTemplate;

    public ServiceResponse<CourseResponseDTO> addNewCourseToDashboard(CourseRequestDTO courseRequestDTO) {
        return restTemplate.postForObject(BASE_URL + "course", courseRequestDTO, ServiceResponse.class);
    }

    public ServiceResponse<List<CourseResponseDTO>> fetchAllCourses() {
        return restTemplate.getForObject(BASE_URL + "course", ServiceResponse.class);
    }

    public ServiceResponse<CourseResponseDTO> findCourseById(Integer courseId) {
        return restTemplate.getForObject(BASE_URL + "course/search/path/" + courseId, ServiceResponse.class);
    }

    public ServiceResponse<CourseResponseDTO> findCourseByIdUsingRequestParam(Integer courseId) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("courseId", courseId);
        return restTemplate.getForObject(BASE_URL + "course/search/request/?courseId={courseId}", ServiceResponse.class, requestMap);
    }

    public void updateCourseInDashboard(int courseId, CourseRequestDTO courseRequestDTO) {
        restTemplate.put(BASE_URL + "course/" + courseId, courseRequestDTO);
    }

    public void deleteCourseFromDashboard(int courseId) {
        restTemplate.delete(BASE_URL + "course/" + courseId);
    }
}