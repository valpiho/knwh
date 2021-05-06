package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.Company;
import com.pibox.knwh.entity.DTO.CompanyDTO;
import com.pibox.knwh.exception.domain.BadRequestException;
import com.pibox.knwh.exception.domain.NotFoundException;
import com.pibox.knwh.repository.CompanyRepository;
import com.pibox.knwh.service.CompanyService;
import org.modelmapper.ModelMapper;
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
        checkIfTitleExists(companyDTO.getTitle());
        checkIfEmailExists(companyDTO.getEmail());
        checkIfVatNumberExists(companyDTO.getVatNumber());

        Company company = modelMapper.map(companyDTO, Company.class);
        company.setVatNumber(company.getVatNumber().toUpperCase());
        companyRepository.save(company);
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
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        checkIfCompanyExists(id);
        if (!companyDTO.getTitle().equals(companyRepository.findCompanyById(id).getTitle())) {
            checkIfTitleExists(companyDTO.getTitle());
        }
        if (!companyDTO.getEmail().equals(companyRepository.findCompanyById(id).getEmail())) {
            checkIfEmailExists(companyDTO.getEmail());
        }
        if (!companyDTO.getVatNumber().equals(companyRepository.findCompanyById(id).getVatNumber())) {
            checkIfVatNumberExists(companyDTO.getVatNumber());
        }

        Company company = companyRepository.findCompanyById(id);
        modelMapper.typeMap(CompanyDTO.class, Company.class).addMappings(mapper -> mapper.skip(Company::setId));
        modelMapper.map(companyDTO, company);
        companyRepository.save(company);
        return modelMapper.map(company, CompanyDTO.class);
    }

    @Override
    public void deleteCompanyById(Long id) {
        checkIfCompanyExists(id);
        companyRepository.delete(companyRepository.findCompanyById(id));
    }

    private void checkIfCompanyExists(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException(
                    "Company with ID: '" + id + "' is not found"
            );
        }
    }

    private void checkIfTitleExists(String title) {
        if (companyRepository.existsByTitle(title)) {
            throw new BadRequestException(
                    "Title: '" + title + "' already taken for other company"
            );
        }
    }

    private void checkIfEmailExists(String email) {
        if (companyRepository.existsByEmail(email)) {
            throw new BadRequestException(
                    "Email: '" + email + "' already taken for other company"
            );
        }
    }

    private void checkIfVatNumberExists(String vatNumber) {
        if (companyRepository.existsByVatNumber(vatNumber.toUpperCase())) {
            throw new BadRequestException(
                    "Vat Number: '" + vatNumber + "' already taken for other company"
            );
        }
    }
}
