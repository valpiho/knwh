package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.Company;
import com.pibox.knwh.entity.DTO.CompanyDTO;
import com.pibox.knwh.exception.domain.NotFoundException;
import com.pibox.knwh.exception.domain.UniqueFieldExistException;
import com.pibox.knwh.repository.CompanyRepository;
import com.pibox.knwh.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createCompany(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        company.setVatNumber(company.getVatNumber().toUpperCase());
        try {
            companyRepository.save(company);
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
    public CompanyDTO findCompanyById(Long id) {
        checkIfCompanyExists(id);
        return modelMapper.map(companyRepository.findCompanyById(id), CompanyDTO.class);
    }

    @Override
    public List<CompanyDTO> findAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(company -> modelMapper.map(company, CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompanyById(Long id) throws NotFoundException {
        checkIfCompanyExists(id);
        companyRepository.delete(companyRepository.findCompanyById(id));
    }

    private void checkIfCompanyExists(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException(
                    "Company with ID '" + id + "' is not found"
            );
        }
    }
}
