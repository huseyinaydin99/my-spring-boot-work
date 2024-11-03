# spring-data-jasypt

```
mvn jasypt:encrypt-value -Djasypt.encryptor.password=huseyin -Djasypt.plugin.value=

mvn jasypt:decrypt-value -Djasypt.encryptor.password=huseyin -Djasypt.plugin.value=evRc/ux7kFgQbjocJtqAy/+XRyHWZxUWXutdiytTVs+Ghv+DwP6gOyRmURi4byxo

mvn jasypt:encrypt -Djasypt.encryptor.password=toor

mvn jasypt:encrypt -Djasypt.encryptor.password=toor -Djasypt.plugin.path="file:src/main/resources/application.yml"
```

Bugün projede hassas bilgileri güvenli hale getirmek için Jasypt’i kullandım. Önce jasypt-maven-plugin eklentisini Maven yapılandırmasına ekledim, böylece şifreleme ve çözme işlemlerini kolaylaştırmış oldum. Şifreleme işlemi için PooledPBEStringEncryptor nesnesini oluşturdum ve SimpleStringPBEConfig ile gerekli ayarları yaptım. Gizli anahtar olarak "toor" ifadesini kullanarak verilerin güvenliğini artırdım. Ayrıca, PBEWithMD5AndDES algoritmasını tercih ettim, çünkü güçlü ve yaygın olarak kullanılıyor. application.properties dosyasında yer alan hassas bilgileri şifreleyip, dosyada güvenli bir şekilde saklanmasını sağladım. Şifreleme ve çözme işlemlerini test etmek için mvn jasypt:encrypt ve mvn jasypt:decrypt komutlarını çalıştırdım. Tüm bu işlemler sonucunda, projemdeki gizli bilgilerin korunduğundan emin oldum.