package no.itera.assignment.configuration;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ConfigurableHttpMethods;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@RequiredArgsConstructor
public class DataRestConfiguration implements RepositoryRestConfigurer {

  private final EntityManager entityManager;

  @Override
  public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration config, CorsRegistry cors) {
    config.exposeIdsFor(
        entityManager.getMetamodel().getEntities().stream()
            .map(Type::getJavaType)
            .toArray(Class[]::new));

    ExposureConfiguration exposureConfiguration = config.getExposureConfiguration();
    exposureConfiguration
        .withCollectionExposure((metadata, httpMethods) -> getDisabledHttpMethods(httpMethods));
    exposureConfiguration
        .withItemExposure((metadata, httpMethods) -> getDisabledHttpMethods(httpMethods));
    exposureConfiguration
        .withAssociationExposure((metadata, httpMethods) -> getDisabledHttpMethods(httpMethods));
  }

  private ConfigurableHttpMethods getDisabledHttpMethods(ConfigurableHttpMethods httpMethods) {
    return httpMethods
        .disable(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH);
  }
}
