# validation-exception-handling

Bu projede validation exception handling mekanizmalarını inceledim. Kullanıcıdan gelen verilerin doğrulanması sürecinde, hatalı veriler için özel hata mesajları ürettim. Hataların merkezi olarak yönetilmesi için global exception handler kullandım. Bu sayede uygulamanın daha güvenilir ve kullanıcı dostu hale gelmesini sağladım.
Spring Boot projelerinde validation exception handling ile AOP arasında güçlü bir ilişki var. AOP, exception handling sürecini merkezi bir noktada ele alarak, kod tekrarını önler ve uygulama genelinde tutarlı bir hata yönetimi sağlar.