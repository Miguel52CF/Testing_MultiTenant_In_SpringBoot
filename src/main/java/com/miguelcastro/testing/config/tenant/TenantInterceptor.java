package com.miguelcastro.testing.config.tenant;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String tenant = request.getHeader("X-Tenant-ID");

    // Si se encuentra un tenant, lo configuramos en el contexto
    if (tenant != null) {
      TenantContext.setCurrentTenant(tenant);
    } else {
      // Si no se encuentra un tenant, se puede manejar de alguna manera (p.e., lanzar
      // excepción)
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("Tenant ID is missing");
      return false;
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // Aquí puedes hacer post procesamiento si es necesario
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    // Limpiar el contexto después de la solicitud
    TenantContext.clear();
  }

}
