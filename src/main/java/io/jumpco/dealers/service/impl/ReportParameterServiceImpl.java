package io.jumpco.dealers.service.impl;

import io.jumpco.dealers.service.ReportParameterService;
import io.jumpco.dealers.domain.ReportParameter;
import io.jumpco.dealers.repository.ReportParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ReportParameter}.
 */
@Service
@Transactional
public class ReportParameterServiceImpl implements ReportParameterService {

    private final Logger log = LoggerFactory.getLogger(ReportParameterServiceImpl.class);

    private final ReportParameterRepository reportParameterRepository;

    public ReportParameterServiceImpl(ReportParameterRepository reportParameterRepository) {
        this.reportParameterRepository = reportParameterRepository;
    }

    /**
     * Save a reportParameter.
     *
     * @param reportParameter the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReportParameter save(ReportParameter reportParameter) {
        log.debug("Request to save ReportParameter : {}", reportParameter);
        return reportParameterRepository.save(reportParameter);
    }

    /**
     * Get all the reportParameters.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReportParameter> findAll() {
        log.debug("Request to get all ReportParameters");
        return reportParameterRepository.findAll();
    }


    /**
     * Get one reportParameter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReportParameter> findOne(Long id) {
        log.debug("Request to get ReportParameter : {}", id);
        return reportParameterRepository.findById(id);
    }

    /**
     * Delete the reportParameter by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportParameter : {}", id);

        reportParameterRepository.deleteById(id);
    }
}
