# spring-data-redis-db

Spring Boot ve Redis NoSQL cache kullanarak bir ön bellekleme projesi geliştirdim. Projede, müşteri bilgilerini yönetmek için Customer sınıfını oluşturup, Redis ile entegre ettim ve bu sınıfın nesnelerini @RedisHash("CustomersInfo") anotasyonu ile Redis üzerinde depoladım. Müşteri CRUD işlemlerini gerçekleştiren CustomerService sınıfında, Redis cache anotasyonlarını (@Cacheable, @CachePut, @CacheEvict) kullanarak verilerin hızlı erişimini sağladım. Ayrıca, CustomerDAO ve CustomerRepository sınıflarını kullanarak Redis üzerinden müşteri verilerine erişim ve yönetim işlemlerini yönettim. CustomerController sınıfında ise API istekleriyle müşteri bilgilerini kaydetme, listeleme, güncelleme ve silme işlemlerini gerçekleştirdim. Redis sayesinde sık kullanılan verilere hızlı bir şekilde erişim sağladım, böylece uygulamanın performansını artırdım ve veri tabanı yükünü azalttım.
Projede ön bellekleme işlemleri için daha eski bir yöntem olan DAO yapısını kullandım ve RedisTemplate ile doğrudan Redis işlemlerini yönettim. Ancak, Spring Boot’ta cache işlemlerini @Cacheable, @CachePut ve @CacheEvict gibi anotasyonlarla ve RedisRepository kullanarak yapmanın daha modern ve anlaşılır bir yol olduğunu biliyorum. Bu anotasyonlar sayesinde veriyi kaydetme, güncelleme ve silme işlemleri daha kolay yönetilebiliyor. Projede DAO yerine doğrudan cache anotasyonlarını kullanarak kodu sadeleştirip bakımını daha kolay hale getirmeyi tercih ettim.

POST İsteği :

```
curl --location --request POST 'http://localhost:9191/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "firstName": "Hüseyin",
    "lastName": "AYDIN",
    "email": "huseyinaydin99@gmail.com",
    "dob": "01/01/1453",
    "phone": "0555 555 55 54"
}'
```

PUT İsteği 

```
curl --location --request PUT 'http://localhost:9191/customers/2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Osman",
    "lastName": "Gazi",
    "email": "osmangazi99@gmail.com",
    "dob": "01/01/1990",
    "phone": "0554 554 54 54"
}'
```

Remote Dictionary Service kelimelerinin kısaltmasıdır Redis. C programlama dili ile yazılmıştır. Çıkış noktası hızdır. Key(anahtar)- value(değer) eşlemi şeklinde tasarlanmış NoSQL türünde veritabanıdır. InMemory Database yaklaşımını benimser yani verileri RAM(Random Access Memory) bellekte tutar. Fakat durability diye bir özelliğide vardır. Durability yani disk üzerine veri yazabilme özelliği opsiyonel(isteğe bağlı)dır. Redis çok gelişmiş bir Key-Value eşlem deposudur.


Desteklenen veri türleri; String, Hash, List, Set, SortedSet, Geopatial Index, HyperLog gibi verileri tutar.


Nerelerde kullanılır:

1- Database(Redis sakladığı veriyi disk üzerinde tutabilir - Persist).

2- Caching Layer(ön bellekleme katmanı): Sitemizin cache’ini yapmak istiyorsak Redis çok hızlı olduğu için avantaj sağlar.


3- Message Broker: Redis sadece Key-Value eşlemi olarak çalışmaz. RabbitMQ gibi kullanılabilir.


Redis ne değildir?

Releational Database(ilişkisel veritabanı) yahut Document(döküman[JSON]) bazlı sistemlerin alternatifi değildir. Aksine bu DB türlerinin ilişkilerini tanımlayacak katman olarak kullanılabilir. En iyi kullanım alanı ise; ön görülebilir DB boyutuyla hızla büyüyen veriler.


Use Case(kullanım durumları)’ler neler?

Cache(ön bellek) mekanizmaları Pub/Sub kuyruklarının engellenmesi ve geciktirilmesinde, kısa canlı verilerde(fraud detection-dolandırıcılık tespiti, oturum kontrolleri, filtreleme servisleri), yorum sayımlarında, her defasında unique(benzersiz) verilerin saklanmasında kullanılır.

Redis’i ne zaman kullanmalıyız?

Çok yüksek hıza ihtiyaç duyduğumuzda, key-value çiftinden daha fazlasına ihtiyaç duyulduğunda, dataset’in kritik olmadığı yerlerde kullanılabilir.

Kullanırken dikkat edilmesi gereken hususlar: Redis bellek üzerinde verileri saklar, bundan dolayı çok fazla bellek kısıtlaması vardır. Boş bir Instance(örnek) 1MB bellek kullanır. 1 milyon küçük key-value string eşlemi 100MB yer kullanır. 1 milyon küçük key-value 5 field’den oluşan hash çifti 200MB yer kullanır.

Performans?

Redis’e bir veriyi gönderirsek asenkron olarak çalışmadığı için her verdiğimiz veriyi teker teker bitirecektir.

Pipeline?

