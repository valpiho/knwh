package com.pibox.knwh.rest;

import com.pibox.knwh.entity.Company;
import com.pibox.knwh.service.CompanyService;
import com.pibox.knwh.utils.MapValidationErrorService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;
    private final MapValidationErrorService mapValidationErrorService;

    public CompanyController(CompanyService companyService, MapValidationErrorService mapValidationErrorService) {
        this.companyService = companyService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdateCompany(@Valid @RequestBody Company company, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        Company newCompany = companyService.createOrUpdateCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long companyId) throws NotFoundException {
        Company company = companyService.findCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Company> getAllCompanies() {
        return companyService.findAllCompanies();
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long companyId) throws NotFoundException {
        companyService.deleteCompanyById(companyId);
        return new ResponseEntity<>("Company with ID '" + companyId + "' was deleted successfully.", HttpStatus.OK);
    }
}
