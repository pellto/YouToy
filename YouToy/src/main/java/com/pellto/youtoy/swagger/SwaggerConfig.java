package com.pellto.youtoy.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  private static final String SECURITY_SCHEME_NAME = "authorization";  // 추가

  @Bean
  public OpenAPI swaggerApi() {
    var openApi = new OpenAPI();

    // server setting
    getServers().forEach(openApi::addServersItem);

    return openApi
        .components(new Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
  }


  // TODO: define remote servers in micro service
  private List<Server> getServers() {
    var ret = new ArrayList<Server>();
    ret.add(getLocalhostServerItem());
    ret.add(getApiGatewayServerItem());
    return ret;
  }

  private Server getLocalhostServerItem() {
    return new Server().url("http://localhost:8080");
  }

  private Server getApiGatewayServerItem() {
    return new Server().url("http://localhost:8000");
  }
}
