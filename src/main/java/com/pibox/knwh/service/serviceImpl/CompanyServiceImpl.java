package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.Company;
import com.pibox.knwh.exception.domain.UniqueFieldExistException;
import com.pibox.knwh.repository.CompanyRepository;
import com.pibox.knwh.service.CompanyService;
import javassist.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createOrUpdateCompany(Company company) {
        company.setVatNumber(company.getVatNumber().toUpperCase());
        try {
            return companyRepository.save(company);
        } catch (DataIntegrityViolationException exception) {
            if (companyRepository.findCompanyByTitle(company.getTitle()) != null) {
                throw new UniqueFieldExistException("Company title '" + company.getTitle() + "' already exists");
            }
            if (companyRepository.findCompanyByEmail(company.getEmail()) != null) {
                throw new UniqueFieldExistException("Company email '" + company.getEmail() + "' already exists");
            }
            if (companyRepository.findCompanyByVatNumber(company.getVatNumber()) != null) {
                throw new UniqueFieldExistException("Company VAT Number '" + company.getVatNumber() + "' already exists");
            }
            throw exception;
        }
    }

    @Override
    public Company findCompanyById(Long id) throws NotFoundException {
        Company company = companyRepository.findCompanyById(id);
        if (company == null) {
            throw new NotFoundException("Company with ID '" + id + "' is not found");
        }
        return company;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void deleteCompanyById(Long id) throws NotFoundException {
        Company company = companyRepository.findCompanyById(id);
        if (company == null) {
            throw new NotFoundException("Company with ID '" + id + "' is not found");
        }
        companyRepository.delete(company);
    }
}
