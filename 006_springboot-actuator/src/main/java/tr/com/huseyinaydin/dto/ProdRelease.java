package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdRelease {

    private String crq;
    private String releaseDt;
    private List<String> features;
}