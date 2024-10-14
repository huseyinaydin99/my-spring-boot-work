package tr.com.huseyinaydin.di;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserApp {

//    @Autowired
//    @Qualifier("whatsappService")
//    private SocialAppService service;

    //setter injection

    //constructor injection

    private SocialAppService service;

    public UserApp(@Qualifier("whatsappService") SocialAppService service) {
        this.service = service;
    }

    public void loadUserFeeds(){
        service.getUserFeeds();
    }

}
