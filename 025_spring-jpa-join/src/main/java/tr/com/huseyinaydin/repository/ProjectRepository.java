package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.bo.ProjectEngineerResponseBO;
import tr.com.huseyinaydin.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface ProjectRepository extends JpaRepository<Project,Integer> {

    //Saf SQL
    @Query(value = "select new tr.com.huseyinaydin.bo.ProjectEngineerResponseBO(p.name as  project_name, p.project_code, e.name as engineer_name, e.email) from Project p join Engineer e on p.project_id = e.project_engineer_fk", nativeQuery = true)
    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithSQL();

    //Java Persistance Query Language
    @Query(value = "SELECT new tr.com.huseyinaydin.bo.ProjectEngineerResponseBO(p.name , p.projectCode , e.name , e.email) FROM Project p JOIN p.engineers e")
    public List<ProjectEngineerResponseBO> getProjectSpecificInfoWithJPQL();
}

/*
SQL Sorgusu (getProjectSpecificInfoWithSQL)
İlk sorguda, saf SQL kullanarak Project ve Engineer tablolarını birleştirdim.
ProjectEngineerResponseBO sınıfını kullanarak, sadece belirli bilgileri
(proje adı, proje kodu, mühendis adı ve e-posta) çekmeyi sağladım.
JOIN ile Project ve Engineer tablolarını project_id ve project_engineer_fk alanları üzerinden birleştirdim.
Böylece her projeye bağlı tüm mühendislerin bilgilerini tek seferde almış oldum.

JPQL Sorgusu (getProjectSpecificInfoWithJPQL)
İkinci sorguda, JPQL kullanarak Project entity'si üzerinde engineers ilişkisini kullandım.
ProjectEngineerResponseBO sınıfına p.name, p.projectCode, e.name, e.email alanlarını geçirerek
ihtiyacım olan veriyi aldım. Bu sayede, Project ve Engineer entity'leri arasındaki OneToMany
ilişkiyi doğrudan JPQL ile kullandım ve veritabanına özel SQL yazmak yerine
JPA’nın sağladığı ilişki yönetimi olanaklarından faydalandım.

Özetle, her iki sorguda da proje ve mühendis bilgilerini birleştirip tek seferde çekmiş oldum;
SQL’de doğrudan native sorgu yazarken, JPQL’de ilişkileri kullanarak sorguladım.

Genelde saf SQL daha performanslıdır.
*/