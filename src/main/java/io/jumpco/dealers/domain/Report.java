package io.jumpco.dealers.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import io.jumpco.dealers.domain.enumeration.ReportType;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Report implements Serializable {

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
    private ReportType type;

    @Column(name = "last_update")
    private ZonedDateTime lastUpdate;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "config")
    private String config;

    @OneToMany(mappedBy = "report")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "report")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ReportParameter> reportParameters = new HashSet<>();

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

    public Report name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Report description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportType getType() {
        return type;
    }

    public Report type(ReportType type) {
        this.type = type;
        return this;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public Report lastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getConfig() {
        return config;
    }

    public Report config(String config) {
        this.config = config;
        return this;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public Report roles(Set<UserRole> userRoles) {
        this.roles = userRoles;
        return this;
    }

    public Report addRole(UserRole userRole) {
        this.roles.add(userRole);
        userRole.setReport(this);
        return this;
    }

    public Report removeRole(UserRole userRole) {
        this.roles.remove(userRole);
        userRole.setReport(null);
        return this;
    }

    public void setRoles(Set<UserRole> userRoles) {
        this.roles = userRoles;
    }

    public Set<ReportParameter> getReportParameters() {
        return reportParameters;
    }

    public Report reportParameters(Set<ReportParameter> reportParameters) {
        this.reportParameters = reportParameters;
        return this;
    }

    public Report addReportParameter(ReportParameter reportParameter) {
        this.reportParameters.add(reportParameter);
        reportParameter.setReport(this);
        return this;
    }

    public Report removeReportParameter(ReportParameter reportParameter) {
        this.reportParameters.remove(reportParameter);
        reportParameter.setReport(null);
        return this;
    }

    public void setReportParameters(Set<ReportParameter> reportParameters) {
        this.reportParameters = reportParameters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report)) {
            return false;
        }
        return id != null && id.equals(((Report) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Report{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", lastUpdate='" + getLastUpdate() + "'" +
            ", config='" + getConfig() + "'" +
            "}";
    }
}
