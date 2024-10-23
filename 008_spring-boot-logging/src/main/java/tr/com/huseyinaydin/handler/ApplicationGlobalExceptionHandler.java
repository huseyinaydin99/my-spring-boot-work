package tr.com.huseyinaydin.handler;

import tr.com.huseyinaydin.dto.ErrorDTO;
import tr.com.huseyinaydin.dto.ServiceResponse;
import tr.com.huseyinaydin.exception.CourseServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    //MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ServiceResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = new ErrorDTO(error.getField() + " : " + error.getDefaultMessage());
                    errorDTOList.add(errorDTO);
                });
        //serviceResponse.setStatus(HttpStatus.BAD_REQUEST);
        serviceResponse.setError(errorDTOList);
        return serviceResponse;
    }

    @ExceptionHandler(CourseServiceBusinessException.class)
    public ServiceResponse<?> handleServiceException(CourseServiceBusinessException exception) {
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        errorDTOList.add(new ErrorDTO(exception.getMessage()));
        serviceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        serviceResponse.setError(errorDTOList);
        return serviceResponse;
    }
}