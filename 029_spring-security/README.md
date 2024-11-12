# spring-security-example

### JwtAuthFilter Sınıfı:
Bu sınıfı yazarken, Spring Security'nin OncePerRequestFilter sınıfını kullanarak gelen her isteği bir kez kontrol etmeyi amaçladım. İlk olarak, gelen HTTP isteğinden Authorization başlığını aldım ve token'ı çıkardım. Eğer başlık "Bearer " ile başlıyorsa, token'ı aldım ve JWT servisimi kullanarak bu token'dan kullanıcı adını çıkardım. Ardından, token'ı doğrulamak için JwtService sınıfını kullandım ve kullanıcının bilgilerini UserDetailsService üzerinden aldım. Eğer token geçerliyse, güvenli bir şekilde kimlik doğrulaması yaparak SecurityContextHolder ile Spring Security'nin güvenlik bağlamına yerleştirdim.

Bu kodda özellikle güvenliği sağlamak için SecurityContextHolder kullanarak kullanıcı doğrulamasını gerçekleştirdim. Token'ın doğruluğunu kontrol etmek ve kullanıcının güvenliğini sağlamak için her detayı dikkatlice ele aldım. Son olarak, başarılı doğrulama sonrası, gelen isteği filtrelemek için filterChain.doFilter() metodunu çağırarak süreci tamamladım.

### EmployeeService Sınıfı:
Bu sınıfı yazarken, çalışan yönetimi için temel CRUD işlemleri üzerine odaklandım. Yeni bir çalışan oluşturmak için, öncelikle kullanıcının şifresini güvenli bir şekilde şifreledim. Bunun için Spring Security'nin PasswordEncoder sınıfını kullandım. Şifreyi doğru bir şekilde şifreledikten sonra, EmployeeRepository ile veritabanına kaydettim. Ayrıca, çalışanların rollerini değiştirebilmek için bir metod ekledim ve bu metodu, var olan bir çalışanın rollerini değiştirmek için kullandım.

Çalışan yönetimini ve şifre güvenliğini gerçekten önemseyerek tasarladım. Her adımda, verilerin güvenliğini ve işlemlerin doğru şekilde yapılmasını sağlamak için gerekli tüm önlemleri aldım. Bu sınıf sayesinde çalışan eklemek, güncellemek ve şifre güvenliğini sağlamak gibi işlemleri çok kolay ve güvenli bir şekilde yapabiliyorum.

### EmployeeController Sınıfı:
Controller sınıfımda, çalışanlarla ilgili tüm işlemleri RESTful API aracılığıyla yönettim. Kullanıcılar, giriş yapmak için authenticate metodunu kullanarak kullanıcı adı ve şifre ile doğrulama yapabiliyor. Eğer kullanıcı doğrulandıysa, başarılı bir şekilde JWT token'ı alıyor. Ayrıca, çalışan eklemek için onboardNewEmployee metodunu kullandım ve yalnızca belirli bir yetkiye sahip kullanıcıların bu işlemi yapabilmesi için @PreAuthorize anotasyonunu ekledim.

getEmployees metodunda, çalışanların listesini yalnızca "HR" veya "Manager" rolüne sahip kullanıcıların görebilmesi için gerekli yetkilendirmeyi ekledim. Ayrıca, her çalışanın bilgilerini almak için getEmployeeById metodunu oluşturduk ve burada "Employee" rolüne sahip kullanıcıların yalnızca kendi bilgilerini görüntülemelerini sağladım. Son olarak, çalışanların rollerini güncelleyebilmesi için updateRoles metodunu yazdım ve yalnızca "HR" rolüne sahip kullanıcıların bu işlemi gerçekleştirmesine olanak tanıdım.

Bu sınıfta, güvenlik en üst seviyede olmalıydı, bu yüzden her bir API uç noktasına doğru yetkilendirmeleri ekledim. Ayrıca, kullanıcı doğrulama ve yetkilendirme işlemleri ile ilgili her şeyi doğru bir şekilde yapılandırarak, hem kullanıcı deneyimini hem de sistem güvenliğini sağladım.

