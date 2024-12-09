# redis-pubsub
Redis, "Remote Dictionary Server" kelimelerinin kısaltmasıdır ve açık kaynaklı, bellek içi (in-memory) çalışan, yüksek performanslı bir veri yapısı deposudur.

Redis genellikle aşağıdaki amaçlarla kullanılır:

Önbellekleme: Veritabanı sorgularını hızlandırmak veya sık kullanılan verileri saklamak için.

Mesajlaşma: Pub/Sub mekanizmasıyla uygulamalar arasında hızlı iletişim sağlamak.

Veri Yapıları: Listeler, kümeler, sıralı kümeler, hash'ler gibi veri yapılarını destekler.

Kuyruk Yönetimi: İşlem kuyruklarını yönetmek için.

Oturum Yönetimi: Kullanıcı oturum bilgilerini hızlıca saklayıp erişmek için.
Redis, genelde veri tabanlarına göre çok daha hızlıdır çünkü verileri bellekte (RAM) saklar ve gerektiğinde diske yazar. Yüksek performanslı ve esnek bir araç olarak hem büyük ölçekli sistemlerde hem de küçük projelerde sıkça tercih edilir.

#### Redis Konfigürasyonu:
RedisMQConfig sınıfını oluşturup Redis'in tüm yapılandırmalarını burada yaptım.
Redis ile iletişim için StringRedisTemplate nesnesini RedisConnectionFactory ile bağlayarak bir bean olarak tanımladım.
Mesajların yayınlanacağı ve dinleneceği bir kanal tanımladım (paytm-channel).
Mesajları dinlemek için bir MessageListenerAdapter kullandım ve bu adapteri PaytmClientApp sınıfına bağladım.
Redis'ten gelen mesajları dinlemek için bir RedisMessageListenerContainer oluşturup, bu container'a hem listener'ı hem de kanalı ekledim.

#### Mesaj Dinleyici:
PaytmClientApp sınıfını bir MessageListener olarak tanımladım.
Redis kanalından gelen mesajları onMessage metodu içinde yakalıyorum. Burada sadece mesajın konsola yazılmasını sağladım, ancak ihtiyaç olursa bu mesajları başka işlemler için de kullanabilirim.

#### Veri Modeli:
PaymentRequest sınıfını oluşturup, bir ödeme isteği için gerekli olan bilgileri (transactionId, kaynak ve hedef hesaplar, miktar, işlem tarihi) tanımladım. Bu sınıf, mesaj alışverişinde kullanılan veri modeli oldu.

#### Mesaj Üretici:
PaytmProducer sınıfını yazdım.
Ödeme isteğini aldıktan sonra Redis kanalına mesaj göndermek için convertAndSend metodunu kullandım.
PaymentRequest nesnesini JSON formatına çevirerek Redis'e gönderdim. Bu sayede sistemler arasında kolayca taşınabilir bir veri formatı kullandım.

#### Rest API:
RedisPubsubExampleApplication ana sınıfını yazdım.
Spring Boot uygulaması başlatan main metodunu ekledim.
Bir REST endpoint (/redis/pay) oluşturup, bu endpoint'e gelen ödeme isteğini PaytmProducer üzerinden Redis'e gönderdim.
Her işlem için benzersiz bir transactionId ve işlem tarihi (txDate) ekledim.

#### Özetle:
Bu projede Redis Pub/Sub mekanizmasını kullanarak bir mikroservis tasarladım. Ödeme isteği REST API üzerinden alınıyor, Redis'e gönderiliyor ve Redis kanalından mesaj olarak tüketiliyor. Proje, yüksek performanslı ve düşük gecikmeli mesajlaşma sistemleri kurmak için Redis'in güçlü yanlarını kullanıyor.