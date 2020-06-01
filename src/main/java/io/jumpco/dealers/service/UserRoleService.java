package io.jumpco.dealers.service;

import io.jumpco.dealers.domain.UserRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link UserRole}.
 */
public interface UserRoleService {

    /**
     * Save a userRole.
     *
     * @param userRole the entity to save.
     * @return the persisted entity.
     */
    UserRole save(UserRole userRole);

    /**
     * Get all the userRoles.
     *
     * @return the list of entities.
     */
    List<UserRole> findAll();

    /**
     * Get all the userRoles with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<UserRole> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" userRole.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserRole> findOne(Long id);

    /**
     * Delete the "id" userRole.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
