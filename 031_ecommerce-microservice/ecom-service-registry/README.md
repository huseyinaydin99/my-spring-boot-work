ecom-service-registry'yi, mikroservislerin birbirlerini bulup iletişim kurmasını sağlamak için kurdum.

Servislerin dinamik olarak kaydolması ve keşfedilmesi sayesinde sabit IP adreslerine olan ihtiyacı ortadan kaldırdım.

Eureka gibi servis keşif araçlarını kullanarak ölçeklenebilir ve esnek bir yapı öğrendim ve uyguladım.

Bu yapı, mikroservisler arasında yük dengeleme ve hata toleransı sağlama konusunda bana önemli deneyimler kazandırdı.

@EnableEurekaServer dipnotu, uygulamayı Eureka tabanlı bir servis kayıt ve keşif sunucusu olarak çalıştırmak için kullanılır.

Bu sayede diğer mikroservisler, Eureka'ya kaydolup birbirlerini dinamik olarak keşfedebilirler.

Eureka Server, mikroservislerin kayıt olup birbirlerini bulmasını sağlayan bir servis keşif ve kayıt sunucusudur.

Servisler, dinamik olarak bu sunucuya kaydolur ve diğer servislerin adreslerini buradan alarak iletişim kurar.

Bu yapı, yük dengeleme ve hata toleransı gibi özelliklerle mikroservis mimarisinde esnek ve ölçeklenebilir bir çözüm sunar.