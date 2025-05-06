package com.miguelcastro.testing.enums;

public enum DocumentTypes {

  CEDULA_CIUDADANIA(1), // Cédula de Ciudadanía (Colombia)
  CEDULA_EXTRANJERIA(2), // Cédula de Extranjería (Colombia)
  TARJETA_IDENTIDAD(3), // Tarjeta de Identidad (Colombia)
  DNI(4), // Documento Nacional de Identidad (España, Argentina, Perú)
  NIE(5), // Número de Identificación de Extranjeros (España)
  CI(6), // Carnet de Identidad (Uruguay, Bolivia, Paraguay)
  NID(7), // National ID (Bangladés, Nigeria)
  PASAPORTE(8), // Pasaporte internacional
  LICENCIA_CONDUCIR(9), // Licencia de Conducir (Global)
  IFE(10), // Credencial para Votar (México)
  INE(11), // Instituto Nacional Electoral (México)
  AADHAAR(12), // Aadhaar (India)
  KTP(13), // Kartu Tanda Penduduk (Indonesia)

  // Documentos Tributarios y Empresariales
  NIT(20), // Número de Identificación Tributaria (Colombia)
  RUT(21), // Rol Único Tributario (Chile)
  RUN(22), // Rol Único Nacional (Chile)
  CPF(23), // Cadastro de Pessoas Físicas (Brasil)
  CNPJ(24), // Cadastro Nacional da Pessoa Jurídica (Brasil)
  NIF(25), // Número de Identificación Fiscal (España)
  RFC(26), // Registro Federal de Contribuyentes (México)
  PAN(27), // Permanent Account Number (India)

  // Documentos de Seguridad Social
  SSN(30), // Social Security Number (Estados Unidos)
  SIN(31), // Social Insurance Number (Canadá)
  CURP(32), // Clave Única de Registro de Población (México)

  // Permisos Especiales
  PERMISO_PROTECCION_TEMPORAL(40), // Permiso por Protección Temporal (Colombia)
  PEP(41), // Permiso Especial de Permanencia (Colombia)
  TARJETA_RESIDENCIA(42), // Tarjeta de Residencia (Varios países)

  OTRO(99); // Otro tipo de documento

  DocumentTypes(int documentType) {
  }
}
