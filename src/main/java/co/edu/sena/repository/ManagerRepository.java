package co.edu.sena.repository;

import co.edu.sena.domain.Manager;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Manager entity.
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    default Optional<Manager> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Manager> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Manager> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct manager from Manager manager left join fetch manager.user",
        countQuery = "select count(distinct manager) from Manager manager"
    )
    Page<Manager> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct manager from Manager manager left join fetch manager.user")
    List<Manager> findAllWithToOneRelationships();

    @Query("select manager from Manager manager left join fetch manager.user where manager.id =:id")
    Optional<Manager> findOneWithToOneRelationships(@Param("id") Long id);
}
