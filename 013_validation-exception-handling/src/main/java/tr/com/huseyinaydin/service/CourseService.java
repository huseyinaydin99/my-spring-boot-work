package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dao.CourseDao;
import tr.com.huseyinaydin.dto.CourseRequestDTO;
import tr.com.huseyinaydin.dto.CourseResponseDTO;
import tr.com.huseyinaydin.entity.CourseEntity;
import tr.com.huseyinaydin.exception.CourseServiceBusinessException;
import tr.com.huseyinaydin.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
@AllArgsConstructor
public class CourseService {

    private CourseDao courseDao;

    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity = null;
        try {
            entity = courseDao.save(courseEntity);
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
            return courseResponseDTO;
        } catch (Exception exception) {
            throw new CourseServiceBusinessException("onboardNewCourse servis metodu hata verdi..");
        }
    }

    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities = null;
        try {
            courseEntities = courseDao.findAll();
            return StreamSupport.stream(courseEntities.spliterator(), false)
                    .map(AppUtils::mapEntityToDTO)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new CourseServiceBusinessException("viewAllCourses servis metodu hata verdi..");
        }
    }

    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new CourseServiceBusinessException(courseId + " Id'li kurs bulunamadı."));
        return AppUtils.mapEntityToDTO(courseEntity);
    }

    public void deleteCourse(int courseId) {
        courseDao.deleteById(courseId);
    }

    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        CourseEntity existingCourseEntity = courseDao.findById(courseId).orElse(null);

        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());

        CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
        return AppUtils.mapEntityToDTO(updatedCourseEntity);
    }
}