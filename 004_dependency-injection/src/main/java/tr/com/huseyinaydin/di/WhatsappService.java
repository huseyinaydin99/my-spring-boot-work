package tr.com.huseyinaydin.di;

import org.springframework.stereotype.Service;

@Service
public class WhatsappService implements SocialAppService{

    @Override
    public void getUserFeeds() {
        System.out.println("load feeds from whatsapp");
    }
}
