Order-service, mikroservis mimarisinde siparişlerin alınıp işlenmesi için geliştirdiğim bir servis. @Service ve @RefreshScope anotasyonlarını kullanarak servisin dinamik olarak yapılandırılabilir olmasını sağladım. @Lazy anotasyonunu RestTemplate için kullandım, böylece sadece ihtiyaç duyulduğunda başlatılarak uygulama performansını optimize ettim. RestTemplate, dış servislerle iletişimi sağlamak için kullanılıyor ve @LoadBalanced anotasyonu sayesinde yük dengelemesi yaparak daha verimli bir ağ iletişimi kurabiliyorum. @CircuitBreaker anotasyonu ise servisin başarısız olması durumunda sistemin çökmesini engelliyor, belirlediğim fallback methodu ile alternatif bir işlem sunuyorum.

Order-service’de siparişler kaydediliyor ve ödeme ile kullanıcı bilgileri gibi dış mikroservislerle iletişim kurmak için REST çağrıları yapılıyor. Kafka yerine StreamBridge kullanarak, sipariş bilgilerini ödeme mikroservisine gönderiyorum. @EnableDiscoveryClient kullanarak bu servisi Eureka üzerinde kaydettim, böylece diğer mikroservislerin bu servisi bulup iletişime geçebilmesini sağladım. Böylelikle, servisler arası iletişim için merkezi bir servis keşfi oluşturmuş oldum.

Servis, gelen sipariş bilgilerini doğruladıktan sonra, veritabanına kaydediyor ve ardından ilgili ödeme ve kullanıcı servisine REST çağrıları yapıyor. Ödeme servisi ve kullanıcı servisi ile olan bu iletişim, mikroservisler arası bağımsızlık ve esneklik sağlıyor. Ayrıca, devre kesici özelliği sayesinde sipariş servisi başarısız olduğunda bile sistem çalışmaya devam edebiliyor ve kullanıcıya hata yerine alternatif bir işlem sunuluyor.

Yapım sürecinde sürekli olarak hataları loglayıp, uygulama sağlığını izleyerek hızlıca müdahale ettim. Örneğin, ödeme işlemleri başarısız olduğunda devre kesici devreye giriyor ve belirlediğim fallback methodu ile kullanıcıya "Sipariş alınamadı" şeklinde bir mesaj veriyorum. Bu mikroservisi geliştirmemde @Value ile dış konfigürasyon dosyalarındaki değerleri kolayca çekebildim, böylece servisler arası veri paylaşımı daha rahat oldu.

Sonuç olarak, order-service, Spring Cloud özelliklerini kullanarak sağlam, ölçeklenebilir ve esnek bir yapıda geliştirildi.

@RefreshScope, Spring Cloud Config ile birlikte kullanılarak, uygulamanın konfigürasyon değerlerini dinamik olarak yenilemesini sağlar.
Bu sayede, uygulama yeniden başlatılmadan  yapılandırma değişiklikleri anında uygulanabilir.

@RefreshScope ile işaretlenmiş bir sınıf içerisindeki @Value ile enjekte edilen değerler,  konfigürasyon değiştirildiğinde otomatik olarak yenilenir.
Ancak bunun gerçekleşmesi için Spring Cloud Config ve bir mekanizma olan  /actuator/refresh endpoint'inin kullanılması gerekir.