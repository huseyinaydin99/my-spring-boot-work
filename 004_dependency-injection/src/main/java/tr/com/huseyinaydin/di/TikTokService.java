package tr.com.huseyinaydin.di;

import org.springframework.stereotype.Component;

@Component
public class TikTokService implements SocialAppService{

    @Override
    public void getUserFeeds() {
        System.out.println("feeds loaded from TikTok...");
    }
}
