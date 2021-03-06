package io.jumpco.dealers.repository;

import io.jumpco.dealers.domain.UserRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the UserRole entity.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "select distinct userRole from UserRole userRole left join fetch userRole.users",
        countQuery = "select count(distinct userRole) from UserRole userRole")
    Page<UserRole> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct userRole from UserRole userRole left join fetch userRole.users")
    List<UserRole> findAllWithEagerRelationships();

    @Query("select userRole from UserRole userRole left join fetch userRole.users where userRole.id =:id")
    Optional<UserRole> findOneWithEagerRelationships(@Param("id") Long id);
}
