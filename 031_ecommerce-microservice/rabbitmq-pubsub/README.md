# rabbitmq-pubsub

RabbitMQ, sistemler arasında veri iletmek için kullanılan bir mesajlaşma aracıdır. Aslında bir "mesaj kuyruğu" gibi çalışır ve farklı uygulamalar ya da servisler arasında veri transferini kolaylaştırır. Bu sayede bir sistem mesajı gönderirken, alıcı sistem mesajı almak için hazır olduğunda işlemi alır, yani asenkron çalışır. Kısacası, RabbitMQ, birden fazla uygulamanın birbirine mesaj göndermesini ve almasını sağlayan bir köprü gibi düşünülebilir.

<hr />

Projemi RabbitMQ kullanarak bir mikroservis mimarisiyle oluşturduğum bir ödeme işleme sistemi üzerine geliştirdim. Bu proje, ödeme taleplerini alıp işleyen ve kuyruğa gönderen bir üretici (publisher) ile bu talepleri kuyruğa gönderilen mesajları dinleyerek işleyen tüketicilerden (consumers) oluşuyor.

İlk olarak, MessagingConfig sınıfını yazdım. Burada RabbitMQ ile haberleşmek için gerekli olan kuyruğu, exchange'i ve routing key'i tanımladım. Aynı zamanda AmqpTemplate ve mesaj dönüştürücü (message converter) olarak Jackson2JsonMessageConverter kullandım. Bu sayede, gelen ve giden mesajların JSON formatında güvenli ve verimli bir şekilde iletilmesini sağladım.

Ödeme taleplerini işleyen üç farklı tüketici servisi oluşturdum: PaytmClientApp1, PaytmClientApp2, ve PaytmClientApp3. Her biri, RabbitMQ kuyruğundaki mesajları dinleyerek gelen ödeme taleplerini aldı ve işledi. Bu şekilde, aynı kuyruğa abone olan birden fazla tüketici ile yük dengelemesi yaparak yüksek kullanılabilirlik sağladım.

API kısmında, PaytmController sınıfını oluşturdum. Bu sınıf, bir REST endpoint’i üzerinden ödeme işlemi başlatıyor. Kullanıcıdan gelen ödeme talebini, RabbitMQ kuyruğuna gönderecek şekilde PaytmPublisher sınıfındaki doPaymentTransaction metodunu çağırıyor. Bu metodda, her ödeme isteğine benzersiz bir transactionId ve işlem tarihi ekliyorum, sonra bu isteği RabbitMQ'ya gönderiyorum.

Son olarak, ödeme taleplerini içeren PaymentRequest sınıfını tanımladım. Bu sınıf, işlemle ilgili gerekli tüm bilgileri tutuyor: gönderen hesap, alıcı hesap, tutar ve işlem tarihi gibi bilgiler bulunuyor.

Bu proje, RabbitMQ’nun asenkron mesajlaşma yapısını kullanarak ödeme işlemlerini verimli bir şekilde yönetmeme olanak tanıdı ve mikroservisler arasında kolayca iletişim kurmamı sağladı.