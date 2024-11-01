# spring-data-auditing

Spring Boot'ta Auditing, veri tabanındaki kayıtların oluşturulma, güncellenme ve silinme işlemlerinin otomatik olarak izlenmesini sağlar. Bu özellik, kullanıcı bilgilerini (denetleyici) ve zaman damgalarını (oluşturma ve güncelleme tarihleri) saklayarak, uygulamanın veri yönetimini ve izlenebilirliğini artırır.

Envers, Hibernate için bir modüldür ve veri tabanı nesnelerinin geçmişini takip etmek amacıyla audit (denetim) ve revizyon özellikleri sunar. Envers, bir varlık üzerinde yapılan değişikliklerin (ekleme, güncelleme, silme) geçmişini otomatik olarak saklar ve bu değişikliklere erişim sağlar.

AuditorAwareImpl sınıfını oluşturarak, Spring Boot uygulamamda Auditing işlemleri için geçerli denetleyici bilgisini sağlamaya karar verdim. AuditorAware<String> arayüzünü implement ederek, getCurrentAuditor metodunu tanımladım ve bu metod her çağrıldığında "Hüseyin" adını döndürerek, veri tabanında kaydedilen değişikliklerin kim tarafından yapıldığını belirtmiş oldum. Bu sayede, uygulamamda denetleyici bilgilerini otomatik bir şekilde saklayabiliyorum.

@EntityListeners(AuditingEntityListener.class) anotasyonu, Spring Boot'ta Auditing işlemlerini etkinleştirmek için kullanılır. Bu anotasyonu ekleyerek, veri tabanında bir kaydın ne zaman oluşturulduğunu ve kim tarafından güncellendiğini otomatik olarak izleyebilirim. AuditingEntityListener, nesneye özel değişiklikleri algılayarak ilgili alanları güncellememe yardımcı olur.

ProductRepository arayüzü, hem RevisionRepository hem de JpaRepository arayüzlerini genişleterek ürün veritabanı işlemlerini yönetmek için kullanılır. RevisionRepository<Product, Integer, Integer> kısmı, ürünlerin revizyonlarını takip etmemi sağlarken, JpaRepository<Product, Integer> kısmı ise temel CRUD (Create, Read, Update, Delete) işlemlerini gerçekleştirmeme olanak tanır. Bu yapı sayesinde, ürün kayıtlarının geçmişini izleyebilir ve veritabanı üzerinde etkili bir şekilde işlem yapabilirim.

@EnableJpaAuditing(auditorAwareRef = "auditorAware") anotasyonu, Spring Boot uygulamamda JPA Auditing özelliğini etkinleştirmek için kullanılır ve auditorAware referansını belirleyerek, güncel denetleyici bilgisinin hangi sınıftan alınacağını tanımlar. Bu, veritabanındaki kayıtların ne zaman ve kim tarafından oluşturulduğunu veya güncellendiğini otomatik olarak izlememi sağlar.

@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class) anotasyonu ise, Envers ile birlikte audit işlemlerini destekleyen özel repository sınıflarını kullanmamı sağlar. Bu sayede, hem standart JPA işlemlerini hem de revizyon kayıtlarını yönetme yeteneğini bir arada elde ederim. Bu iki anotasyon sayesinde, uygulamamda etkili bir audit ve repository yönetimi gerçekleştirmiş oldum.