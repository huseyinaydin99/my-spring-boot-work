# spring-batch-faultTolerance - hata toleransı

Bu kodda bir Spring Batch adımı tanımlıyorum ve buna importCustomersStep adını veriyorum. Bu adımda müşteri verilerini okuma, işleme ve yazma işlemlerini gerçekleştiriyorum. Verileri 10'luk parçalara (chunk) bölerek işliyorum, böylece daha yönetilebilir hale geliyor. Ayrıca, hata toleransı sağladım; yani belirli hataları göz ardı edebilirim. skipPolicy ile hangi hataların atlanacağını belirlerken, skipListener ise hataların dinlenmesi ve yönetilmesiyle ilgili işlemleri yapıyor. Ek olarak, adımın performansını artırmak için paralel çalıştırma ve hataları atlama gibi seçenekleri tanımladım, ama bazılarını şu an için devre dışı bıraktım.
BatchStepEventListener sınıfı Customer nesnelerini okurken, yazarken veya işlerken meydana gelen hataları yönetmek için belirli yöntemler içeriyor.

#### onSkipInRead 
Okuma sırasında bir hata oluşursa loglamak için kullandım; bu sayede hatanın mesajını görebiliyorum.
#### onSkipInWrite 
Yazma işlemi sırasında bir sorun çıkarsa, hatanın yanı sıra hangi öğenin yazılamadığını kaydediyorum.
#### onSkipInProcess 
Bir Customer nesnesi işlenirken hata alındığında devreye girmesi için yazdım; burada hatalı öğenin bilgilerini ve hata nedenini JSON formatında logluyorum.
<hr />
Son olarak, @SneakyThrows anotasyonunu kullanarak kontrol edilen istisnaları yönetimini daha kolay hale getirdim, böylece hata yönetiminde ekstra kod yazmama gerek kalmıyor.

#### CustomSkipPolicy Sınıfı
Metodun içinde, hata türüne göre atlama kararını veriyorum. Eğer hata FileNotFoundException ise, bu durumda atlamaya izin vermiyorum, yani işlemi durduruyorum. Ancak eğer hata NumberFormatException ise, atlamaya izin veriyorum. Diğer durumlarda ise atlama yapılmıyor. Bu şekilde, hangi hataların atlanacağını kontrol ederek işlemin daha sağlıklı devam etmesini sağlıyorum.