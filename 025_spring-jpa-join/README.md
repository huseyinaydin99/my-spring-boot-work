# spring-jpa-join

ProjectManagementService sınıfını oluşturup ProjectRepository ve EngineerRepository bileşenlerini @Autowired ile enjekte ettim. saveProject metodu ile yeni bir proje kaydediyorum, getProjects ve getEngineers metodlarıyla tüm projeleri ve mühendisleri listeliyorum. deleteProject metodu ile belirli bir proje ID'sini kullanarak projeyi siliyorum. Ayrıca, getProjectSpecificInfoSQL ve getProjectSpecificInfoJPQL metodlarıyla, SQL ve JPQL kullanarak proje ve mühendis bilgilerini birleştirerek özel verileri çekiyorum.

```java
    //Saf SQL
    @Query(value = "select new tr.com.huseyinaydin.bo.ProjectEngineerResponseBO(p.name as  project_name, p.project_code, e.name as engineer_name, e.email) from Project p join Engineer e on p.project_id = e.project_engineer_fk", nativeQuery = true)
    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithSQL();
```
SQL Sorgusu (getProjectSpecificInfoWithSQL)
İlk sorguda, saf SQL kullanarak Project ve Engineer tablolarını birleştirdim. ProjectEngineerResponseBO sınıfını kullanarak, sadece belirli bilgileri (proje adı, proje kodu, mühendis adı ve e-posta) çekmeyi sağladım. JOIN ile Project ve Engineer tablolarını project_id ve project_engineer_fk alanları üzerinden birleştirdim. Böylece her projeye bağlı tüm mühendislerin bilgilerini tek seferde almış oldum.

```java
    //Java Persistance Query Language
    @Query(value = "SELECT new tr.com.huseyinaydin.bo.ProjectEngineerResponseBO(p.name , p.projectCode , e.name , e.email) FROM Project p JOIN p.engineers e")
    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithJPQL();
```
JPQL Sorgusu (getProjectSpecificInfoWithJPQL)
İkinci sorguda, JPQL kullanarak Project entity'si üzerinde engineers ilişkisini kullandım. ProjectEngineerResponseBO sınıfına p.name, p.projectCode, e.name, e.email alanlarını geçirerek ihtiyacım olan veriyi aldım. Bu sayede, Project ve Engineer entity'leri arasındaki OneToMany ilişkiyi doğrudan JPQL ile kullandım ve veritabanına özel SQL yazmak yerine JPA’nın sağladığı ilişki yönetimi olanaklarından faydalandım.

Özetle, her iki sorguda da proje ve mühendis bilgilerini birleştirip tek seferde çekmiş oldum; SQL’de doğrudan native sorgu yazarken, JPQL’de ilişkileri kullanarak sorguladım.
Genelde saf SQL daha performanslıdır.
