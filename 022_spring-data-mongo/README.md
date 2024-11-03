# spring-data-mongo

MongoDB, verileri esnek bir yapıda saklayan ve belgeler (documents) şeklinde organize eden bir NoSQL veritabanıdır. Geleneksel ilişkisel veritabanlarının katı şemalarına bağlı kalmadan, verileri hızlı bir şekilde işleyebilme yeteneği sunar. Yüksek performansı ve ölçeklenebilirliği sayesinde büyük veri uygulamaları için idealdir.

MongoDB ve Spring Boot ile çok katmanlı bir proje geliştirdim. İlk olarak, görevleri yönetmek amacıyla bir Task sınıfı oluşturdum ve bu sınıfı MongoDB'deki "Tasks" koleksiyonuyla ilişkilendirdim. Ardından, TaskController sınıfını oluşturarak RESTful API’ler aracılığıyla görev ekleme, güncelleme, silme ve listeleme işlemlerini gerçekleştirdim. Veritabanı etkileşimlerini kolaylaştırmak için TaskRepository kullanarak özelleştirilmiş sorgular ekledim. TaskService sınıfında, iş mantığını tanımlayarak görevlerin nasıl yönetileceğini belirledim. Projemin yapılandırmasını ise application.yml dosyasında MongoDB sunucusuna bağlanacak şekilde düzenledim. Bu şekilde, görev yönetim uygulamamı başarılı bir şekilde hayata geçirmiş oldum ve MongoDB'nin sunduğu esneklikten yararlandım.

````Java
@Query(value = "{assignee: ?0 ,priority: ?1}", fields = "{'description' : 1 , 'storyPoint': 2}")
List<Task> findTaskWithAssigneeAndPriority(String assignee, String priority);
````

Bu sorgu, MongoDB'de belirli bir assignee ve priority değerine sahip görevleri bulmak için kullanılır.
?0 ve ?1, sırasıyla bu iki parametreyi temsil eder.
Sorgunun sonuçları yalnızca description ve storyPoint alanlarını içerecek şekilde sınırlandırılmıştır,
böylece daha az veri iletilerek performans artırılır.

MongoDB'de sorgu sonuçlarını kısıtlarken kullanılan fields parametresindeki 1 ve 2 değerleri, ilgili alanların nasıl döneceğini belirler:

1: Bu, ilgili alanın sorgu sonuçlarında yer alacağını belirtir. Yani, {'description' : 1} ifadesi, description alanının sonuçta gösterilmesini istiyoruz demektir.
<br />0: Eğer 0 kullanılsaydı, ilgili alanın sonuçta yer almayacağını belirtirdi.
<br />2: Ancak, burada storyPoint için 2 kullanımı yanlıştır. MongoDB'de bu şekilde alan gösterimi yoktur. 1 kullanılması yeterlidir; bu yüzden {'storyPoint': 1} olmalıdır.
