# spring-boot-profile

``
export SPRING_PROFILES_ACTIVE=foo,bar
java -jar jardosyan.jar
``

Spring Boot'da profiller, farklı ortamlar (DEV, PROD gibi) için özel yapılandırmalar tanımlamaya olanak tanır. Örneğin, DEV profili geliştirme aşamasındaki ayarları içerirken, PROD profili canlıya çıkarken kullanılacak ayarları barındırır. Hangi profilin kullanılacağını belirlemek için spring.profiles.active ayarını kullanarak uygulamayı o ortama göre başlatabiliriz. Spring Boot'ta birden fazla properties veya YAML dosyası olabilir. Örneğin, application-dev.properties veya application-prod.properties gibi farklı ortamlar için ayrı yapılandırma dosyaları oluşturabilirsiniz. Spring Boot, aktif profile göre bu dosyalardan uygun olanını otomatik olarak yükler ve uygular.