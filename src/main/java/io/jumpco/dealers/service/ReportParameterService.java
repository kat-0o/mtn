package io.jumpco.dealers.service;

import io.jumpco.dealers.domain.ReportParameter;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ReportParameter}.
 */
public interface ReportParameterService {

    /**
     * Save a reportParameter.
     *
     * @param reportParameter the entity to save.
     * @return the persisted entity.
     */
    ReportParameter save(ReportParameter reportParameter);

    /**
     * Get all the reportParameters.
     *
     * @return the list of entities.
     */
    List<ReportParameter> findAll();


    /**
     * Get the "id" reportParameter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportParameter> findOne(Long id);

    /**
     * Delete the "id" reportParameter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