### Kod Genel Değerlendirme:
Projede yaptığım işlerin güvenliğine büyük önem verdim. Spring Security'yi kullandım ve JWT ile token bazlı kimlik doğrulaması sağladım. Bu, uygulamanın güvenliğini sağlayan temel yapı taşı oldu. JwtAuthFilter sınıfı sayesinde her gelen isteği kontrol ettim ve token geçerliyse kullanıcıyı güvenli bir şekilde doğruladım. Ayrıca, EmployeeService sınıfında çalışanların şifrelerini güvenli bir şekilde şifreleyip, rollerini yönetebildim.

Controller kısmında ise, sadece yetkili kullanıcıların işlem yapabilmesini sağlayacak @PreAuthorize anotasyonları ekledim ve API'yi doğru şekilde yapılandırdım. Bu sayede, sistemim hem güvenli hem de kullanıcı dostu hale geldi. Sonuç olarak, tüm bu süreçlerde güvenliği sağlamaya yönelik en iyi uygulamaları dikkate alarak her adımı özenle gerçekleştirdim.

<hr />

### Spring Security:
Spring Security, güvenli bir uygulama geliştirmek için çok güçlü bir araç. Bu projede, güvenlik altyapısının temeli olarak Spring Security'i kullandım. Uygulamanın her kısmında kimlik doğrulama, yetkilendirme ve güvenli oturum yönetimi için bu framework'ü aktif olarak kullanarak, kullanıcıların doğru bir şekilde kimliklerini doğrulamalarını sağladım. Özellikle her endpoint için belirli güvenlik kuralları tanımladım, örneğin, sadece yetkili kullanıcıların erişebileceği sayfalara erişim sağlayabildiler. Böylece, her bir endpointin güvenliği sağlanmış oldu.

Projede, Spring Security ile birlikte her işlemde kullanıcıların hangi rollerle işlem yapabileceklerini belirledim. Bu sayede, uygulama üzerinde "admin", "employee", "HR" gibi farklı kullanıcı rollerine göre özelleştirilmiş yetkiler oluşturabildim. @PreAuthorize ve @Secured gibi anotasyonlarla, metod düzeyinde güvenliği sağlayarak kullanıcıların yalnızca yetkili oldukları işlemleri yapabilmelerini sağladım. Bu, gerçekten önemli çünkü kullanıcıların sadece yetkileri doğrultusunda erişim sağlamalarını sağlamak, projenin güvenliğini ciddi şekilde artırıyor.

### JWT (JSON Web Token):
JWT, uygulamamda kullanıcı doğrulama ve yetkilendirme işlemlerinde temel bir teknoloji olarak yer alıyor. Kullanıcılar sisteme giriş yaptıktan sonra, kimlik doğrulama işlemi başarılı olduğunda bir JWT token'ı oluşturuluyor ve bu token her istekle birlikte sunucuya gönderiliyor. Bu token, sunucuya her gelen istekte kimliği doğrulamak için kullanılıyor. Ben JWT kullanarak uygulama güvenliğini arttırmayı amaçladım, çünkü bu yöntemle her istekte yeniden kullanıcı bilgilerini almak zorunda kalmıyoruz. Böylece uygulamanın hızını da artırdım.

JWT, her request ile birlikte kullanıcı doğrulama işlemini gerçekleştiriyor. Token, belirli bir süre geçerli oluyor ve süresi dolduğunda yeniden doğrulama yapılması gerekiyor. Ben bu doğrulama sürecini çok dikkatli bir şekilde yönettim. Her kullanıcıya özel token'lar oluşturuluyor ve bu token'lar, sunucuda her istekte kullanıcı kimliğini doğrulamak için kullanılıyor. Sonuç olarak, JWT sayesinde oturum yönetimi işlemlerini çok daha verimli hale getirdim ve sistemdeki güvenliği en üst seviyeye çıkardım.

### Proje Genel Yapısı:
Projede yaptığım en önemli şeylerden biri Spring Security ve JWT entegrasyonu oldu. Bu ikisini entegre ederek güçlü bir güvenlik altyapısı kurdum. İlk olarak, kullanıcıların giriş işlemlerini JWT ile yapılandırdım ve güvenli bir şekilde kullanıcıların kimliklerini doğrulamak için Spring Security kullanarak, her endpointi korudum. Özellikle login ve signup gibi işlemlerde, kullanıcıların doğru bir şekilde kimlik doğrulamalarını sağlamak için JWT ile authentication işlemlerini dikkatlice tasarladım. Bu tasarım sayesinde, kullanıcılar sadece geçerli bir JWT token'ı ile uygulamanın her yerine erişebildiler.

