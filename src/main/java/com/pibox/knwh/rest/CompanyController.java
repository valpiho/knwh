package com.pibox.knwh.rest;

import com.pibox.knwh.entity.Company;
import com.pibox.knwh.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> createNewCompany(@RequestBody Company company) {
        Company newCompany = companyService.addOrUpdateCompany(company);
        return new ResponseEntity<Company>(newCompany, HttpStatus.CREATED);
    }
}
