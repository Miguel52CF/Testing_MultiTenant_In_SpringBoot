package com.miguelcastro.testing.config.tenant;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class TenantFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    // Aquí puedes obtener el tenant desde el encabezado de la solicitud, por
    // ejemplo
    String tenant = httpRequest.getHeader("X-Tenant-ID");

    if (tenant != null) {
      TenantContext.setCurrentTenant(tenant);
    }

    chain.doFilter(request, response);

    // Limpiar el contexto después de procesar la solicitud
    TenantContext.clear();
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void destroy() {
  }

}
