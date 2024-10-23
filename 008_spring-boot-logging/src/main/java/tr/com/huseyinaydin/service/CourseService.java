package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dao.CourseDao;
import tr.com.huseyinaydin.dto.CourseRequestDTO;
import tr.com.huseyinaydin.dto.CourseResponseDTO;
import tr.com.huseyinaydin.entity.CourseEntity;
import tr.com.huseyinaydin.exception.CourseServiceBusinessException;
import tr.com.huseyinaydin.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CourseService {

    //H2,DERBY , AeroSpike -> bunlar bellek bazlı database'dir.

    @Autowired
    private CourseDao courseDao;

    Logger log = LoggerFactory.getLogger(CourseService.class);

    //veritabanında gelen DTO objesini entity'e çevirip save(insert) et. -> POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity = null;
        log.info("CourseService::onboardNewCourse metot çalıştırma başladı.");
        try {
            entity = courseDao.save(courseEntity);
            log.debug("Veritabanındaki kurs nesnesinin son hali:  {} ", AppUtils.convertObjectToJson(entity));
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
            log.debug("CourseService::onboardNewCourse kurs cevap veri transfer objesi {} ", AppUtils.convertObjectToJson(courseResponseDTO));
            log.info("CourseService::onboardNewCourse metot çalıştırma bitti.");
            return courseResponseDTO;
        } catch (Exception exception) {
            log.error("CourseService::onboardNewCourse kurs nesnesi database'de kalıcı hale getirilirken hata oldu.");
            throw new CourseServiceBusinessException("onboardNewCourse servis metodu hata verdi..");
        }
    }

    //veritabanından tüm kursları yükle.  // GET
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

    //kurs'u Id'ye göre getir. //GET
    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(() -> new CourseServiceBusinessException(courseId + " Id'li kurs bulunamadı."));
        return AppUtils.mapEntityToDTO(courseEntity);
    }

    //kursu Id'ye göre sil.  //DELETE
    public void deleteCourse(int courseId) {
        log.info("CourseService::deleteCourse metot çalıştırma başladı. ..");
        try {
            log.debug("CourseService::deleteCourse metot parametresi(girdisi) {}", courseId);
            courseDao.deleteById(courseId);
        } catch (Exception ex) {
            log.error("CourseService::deleteCourse kurs nesnesi silinirken hata oldu: {} ", ex.getMessage());
            throw new CourseServiceBusinessException("deleteCourse servis metodu hata verdi.." + ex.getMessage());
        }
        log.info("CourseService::deleteCourse metot çalıştırma bitti..");
    }

    //gelen Id'ye göre gelen kurs bilgilerini güncelle //PUT
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        log.info("CourseService::updateCourse metot çalıştırma başladı..");
        try {
            //zaten var olan objeyi get et.
            log.debug("CourseService::updateCourse kurs DTO JSON {} ve kurs Id: {} ", AppUtils.convertObjectToJson(courseRequestDTO), courseId);
            CourseEntity existingCourseEntity = courseDao.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Kurs veritabanında mevcut değil. " + courseId));
            log.debug("CourseService::updateCourse veritabanında var olan kurs kaydı JSON {} ", AppUtils.convertObjectToJson(existingCourseEntity));
            //var olan objeyi yeni değerleriyle değiştir.
            existingCourseEntity.setName(courseRequestDTO.getName());
            existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
            existingCourseEntity.setDuration(courseRequestDTO.getDuration());
            existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
            existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
            existingCourseEntity.setFees(courseRequestDTO.getFees());
            existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
            existingCourseEntity.setDescription(courseRequestDTO.getDescription());
            //değişen değerleri save(update) et.
            CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(updatedCourseEntity);
            log.debug("CourseService::updateCourse kurs objesinin güncellenmiş JSON hali: {} ", AppUtils.convertObjectToJson(courseResponseDTO));
            log.info("CourseService::updateCourse method execution ended ..");
            return courseResponseDTO;
        } catch (Exception ex) {
            log.error("CourseService::updateCourse kurs nesnesi güncellenirken hata oluştur: {} ", ex.getMessage());
            throw new CourseServiceBusinessException("updateCourse servis metodu hata verdi." + ex.getMessage());
        }
    }
}