Yaptığım bir diğer önemli şey ise EmployeeUserDetailsService sınıfıydı. Burada, kullanıcı bilgilerini Employee sınıfından alarak UserDetails'e dönüştürüp, Spring Security ile uyumlu hale getirdim. Bu sınıfın amacı, kullanıcının bilgilerini güvenli bir şekilde elde etmek ve Spring Security'nin kimlik doğrulama sürecinde kullanılmak üzere düzenlemeler yapmaktı. JwtAuthFilter sınıfında ise, gelen her isteği kontrol ettim ve JWT token'ının geçerli olup olmadığını doğruladım. Eğer token geçerli ise, kullanıcı kimliğini doğruladım ve bu kimlikle ilgili yetkilendirme işlemlerini gerçekleştirdim.

JWT ile oturum yönetimini de oldukça verimli hale getirdim. Kullanıcılar her istekle birlikte JWT token'larını sunucuya gönderdiler ve ben bu token'ı doğrulayarak kullanıcının kimliğini kontrol ettim. Eğer token geçerli değilse, erişim reddedildi ve kullanıcıya yeni bir token almak için giriş yapması hatırlatıldı. Bu sayede, her kullanıcıya ayrı ayrı oturum açma işlemi yapmam gerekmedi, token geçerliliğini her seferinde kontrol ettim.

### Kodun Detaylı Açıklaması:
EmployeeUserDetails: Spring Security'nin UserDetails arayüzünü implement ettim. Burada, kullanıcı bilgilerini (kullanıcı adı, şifre, roller) Employee sınıfından alıp, Spring Security'nin istediği yapıya dönüştürdüm. Bu sınıf, kullanıcı bilgilerini güvenli bir şekilde saklar ve kullanıcı doğrulama işlemleri için gerekli veriyi sağlar.

JwtService: JWT token'larının oluşturulması ve doğrulanması ile ilgili işlemleri bu sınıfla yönettim. generateToken metodu ile kullanıcı adına özel bir token oluşturuluyor ve bu token, kullanıcı doğrulaması yapıldıktan sonra geri dönüyor. Ayrıca, token'ın süresinin geçip geçmediğini kontrol eden bir yöntem ekledim. Bu, token yönetiminin sorunsuz bir şekilde yapılmasını sağladı.

JwtAuthFilter: Bu sınıf, her gelen istekte JWT token'ını kontrol eder ve geçerli ise kullanıcının kimliğini doğrular. Eğer token geçerli ise, kullanıcının oturum bilgileri Spring Security'nin SecurityContext'ine eklenir. Bu sayede, her istekle birlikte kullanıcı doğrulaması yapılmış olur. Kullanıcının her isteğinde kimlik doğrulaması yapılması, güvenliği önemli ölçüde artırır.

EmployeeService: Bu servis sınıfı, Employee nesneleri ile ilgili işlemleri yönetir. Yeni bir çalışan kaydı oluşturulması, çalışanların bilgilerinin alınması ve mevcut çalışanların rollerinin değiştirilmesi gibi işlemleri burada tanımladım. Özellikle, şifrelerin güvenli bir şekilde saklanabilmesi için Spring Security'nin PasswordEncoder'ını kullanarak, çalışanların şifrelerini şifreledim.

EmployeeController: REST API katmanında, çalışanlarla ilgili işlemleri gerçekleştiren kontrolcü sınıfıdır. Kullanıcıların login işlemlerinden, çalışan eklemeye kadar birçok işlemi burada tanımladım. Yetkilendirme işlemleri için @PreAuthorize kullanarak, yalnızca yetkili kullanıcıların belirli işlemleri yapabilmesini sağladım. Örneğin, sadece "HR" yetkisine sahip kullanıcılar çalışan ekleyebilirken, "Manager" ya da "HR" yetkisine sahip kullanıcılar tüm çalışanları görebilir.

### Sonuç:
Bu projede kullandığım Spring Security ve JWT, güvenliği sağlamanın yanı sıra uygulamanın verimliliğini de artırdı. JWT ile oturum yönetimi yapılması, kullanıcı doğrulama işlemlerini basitleştirdi. Spring Security'nin sağladığı güçlü güvenlik altyapısı sayesinde, kullanıcıların yalnızca yetkileri doğrultusunda işlemler yapabilmelerini sağladım. Kendi uygulamamda, hem performans hem de güvenlik açısından mükemmel sonuçlar aldım.