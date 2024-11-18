# spring-security-role-auth-and-auditing

Bu projeyi Spring Security ile Auditing mekanizmalarını öğrenmek ve uygulamak amacıyla geliştirdim. İlk olarak, kullanıcı aktivitelerini takip edebilmek için Spring Security ve Auditing özelliklerini entegre ettim. Sistemde oturum açmış bir kullanıcının yaptığı değişikliklerin, kimin tarafından ve ne zaman gerçekleştirildiğini kaydetmek için AuditorAware arayüzünü özelleştirdim. Burada SecurityContextHolder ile aktif kullanıcı bilgilerini çekerek sistemin güvenli bir şekilde audit yapmasını sağladım.

Entitilerimde @CreatedBy, @CreatedDate, @LastModifiedBy, @LastModifiedDate gibi anotasyonları kullanarak, her kaydın oluşturulma ve güncellenme bilgilerini otomatik olarak kaydettim. Ayrıca, uygulamada Hibernate Envers kullanarak geçmiş değişiklikleri de takip edebileceğim bir yapı kurdum. Tüm bunlar sayesinde, kim hangi işlemi yapmış, ne zaman yapmış gibi sorulara kolayca yanıt alabiliyorum.

Sonuç olarak, bu proje ile hem Spring Security Auditing mantığını kavradım hem de gerçek dünyada kullanılabilecek bir kullanıcı aktiviteleri izleme sistemi geliştirmiş oldum. Baştan sona tüm konfigürasyonu ve kodlamayı kendim yaptım, karşılaştığım sorunları çözerek projeyi hayata geçirdim.

Projede @EnableJpaAuditing dipnotunu, Auditing özelliğini etkinleştirmek ve hangi kullanıcıların hangi işlemleri yaptığını takip etmek için kullandım. Bu dipnot sayesinde, @CreatedBy, @CreatedDate, @LastModifiedBy gibi anotasyonlar otomatik olarak çalışıyor.

@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class) dipnotunu ise Hibernate Envers’i etkinleştirmek için ekledim. Bu sayede, entitiler üzerinde yapılan değişikliklerin geçmişine erişip, eski versiyonlarını sorgulayabiliyorum.

@EntityListeners(AuditingEntityListener.class) dipnotunu, JPA entitilerime ekleyerek, bu entitilerdeki her işlemde Auditing özelliklerinin otomatik olarak devreye girmesini sağladım. Bu sayede, veritabanı işlemleri gerçekleştiğinde oluşturulma ve güncellenme bilgileri (örneğin, kim tarafından ve ne zaman) otomatik olarak kaydediliyor.

@Audited dipnotunu ise Hibernate Envers ile birlikte kullanarak, her entitinin geçmiş değişikliklerini izlemeye başladım. Bu özellik sayesinde, her kayıt üzerinde yapılan değişiklikler ve eski versiyonlar tutuluyor. Bu, herhangi bir kaydın geçmişini sorgulamak ve yapılan değişiklikleri görmek için oldukça faydalı oldu.

Bu iki özellik sayesinde, hem Auditing hem de versiyon takibi açısından güçlü bir sistem kurmuş oldum.

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) dipnotunu, uygulamamda metod düzeyinde güvenlik kontrolü yapmak için kullandım. Bu dipnot sayesinde, metodlara özel erişim izinleri tanımlamak mümkün hale geldi. prePostEnabled = true ile, belirli metotlara erişimden önce ve sonra bazı güvenlik kontrolleri yapabiliyorum, örneğin, kullanıcının bir kaynağa erişmeden önce gerekli yetkilere sahip olup olmadığını kontrol edebiliyorum. securedEnabled = true ile de, @Secured anotasyonu kullanarak, metot bazında kullanıcı rol kontrolleri ekleyebiliyorum. Bu sayede uygulamamın her yönünde detaylı güvenlik politikaları uyguladım ve her metodun erişim seviyesini kontrollü bir şekilde yönetiyorum.

#### 2 kullanıcı kaydetme.

```curl --location --request POST 'http://localhost:9090/user/join' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=17V9CVF5EHE54D537123E2790T446E4A' \
--data-raw '{
    "userName": "huseyinaydin99",
    "password": "toor",
    "active": true
}'
```

```
curl --location --request POST 'http://localhost:9090/user/join' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=17V9CVF5EHE54D537123E2790T446E4A' \
--data-raw '{
    "userName": "root",
    "password": "toor",
    "active": true
}'
```

#### Id'si 2 olan kullanıcının rolünü admin yap.

```
update users_authentication_tbl set roles="ROLE_ADMIN" where id=2
```

#### İlgili kullanıcı ile gönderi atmak.

```
curl --location --request POST 'http://localhost:9090/post/create' \
--header 'Authorization: Basic QxFrYB501nM3YDQ=' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=17V9CVF5EHE54D537123E2790T446E4A' \
--data-raw '{
    "subject": "Hashmap hakkında.",
    "description": "Hashmap veri ambarı tekrarlanan key(anahtar)'lere izin verir."
}'
```

#### Id'si 3 olan gönderinin admin veya moderatör tarafından onaylanması.

```
curl --location --request GET 'http://localhost:9090/post/approvePost/3' \
--header 'Authorization: Basic QxFrYB501nM3YDQ==' \
--header 'Cookie: JSESSIONID=17V9CVF5EHE54D537123E2790T446E4A' \
--data-raw ''
```