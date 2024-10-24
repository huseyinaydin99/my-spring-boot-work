package tr.com.huseyinaydin.util;

import tr.com.huseyinaydin.dto.CourseRequestDTO;
import tr.com.huseyinaydin.dto.CourseResponseDTO;
import tr.com.huseyinaydin.entity.CourseEntity;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public class AppUtils {

    //DTO -> ENTITY
    public static CourseEntity mapDTOToEntity(CourseRequestDTO courseRequestDTO){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseRequestDTO.getName());
        courseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        courseEntity.setDuration(courseRequestDTO.getDuration());
        courseEntity.setStartDate(courseRequestDTO.getStartDate());
        courseEntity.setCourseType(courseRequestDTO.getCourseType());
        courseEntity.setFees(courseRequestDTO.getFees());
        courseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        courseEntity.setDescription(courseRequestDTO.getDescription());
        courseEntity.setEmailId(courseRequestDTO.getEmailId());
        courseEntity.setContact(courseRequestDTO.getContact());
        return courseEntity;
    }

    //ENTITY -> DTO
    public static CourseResponseDTO mapEntityToDTO(CourseEntity courseEntity){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseId(courseEntity.getCourseId());
        courseResponseDTO.setName(courseEntity.getName());
        courseResponseDTO.setTrainerName(courseEntity.getTrainerName());
        courseResponseDTO.setDuration(courseEntity.getDuration());
        courseResponseDTO.setStartDate(courseEntity.getStartDate());
        courseResponseDTO.setCourseType(courseEntity.getCourseType());
        courseResponseDTO.setFees(courseEntity.getFees());
        courseResponseDTO.setCertificateAvailable(courseEntity.isCertificateAvailable());
        courseResponseDTO.setDescription(courseEntity.getDescription());
        courseResponseDTO.setEmailId(courseEntity.getEmailId());
        courseResponseDTO.setContact(courseEntity.getContact());
        return courseResponseDTO;
    }
}