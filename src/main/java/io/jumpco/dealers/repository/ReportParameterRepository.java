package io.jumpco.dealers.repository;

import io.jumpco.dealers.domain.ReportParameter;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReportParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportParameterRepository extends JpaRepository<ReportParameter, Long> {
}
