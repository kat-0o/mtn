package io.jumpco.dealers.service;

import io.jumpco.dealers.domain.UserProfile;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link UserProfile}.
 */
public interface UserProfileService {

    /**
     * Save a userProfile.
     *
     * @param userProfile the entity to save.
     * @return the persisted entity.
     */
    UserProfile save(UserProfile userProfile);

    /**
     * Get all the userProfiles.
     *
     * @return the list of entities.
     */
    List<UserProfile> findAll();


    /**
     * Get the "id" userProfile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserProfile> findOne(Long id);

    /**
     * Delete the "id" userProfile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
