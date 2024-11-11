# spring-kafka-producer

Apache Kafka, büyük miktarda veriyi gerçek zamanlı olarak iletmek, işlemek ve analiz etmek için kullanılır. Aynı zamanda, mikro hizmetler arasında güvenilir mesajlaşma ve veri akışını sağlamak amacıyla da tercih edilir.

Sunucu portunu ayarladım: server.port=9191 ile uygulamanın çalışacağı portu 9191 olarak belirledim.
Kafka sunucusunu tanımladım: spring.kafka.producer.bootstrap-servers ayarını localhost:9092 olarak ayarlayarak, Kafka üreticimin bu sunucudan veri göndermesini sağladım.
Konu (topic) adını belirledim: payment.producer.topic.name ayarını payment-topic olarak atayarak, Kafka mesajlarının gönderileceği konuyu belirledim. Böylece Kafka’ya gönderilecek tüm mesajlar bu konuya yönlendirilecek.
Serileştirici ayarlarını yaptım: spring.kafka.producer.key-serializer ile mesaj anahtarlarının StringSerializer kullanılarak serileştirilmesini sağladım. Ayrıca, spring.kafka.producer.value-serializer ile mesaj içeriklerinin JsonSerializer kullanılarak JSON formatında serileştirileceğini belirledim. Bu ayarlar, Kafka’nın anahtarları ve değerleri düzgün bir şekilde işleyebilmesini sağlar.
Bu yapılandırmalarla, Kafka üreticimin doğru sunucu, konu ve serileştirme ayarlarıyla çalışmasını sağladım.

Kafka konusunu belirledim: @Value anotasyonuyla payment.producer.topic.name adında bir yapılandırma özelliğini topicName değişkenine atadım. Bu, Kafka’ya mesaj gönderirken hangi konuyu kullanacağımı belirledi.
KafkaTemplate’i tanımladım: @Autowired anotasyonunu kullanarak, Kafka mesajlarını gönderebilmek için KafkaTemplate<String, Object> nesnesini kafkaTemplate olarak enjekte ettim.
Mesaj gönderen bir GET endpoint’i oluşturdum: /publish/{message} adında bir GET isteği tanımladım. Bu istek, message adlı bir parametre alıyor. Döngüyle message değerine 0’dan 10.000’e kadar sayılar ekleyerek her birini Kafka’ya ayrı mesaj olarak gönderdim. Böylece bu endpoint’i çağırdığımda çok sayıda mesaj göndermiş oldum.
Ödeme işlemi yapan bir POST endpoint’i oluşturdum: /payment adlı bir POST isteği tanımladım. Bu istekte bir PaytmRequest<PaymentRequest> nesnesi alıyorum. Önce PaymentRequest içindeki transactionId alanına yeni bir UUID değeri atadım, ardından txDate alanına mevcut tarihi koydum. Sonra, Kafka’ya paymentRequest nesnesini mesaj olarak gönderdim. İşlem sonunda da "ödeme tamamlandı kardeş... :)" mesajını döndürdüm.
Bu iki endpoint ile Kafka’ya toplu mesaj ve ödeme verisi gönderen bir sistem geliştirdim.

