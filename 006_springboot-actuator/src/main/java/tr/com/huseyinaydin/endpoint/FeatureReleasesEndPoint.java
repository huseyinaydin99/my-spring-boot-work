package tr.com.huseyinaydin.endpoint;

import tr.com.huseyinaydin.dto.ProdRelease;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Component
@Endpoint(id = "releases")
public class FeatureReleasesEndPoint {

    List<ProdRelease> prodReleases = new ArrayList<>();

    @WriteOperation //POST
    public void addNewReleaseInfo(@Selector String crq, @Selector String releaseDt, String features) {
        ProdRelease release = ProdRelease.builder().crq(crq)
                .releaseDt(releaseDt)
                .features(Arrays.stream(features.split(",")).collect(Collectors.toList())).build();
        prodReleases.add(release);
    }

    @ReadOperation //GET
    public List<ProdRelease> getAllReleases() {
        return prodReleases;
    }

    @ReadOperation //
    public ProdRelease getReleaseByCRQ(@Selector String crq) {
        return prodReleases.stream().filter(prodRelease -> prodRelease.getCrq().equals(crq))
                .findAny().get();
    }

    @DeleteOperation //DELETE
    public void deleteRelease(@Selector String crq) {
        prodReleases.remove(getReleaseByCRQ(crq));
    }
}