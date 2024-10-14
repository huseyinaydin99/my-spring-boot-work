package tr.com.huseyinaydin.bean;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public class UserService {

    private SocialMediaService socialMediaService;

//    public SocialMediaService getSocialMediaService() {
//        return socialMediaService;
//    }
//
//    public void setSocialMediaService(SocialMediaService socialMediaService) {
//        this.socialMediaService = socialMediaService;
//    }


    public UserService(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    public void displayFeeds(){
        //yaklaşım 1
        //SocialMediaApp app = new SocialMediaApp();
        //FacebookService facebookService = new FacebookService();
        //InstagramService instagramService = new InstagramService();
        //app.getUserFeeds();

        //yaklaşım 2
        //SocialMediaService service = new FacebookService();
        //service.getUserFeeds();

        //yaklaşım 3 (dependency injection/nesne takma adı IoC Container'den gibi düşünülebilir.)
        //SocialMediaService instance = SocialMediaServiceFactory.getInstance("instagram");
        //instance.getUserFeeds();

        socialMediaService.getUserFeeds();
    }

    //IOC
    public static void main(String[] args) {
        UserService userService = new UserService(new InstagramService());
        //userService.setSocialMediaService(new FacebookService());
        userService.displayFeeds();
    }
}