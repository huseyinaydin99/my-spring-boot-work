package tr.com.huseyinaydin.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Data
@NoArgsConstructor
public class ProjectEngineerResponseBO {

    private String projectName;
    private String projectCode;
    private String engineerName;
    private String email;
    private String temp;
    private String loc;

    public ProjectEngineerResponseBO(String projectName, String projectCode, String engineerName, String email) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.engineerName = engineerName;
        this.email = email;
    }

    public ProjectEngineerResponseBO(String projectName, String engineerName) {
        this.projectName = projectName;
        this.engineerName = engineerName;
    }
}