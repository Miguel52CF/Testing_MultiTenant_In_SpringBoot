package com.miguelcastro.testing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.miguelcastro.testing.config.tenant.TenantInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final TenantInterceptor tenantInterceptor;

  @Autowired
  public WebConfig(TenantInterceptor tenantInterceptor) {
    this.tenantInterceptor = tenantInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // Registramos el TenantInterceptor para interceptar todas las solicitudes
    registry.addInterceptor(tenantInterceptor).addPathPatterns("/**");
  }

}
