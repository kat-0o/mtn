package io.jumpco.dealers.web.rest;

import io.jumpco.dealers.domain.ReportParameter;
import io.jumpco.dealers.service.ReportParameterService;
import io.jumpco.dealers.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.jumpco.dealers.domain.ReportParameter}.
 */
@RestController
@RequestMapping("/api")
public class ReportParameterResource {

    private final Logger log = LoggerFactory.getLogger(ReportParameterResource.class);

    private static final String ENTITY_NAME = "reportParameter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportParameterService reportParameterService;

    public ReportParameterResource(ReportParameterService reportParameterService) {
        this.reportParameterService = reportParameterService;
    }

    /**
     * {@code POST  /report-parameters} : Create a new reportParameter.
     *
     * @param reportParameter the reportParameter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportParameter, or with status {@code 400 (Bad Request)} if the reportParameter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/report-parameters")
    public ResponseEntity<ReportParameter> createReportParameter(@RequestBody ReportParameter reportParameter) throws URISyntaxException {
        log.debug("REST request to save ReportParameter : {}", reportParameter);
        if (reportParameter.getId() != null) {
            throw new BadRequestAlertException("A new reportParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportParameter result = reportParameterService.save(reportParameter);
        return ResponseEntity.created(new URI("/api/report-parameters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-parameters} : Updates an existing reportParameter.
     *
     * @param reportParameter the reportParameter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportParameter,
     * or with status {@code 400 (Bad Request)} if the reportParameter is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportParameter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/report-parameters")
    public ResponseEntity<ReportParameter> updateReportParameter(@RequestBody ReportParameter reportParameter) throws URISyntaxException {
        log.debug("REST request to update ReportParameter : {}", reportParameter);
        if (reportParameter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportParameter result = reportParameterService.save(reportParameter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reportParameter.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /report-parameters} : get all the reportParameters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportParameters in body.
     */
    @GetMapping("/report-parameters")
    public List<ReportParameter> getAllReportParameters() {
        log.debug("REST request to get all ReportParameters");
        return reportParameterService.findAll();
    }

    /**
     * {@code GET  /report-parameters/:id} : get the "id" reportParameter.
     *
     * @param id the id of the reportParameter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportParameter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/report-parameters/{id}")
    public ResponseEntity<ReportParameter> getReportParameter(@PathVariable Long id) {
        log.debug("REST request to get ReportParameter : {}", id);
        Optional<ReportParameter> reportParameter = reportParameterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportParameter);
    }

    /**
     * {@code DELETE  /report-parameters/:id} : delete the "id" reportParameter.
     *
     * @param id the id of the reportParameter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/report-parameters/{id}")
    public ResponseEntity<Void> deleteReportParameter(@PathVariable Long id) {
        log.debug("REST request to delete ReportParameter : {}", id);

        reportParameterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
