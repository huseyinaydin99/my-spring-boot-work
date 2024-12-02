ecom-gateway-service, tüm mikroservislerin giriş noktası olarak hizmet vererek istekleri doğru servislere yönlendirdim.

API Gateway, gelen talepleri filtreleyerek yetkilendirme, doğrulama ve yük dengeleme gibi işlemleri burada gerçekleştirdim.

Mikroservislerin dış dünyaya doğrudan açılmaması, güvenlik açısından büyük bir avantaj sağladı.

Tek bir URL üzerinden tüm servisleri yönetebilmek, bakım ve güncelleme süreçlerini oldukça kolaylaştırdı.

Ayrıca, Gateway üzerinde özel yönlendirme kuralları oluşturarak, farklı servislerin farklı yollarla erişilmesini sağladım.

@EnableDiscoveryClient dipnotu, bir mikroservisin servis kayıt defterine (Eureka gibi) kaydolmasını ve diğer servisleri keşfetmesini sağlar.

Bu sayede mikroservis, dinamik olarak diğer servislerin adreslerini öğrenebilir ve onlarla iletişim kurabilir.

order-service, payment-service ve user-service gibi mikroservislere yönlendirme yapmak için lb:// (load balancer) kullanarak dinamik servis keşfi sağladım.

Her bir servise özel CircuitBreaker filtreleri ekleyerek, servislerde bir arıza durumunda fallback işlemleri tanımladım.

Konfigürasyonun merkezi yönetimi için configserver üzerinden ayarları aldım ve servis adı olarak GATEWAY-SERVICE belirledim.

Ayrıca, Eureka servis keşif sunucusu ile entegrasyonu aktif hale getirebildim, ancak şu an için yorum satırına alındı.