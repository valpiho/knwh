package com.pibox.knwh.service;

import com.pibox.knwh.entity.Company;
import javassist.NotFoundException;

import java.util.List;

public interface CompanyService {

    Company createOrUpdateCompany(Company company);

    Company findCompanyById(Long id) throws NotFoundException;

    List<Company> findAllCompanies();

    void deleteCompanyById(Long id) throws NotFoundException;
}
