package tr.com.huseyinaydin.bean;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public class SocialMediaServiceFactory {

    public static SocialMediaService getInstance(String socialMediaType){
        if(socialMediaType.equals("facebook")){
            return new FacebookService();
        }if(socialMediaType.equals("instagram")){
            return new InstagramService();
        }
        return null;
    }
}