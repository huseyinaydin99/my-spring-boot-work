UserService mikroservisini geliştirirken, kullanıcılara ait işlemleri merkezi bir servis üzerinden yönetmeyi hedefledim.

addNewUser metodu ile yeni kullanıcıları veritabanına eklerken, getUser metodu ile kullanıcıları ID'leri üzerinden sorgulayıp, bulunamadıkları durumda hata fırlatıyorum.

updateAccountStatus fonksiyonunu ekleyerek, kullanıcının mevcut bakiyesini güncelledim ve yapılan değişiklikleri veritabanına kaydettim.

UserController'da, kullanıcı işlemlerini HTTP istekleriyle yönettim, @PostMapping ile kullanıcı kaydını, @GetMapping ile kullanıcıyı sorgulamayı sağladım.

Mikroservis mimarisine uygun olarak, her bir kullanıcı işlemi için ayrı HTTP istekleri ile servisleri birbirinden bağımsız çalıştırdım ve port numarasını loglayarak servislerin doğru çalıştığını doğruladım.

@EnableDiscoveryClient dipnotu, mikroservisin bir servis keşif sunucusuna kaydolmasını ve diğer mikroservisleri keşfetmesini sağlar.