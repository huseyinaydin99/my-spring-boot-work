package tr.com.huseyinaydin.bean;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public class FacebookService implements SocialMediaService{

    @Override
    public void getUserFeeds() {
        System.out.println("Facebook kullanıcı paylaşımları yükleniyor. Şakacıktan (:");
    }
}