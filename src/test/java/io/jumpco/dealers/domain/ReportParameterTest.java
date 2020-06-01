package io.jumpco.dealers.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.jumpco.dealers.web.rest.TestUtil;

public class ReportParameterTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportParameter.class);
        ReportParameter reportParameter1 = new ReportParameter();
        reportParameter1.setId(1L);
        ReportParameter reportParameter2 = new ReportParameter();
        reportParameter2.setId(reportParameter1.getId());
        assertThat(reportParameter1).isEqualTo(reportParameter2);
        reportParameter2.setId(2L);
        assertThat(reportParameter1).isNotEqualTo(reportParameter2);
        reportParameter1.setId(null);
        assertThat(reportParameter1).isNotEqualTo(reportParameter2);
    }
}
