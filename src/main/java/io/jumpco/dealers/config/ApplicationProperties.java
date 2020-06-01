package io.jumpco.dealers.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Mtn Dealer Report.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
}
