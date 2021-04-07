package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.Company;
import com.pibox.knwh.repository.CompanyRepository;
import com.pibox.knwh.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company addOrUpdateCompany(Company company) {
        return companyRepository.save(company);
    }
}