Pipeline işleri toplar hepsini bir anda yapar. Otomatik olarak hızı arttırır. Pipeline açıksa 200.000 tane Request’i 1 milisaniyenin altında işler. Memcached ile 1 saniyede 80.000 işi yapar. MySQL ile kıyaslarsak MySQL 100 milisaniyede 900 iş yapar. Redis MySQL’den çooook hızlıdır.


Scaling(ölçekledirme):

1- Persistance: Redis persistance(kalıcılık[diske yazma]) için 2 farklı mekanizma sağlar.

2- Replication(çoğaltma): 1 Redis instance Master(bir cluster’daki ana makina) olarak kullanılır. Diğer instance’ler ise Slave(bir cluster’daki ana makinanın yönettiği köle) olarak gelir ve bunlar Master’in kopyalarıdır. Client(istemci) bu Slave yada Master Instance’lerden birine bağlanır. Slave’ler default(varsayılan) olarak sadece okuma işlemi yaparlar.

3- Partitioning(bölümleme): Veriyi bölebilir ve paylaştırabilirsiniz.

4- Failover: Çökme, elektrik kesintisi gibi durumlarda Master-Slave topoloji için Redis Sentinel, Cluster topoloji için Redis Cluster kullanılır.


İndirme ve Kurma İşlemleri: adresinden indirebilirsiniz. Eğer dilerseniz Docker üzerinden de kullanabilirsiniz sıkıntı yok. Portainer’i Docker üzerine kurarsanız Portainer arayüzü üzerinden yönetmek çok daha kolay olur.


Docker’ı ve Docker üzerinden Redis Image’sini kurduğunuzu var sayıyorum.

Terminal üzerinden kullanım şekli:

````bash
$: cd /Users/huseyinaydin/utils/redis.6.0.0/ //ilgili klasöre gider konumlanır, yerleşir.
$: clear //terminali temizleyelim.
$: ls //bulunduğumuz klasörü listeler.
$: make //kurma komutudur.
$: make test //test
$: cd src //src klasörüne konumlanır.
$: ./redis-server
$: ./redis-cli //komut satırına girdik Redis in
$: KEYS * //tüm keyleri yani anahtarları listeler.
$: SET name Huseyin //name isimli değişkenin yani keyin değerini Huseyin olarak atama yapar.
$: KEYS * //tüm keyleri yani anahtarları listeler.
$: SET lastname Aydin //lastname(soyad) keyinin değerini Aydin olarak set eder ayarlar, yani atar.
$: SET age 28 //age yani yaş keyinin değerini 28 yaptık.
$: KEYS * //tüm keyleri yani anahtarları listeler.
$: GET lastname //soyad yani lastname keyinin değerini ekrana basar, getirir, okur.
$: GET name //ad yani name keyinin değerini ekrana basar, getirir, okur.
$: GET age //yaş yani age keyinin değerini ekrana basar, getirir, okur.
$: EXISTS lastname //lastname diye bir key var mı yok mu kontrol eder. Varsa 1 yoksa 0 döner.
$: DEL lastname //lastname isimli keyi komple bellekten siler atar.
$: KEYS * //tüm keyleri listeler.
$: APPEND user Huseyin //user keyine Huseyin değerini ekler.
$: APPEND user Aydin //user keyine Aydin değerini ekler. Boşluk eklemek için çift tırnak kullanıyoruz.
$: GET user //user keyini okur, ekrana basar.
$: HSET user1 name Huseyin //user1 bir obje instancesidir. içinde name filedi var. Değeride Huseyin. Anladın?
$: HSET user1 lastname Aydin //user1 obje instancesidir. içinde lastname fieldi var. Değer olarak  Aydin değerini tutuyor.
$: KEYS * //tüm keyleri listeler.
$: GET user1 //hatalıdır. obje GET ile direk get edilemez. HSET ile set edildiğinden dolayı HGETle get edilir.
$: HGET user1 name //user1 objesinin name fieldini getirir okur, ekrana basar.
$: HGETALL user1 //user1 nesnesinin tüm filedleri ile birlikte okur ve ekrana basar.
$: SET name EX 10 //EX geçerlilik süresi demektir(Expire). 10 saniye sonra silinir. Belirtilen süre kadar bellekte varlığını korur sonra kendini imha eder.
$: SUBSCRIBE huso //huso kanalına abone olduk.
2. bir terminal açalım
   $: SUBSCRIBE huso //huso kanalına abone olduk.
3. bir terminal açalaım
   $: PUBLISH huso selamlar nasılsınız??
   //Abone olan terminallere otomatik selamlar nasılsınız?? değeri yansır.
   Redis ile Docker Kullanımı
   $: docker images //docker image(yansıma)lerini gösterir, listeler.
   $: docker run --name redisapp -p 6379:6379 redis //redis isimli imageyi pull eder indirir.
   $: docker run redis //
   $: docker exec -it modes_hopper redis_cli //redis containerinin komut satırına girer.
   // -it interactive terminal demektir. İlgili container üzerinde yani server üzerinde komut çalıştırmamıza yani serverin terminaline erişmeye yarar.
   $: ps -ef | grep -i redis //tüm proseslerin içinde filtreme yaparal redis kelimesi geçenleri bulur.
   //Durability açık olduğundan dolayı restart atsak bile veriler silinmiyor.
   $: FLUSHALL //tüm keyleri siler.
````