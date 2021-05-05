package com.pibox.knwh.service;

import com.pibox.knwh.entity.DTO.CompanyDTO;
import javassist.NotFoundException;

import java.util.List;

public interface CompanyService {

    void createCompany(CompanyDTO companyDTO);

    CompanyDTO findCompanyById(Long id);

    List<CompanyDTO> findAllCompanies();

    void deleteCompanyById(Long id) throws NotFoundException;
}
