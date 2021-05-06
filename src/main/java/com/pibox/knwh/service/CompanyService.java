package com.pibox.knwh.service;

import com.pibox.knwh.entity.DTO.CompanyDTO;

import java.util.List;

public interface CompanyService {

    void createCompany(CompanyDTO companyDTO);

    CompanyDTO findCompanyById(Long id);

    List<CompanyDTO> findAllCompanies();

    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);

    void deleteCompanyById(Long id);
}
