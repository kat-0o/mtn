package io.jumpco.dealers.web.rest;

import io.jumpco.dealers.MtnDealerReportApp;
import io.jumpco.dealers.domain.ReportParameter;
import io.jumpco.dealers.repository.ReportParameterRepository;
import io.jumpco.dealers.service.ReportParameterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.jumpco.dealers.domain.enumeration.ParameterType;
import io.jumpco.dealers.domain.enumeration.ParameterDataType;
/**
 * Integration tests for the {@link ReportParameterResource} REST controller.
 */
@SpringBootTest(classes = MtnDealerReportApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReportParameterResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ParameterType DEFAULT_TYPE = ParameterType.OPTIONAL;
    private static final ParameterType UPDATED_TYPE = ParameterType.REQUIRED;

    private static final ParameterDataType DEFAULT_DATA_TYPE = ParameterDataType.STRING;
    private static final ParameterDataType UPDATED_DATA_TYPE = ParameterDataType.NUMBER;

    private static final String DEFAULT_DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_VALUE = "BBBBBBBBBB";

    @Autowired
    private ReportParameterRepository reportParameterRepository;

    @Autowired
    private ReportParameterService reportParameterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReportParameterMockMvc;

    private ReportParameter reportParameter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportParameter createEntity(EntityManager em) {
        ReportParameter reportParameter = new ReportParameter()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .dataType(DEFAULT_DATA_TYPE)
            .defaultValue(DEFAULT_DEFAULT_VALUE);
        return reportParameter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReportParameter createUpdatedEntity(EntityManager em) {
        ReportParameter reportParameter = new ReportParameter()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .defaultValue(UPDATED_DEFAULT_VALUE);
        return reportParameter;
    }

    @BeforeEach
    public void initTest() {
        reportParameter = createEntity(em);
    }

    @Test
    @Transactional
    public void createReportParameter() throws Exception {
        int databaseSizeBeforeCreate = reportParameterRepository.findAll().size();
        // Create the ReportParameter
        restReportParameterMockMvc.perform(post("/api/report-parameters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportParameter)))
            .andExpect(status().isCreated());

        // Validate the ReportParameter in the database
        List<ReportParameter> reportParameterList = reportParameterRepository.findAll();
        assertThat(reportParameterList).hasSize(databaseSizeBeforeCreate + 1);
        ReportParameter testReportParameter = reportParameterList.get(reportParameterList.size() - 1);
        assertThat(testReportParameter.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testReportParameter.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testReportParameter.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testReportParameter.getDataType()).isEqualTo(DEFAULT_DATA_TYPE);
        assertThat(testReportParameter.getDefaultValue()).isEqualTo(DEFAULT_DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createReportParameterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportParameterRepository.findAll().size();

        // Create the ReportParameter with an existing ID
        reportParameter.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportParameterMockMvc.perform(post("/api/report-parameters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportParameter)))
            .andExpect(status().isBadRequest());

        // Validate the ReportParameter in the database
        List<ReportParameter> reportParameterList = reportParameterRepository.findAll();
        assertThat(reportParameterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReportParameters() throws Exception {
        // Initialize the database
        reportParameterRepository.saveAndFlush(reportParameter);

        // Get all the reportParameterList
        restReportParameterMockMvc.perform(get("/api/report-parameters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportParameter.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].dataType").value(hasItem(DEFAULT_DATA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].defaultValue").value(hasItem(DEFAULT_DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getReportParameter() throws Exception {
        // Initialize the database
        reportParameterRepository.saveAndFlush(reportParameter);

        // Get the reportParameter
        restReportParameterMockMvc.perform(get("/api/report-parameters/{id}", reportParameter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reportParameter.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.dataType").value(DEFAULT_DATA_TYPE.toString()))
            .andExpect(jsonPath("$.defaultValue").value(DEFAULT_DEFAULT_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingReportParameter() throws Exception {
        // Get the reportParameter
        restReportParameterMockMvc.perform(get("/api/report-parameters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReportParameter() throws Exception {
        // Initialize the database
        reportParameterService.save(reportParameter);

        int databaseSizeBeforeUpdate = reportParameterRepository.findAll().size();

        // Update the reportParameter
        ReportParameter updatedReportParameter = reportParameterRepository.findById(reportParameter.getId()).get();
        // Disconnect from session so that the updates on updatedReportParameter are not directly saved in db
        em.detach(updatedReportParameter);
        updatedReportParameter
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .dataType(UPDATED_DATA_TYPE)
            .defaultValue(UPDATED_DEFAULT_VALUE);

        restReportParameterMockMvc.perform(put("/api/report-parameters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedReportParameter)))
            .andExpect(status().isOk());

        // Validate the ReportParameter in the database
        List<ReportParameter> reportParameterList = reportParameterRepository.findAll();
        assertThat(reportParameterList).hasSize(databaseSizeBeforeUpdate);
        ReportParameter testReportParameter = reportParameterList.get(reportParameterList.size() - 1);
        assertThat(testReportParameter.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testReportParameter.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testReportParameter.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testReportParameter.getDataType()).isEqualTo(UPDATED_DATA_TYPE);
        assertThat(testReportParameter.getDefaultValue()).isEqualTo(UPDATED_DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingReportParameter() throws Exception {
        int databaseSizeBeforeUpdate = reportParameterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportParameterMockMvc.perform(put("/api/report-parameters")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reportParameter)))
            .andExpect(status().isBadRequest());

        // Validate the ReportParameter in the database
        List<ReportParameter> reportParameterList = reportParameterRepository.findAll();
        assertThat(reportParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReportParameter() throws Exception {
        // Initialize the database
        reportParameterService.save(reportParameter);

        int databaseSizeBeforeDelete = reportParameterRepository.findAll().size();

        // Delete the reportParameter
        restReportParameterMockMvc.perform(delete("/api/report-parameters/{id}", reportParameter.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReportParameter> reportParameterList = reportParameterRepository.findAll();
        assertThat(reportParameterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
