package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dao.CourseDao;
import tr.com.huseyinaydin.dto.CourseRequestDTO;
import tr.com.huseyinaydin.dto.CourseResponseDTO;
import tr.com.huseyinaydin.entity.CourseEntity;
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

    //H2, DERBY, AeroSpike -> Bellek bazlı veritabanları.

    private CourseDao courseDao;

    //kurs objesi oluşturma DB -> POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity = courseDao.save(courseEntity);
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
    }

    //tüm kursları veritabanından çek // GET
    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities = courseDao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(), false)
                .map(AppUtils::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    //gelen Id'ye göre filtre et //GET
    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new RuntimeException(courseId + " böyle bir kurs yok ey öğrenci hazretleri"));
        return AppUtils.mapEntityToDTO(courseEntity);
    }

    //Id'ye göre sil  //DELETE
    public void deleteCourse(int courseId) {
        courseDao.deleteById(courseId);
    }

    //kursu Id'ye göre güncelle //PUT
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        //zaten veritabanında var olan objeyi get(getirmek, edinmek) et
        CourseEntity existingCourseEntity = courseDao.findById(courseId).orElse(null);
        //zaten databasede var olan objeyi yeni değerleriyle değiştir ki güncellenebilsin
        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());
        //değişen verileri kaydet, kalıcı hale getir.
        CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
        return AppUtils.mapEntityToDTO(updatedCourseEntity);
    }
}