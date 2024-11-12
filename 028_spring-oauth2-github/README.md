# spring-oauth2-github

Spring Security yapılandırmasını sağlayarak, tüm gelen HTTP isteklerinin kimlik doğrulaması yapılmasını zorunlu hale getirdim. authorizeHttpRequests() metodu ile her isteğin doğrulanması gerektiğini belirledim. Ardından .oauth2Login() ile OAuth2 tabanlı giriş işlemini etkinleştirdim, böylece kullanıcılar OAuth2 ile giriş yapabilir.

@SpringBootApplication anotasyonu ile Spring Boot uygulamasını başlattım. Ayrıca @RestController kullanarak REST API endpoint'i oluşturduğum.
GitHub OAuth2 ile Kimlik Doğrulama Sağladım: @GetMapping("/info") endpoint’ini tanımlayarak, kullanıcıların GitHub üzerinden kimlik doğrulaması yapmasını sağladım. Principal nesnesini kullanarak, giriş yapan kullanıcının bilgisini aldım ve bu bilgiyi JSON formatında döndürdüm.

OAuth2, üçüncü taraf uygulamaların kullanıcının kimlik bilgilerini paylaşmadan, kullanıcının başka bir uygulama veya servise güvenli bir şekilde erişmesine olanak tanır. Bu, özellikle farklı platformlar arasında güvenli veri paylaşımı ve oturum yönetimini sağlar.
OAuth2, kullanıcının kimliğini doğrulamadan, başka bir hizmete (örneğin, bir sosyal medya platformuna) erişim izni veren bir yetkilendirme protokolüdür. Kullanıcı, şifreyi paylaşmadan, üçüncü taraf bir uygulamaya sınırlı erişim sağlar.

Spring Security için OAuth2 yapılandırmasını gerçekleştirdim. GitHub üzerinden kullanıcı kimlik doğrulaması sağlamak için clientId ve clientSecret değerlerini belirledim. Bu değerler, OAuth2 uygulaması için gerekli kimlik doğrulama bilgileri.

![github-oauth2-login](https://github.com/user-attachments/assets/94cc00db-859d-4f39-b1b3-f9cd383309cd)
![github-oauth2-login2](https://github.com/user-attachments/assets/8bfd667c-3fe6-4825-8b44-95ed0cf50dd1)
