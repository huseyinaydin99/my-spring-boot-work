package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.CourseRequestDTO;
import tr.com.huseyinaydin.dto.CourseResponseDTO;
import tr.com.huseyinaydin.dto.ServiceResponse;
import tr.com.huseyinaydin.service.CourseService;
import tr.com.huseyinaydin.util.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    //Logger log = LoggerFactory.getLogger(CourseController.class);

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        //isteği doğrulama
        //validateRequestPayload(courseRequestDTO);
        log.info("CourseController:addCourse istek JSON : {}", AppUtils.convertObjectToJson(courseRequestDTO));
        ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();
        CourseResponseDTO newCourse = courseService.onboardNewCourse(courseRequestDTO);
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(newCourse);
        log.info("CourseController:addCourse cevap JSON  : {}", AppUtils.convertObjectToJson(serviceResponse));
        return serviceResponse;
    }

    @GetMapping
    public ServiceResponse<List<CourseResponseDTO>> findALlCourse() {
        List<CourseResponseDTO> courseResponseDTOS = courseService.viewAllCourses();
        return new ServiceResponse<>(HttpStatus.OK, courseResponseDTOS);
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable Integer courseId) {
        CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
        return new ServiceResponse<>(HttpStatus.OK, responseDTO);
    }

    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam(required = false) Integer courseId) {
        CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
        return new ServiceResponse<>(HttpStatus.OK, responseDTO);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {
        log.info("CourseController:deleteCourse {} Id'li kurs silindi.", courseId);
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    //assignment
    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO courseRequestDTO) {
        //validate request
        // validateRequestPayload(courseRequestDTO);
        log.info("CourseController:updateCourse istek JSON: {} ve kurs Id: {}", AppUtils.convertObjectToJson(courseRequestDTO), courseId);
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, courseRequestDTO);
        ServiceResponse<CourseResponseDTO> response = new ServiceResponse<>(HttpStatus.OK, courseResponseDTO);
        log.info("CourseController:updateCourse cevap JSON : {}", AppUtils.convertObjectToJson(response));
        return response;
    }

    private void validateRequestPayload(CourseRequestDTO courseRequestDTO) {
        if (courseRequestDTO.getDuration() == null || courseRequestDTO.getDuration().isEmpty()) {
            throw new RuntimeException("Süre(duration) alanını girmeniz gerekiyor.");
        }
        if (courseRequestDTO.getFees() == 0) {
            throw new RuntimeException("Harç(fees) alanını girmeniz gerekiyor.");
        }
    }

    @GetMapping("/log")
    public String loggingLevel() {
        log.trace("iz mesajı");
        log.debug("hata ayıklama mesajı");
        log.info("bilgi mesajı");
        log.warn("uyarı mesajı");
        log.error("hata amesajı");
        return "log console'a yazıldı";
    }
}