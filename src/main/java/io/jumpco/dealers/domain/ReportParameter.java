package io.jumpco.dealers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import io.jumpco.dealers.domain.enumeration.ParameterType;

import io.jumpco.dealers.domain.enumeration.ParameterDataType;

/**
 * A ReportParameter.
 */
@Entity
@Table(name = "report_parameter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReportParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ParameterType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_type")
    private ParameterDataType dataType;

    @Column(name = "default_value")
    private String defaultValue;

    @ManyToOne
    @JsonIgnoreProperties(value = "reportParameters", allowSetters = true)
    private Report report;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ReportParameter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ReportParameter description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParameterType getType() {
        return type;
    }

    public ReportParameter type(ParameterType type) {
        this.type = type;
        return this;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public ParameterDataType getDataType() {
        return dataType;
    }

    public ReportParameter dataType(ParameterDataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public void setDataType(ParameterDataType dataType) {
        this.dataType = dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public ReportParameter defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Report getReport() {
        return report;
    }

    public ReportParameter report(Report report) {
        this.report = report;
        return this;
    }

    public void setReport(Report report) {
        this.report = report;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportParameter)) {
            return false;
        }
        return id != null && id.equals(((ReportParameter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportParameter{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", dataType='" + getDataType() + "'" +
            ", defaultValue='" + getDefaultValue() + "'" +
            "}";
    }
}
