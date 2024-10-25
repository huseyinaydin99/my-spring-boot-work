# spring-aop-example

Spring Boot ile geliştirdiğim son projede AOP (Aspect-Oriented Programming) yaklaşımını kullanarak yazılımın çeşitli katmanlarında çapraz kesme işlemleri gerçekleştirdim. Bu projede, AspectJ aracılığıyla @After, @Before, @AfterThrowing, ve @Around gibi AOP dipnotlarını uyguladım. Projemde özellikle performans ölçümü ve HTTP istek/yanıt loglama gereksinimlerini karşılamak için iki özel dipnot yazdım: @TrackExecutionTime ve @LogRequestAndResponse.

@TrackExecutionTime dipnotunu kullanarak, her metodun çalışma süresini ölçüp logladım. Böylece uygulama içindeki performans darboğazlarını kolayca tespit edebildim.
@LogRequestAndResponse ile gelen ve giden HTTP istek/yanıtlarını kaydederek, kullanıcı ve sistem etkileşimlerini daha şeffaf hale getirdim. Bu, özellikle debug süreçlerinde çok faydalı oldu.
Projemin backend tarafını geliştirdikten sonra kapsamlı bir test süreci gerçekleştirdim. Spring Boot’ta testlerimi yazarken MockMvc ve Mockito kütüphanelerinden yararlandım.

Test Senaryolarım:

Ürün Ekleme Testi: Bu testte, bir ürünün sisteme başarıyla eklenip eklenmediğini kontrol ettim. Ürün bilgilerini JSON formatında göndererek POST isteği üzerinden, ürün ekleme işlemini simüle ettim ve veritabanında doğru şekilde kaydedilip kaydedilmediğini test ettim.

Ürünleri Listeleme Testi: Ürünlerin başarılı bir şekilde listelendiğini test ettim. GET isteği kullanarak sisteme kayıtlı tüm ürünlerin döndüğünü ve JSON formatında düzgün bir yapı ile listelendiğini doğruladım. Ayrıca andDo(print()) ifadesini kullanarak yapılan HTTP isteklerinin ve yanıtlarının test sırasında konsola yazdırılmasını sağladım. Bu, test sürecini daha izlenebilir hale getirdi.

ID’ye Göre Ürün Getirme Testi: Spesifik bir ürün ID’si ile yapılan sorgunun doğru şekilde çalıştığını ve istenen ürünün doğru bilgilerle döndüğünü kontrol ettim. get("/{id}") ile bir ürün ID’si üzerinden yapılan sorgunun, doğru HTTP yanıtı ve JSON çıktısını verdiğini test ettim.

Ürün Güncelleme Testi: Mevcut bir ürünün bilgilerini güncelleyerek, bu güncellemenin sistemde başarılı bir şekilde yapılıp yapılmadığını test ettim. PUT isteğiyle gönderilen yeni verilerin doğru şekilde güncellendiğini ve yanıt olarak beklenen değerlerin döndüğünü kontrol ettim.

Ürün Silme Testi: Sistemdeki bir ürünü silmek için DELETE isteğini kullandım ve ürün silindiğinde herhangi bir hata mesajı dönmeden başarılı bir silme işlemi gerçekleşip gerçekleşmediğini doğruladım. Mockito’nun doNothing() metodu ile silme işlemini simüle ettim ve başarılı bir şekilde yanıt aldım.

Tüm bu testler, uygulamamın CRUD işlemlerinin sorunsuz çalıştığını ve projenin ihtiyaç duyduğu AOP işlemlerinin düzgün bir şekilde entegre edildiğini gösterdi. Proje boyunca MockMvc, Mockito gibi araçlarla yaptığım entegrasyon testleri sayesinde, uygulamanın hem performansını hem de güvenilirliğini sağlamlaştırmış oldum.

AOP yaklaşımı sayesinde, uygulamanın ana mantığına dokunmadan performans izleme ve loglama gibi işlemleri uygulamaya dahil ettim. Bu proje bana yazılımın sadece işlevselliğini değil, aynı zamanda izlenebilirlik ve bakım kolaylığı gibi özelliklerini de geliştirme fırsatı sundu. Hem performans hem de loglama açısından güçlü bir yapı kurarak, Spring Boot ve AspectJ’in gücünü pratik bir uygulamayla deneyimleme fırsatı buldum.

#### @After
@After dipnotu, belirli bir metodun çalışmasının ardından tetiklenir ve bu, uygulamanın işleyişinin düzgün bir şekilde tamamlandığını doğrulamak için idealdir. Bu sayede, işlem sonrası gerekli temizlik veya loglama gibi işlemleri gerçekleştirerek sistemin şeffaflığını artırır.

#### @Before
@Before dipnotu, hedef metodun çalışmasından önce devreye girer ve bu, ön koşulları kontrol etmek veya önceden yapılandırma yapmak için mükemmel bir fırsat sunar. Böylece, işlemler başlamadan önce gerekli olan tüm ayarlamaların yapıldığından emin olabilirsiniz.

#### @AfterThrowing
@AfterThrowing dipnotu, hedef metodun bir istisna fırlatması durumunda çalışarak hataların izlenmesine ve yönetilmesine olanak tanır. Bu, uygulama güvenilirliğini artırarak hata ayıklama sürecini kolaylaştırır ve kritik hata bilgilerini loglama imkanı sağlar.

#### @Around
@Around dipnotu, bir metodun hem öncesinde hem de sonrasında işlem yapma yeteneği sunarak, metodun çalışma mantığını tamamen kontrol etmenizi sağlar. Bu, performans izleme, işlem sürelerini ölçme ve yanıt döndürmeden önce özel işlemler yapma gibi durumlar için son derece faydalıdır.