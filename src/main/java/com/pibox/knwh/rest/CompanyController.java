package com.pibox.knwh.rest;

import com.pibox.knwh.entity.DTO.CompanyDTO;
import com.pibox.knwh.entity.HttpResponse;
import com.pibox.knwh.service.CompanyService;
import com.pibox.knwh.utils.MapValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {

    private final CompanyService companyService;
    private final MapValidationErrorService mapValidationErrorService;

    public CompanyController(CompanyService companyService,
                             MapValidationErrorService mapValidationErrorService) {
        this.companyService = companyService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("/company/create")
    public ResponseEntity<?> createOrUpdateCompany(@Valid @RequestBody CompanyDTO companyDTO, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        companyService.createCompany(companyDTO);
        return response(HttpStatus.OK, "Company has been created");
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable("id") Long id) {
        CompanyDTO companyDTO = companyService.findCompanyById(id);
        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companyDTOList = companyService.findAllCompanies();
        return new ResponseEntity<>(companyDTOList, HttpStatus.OK);
    }

    @PatchMapping("/company/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable("id") Long id,
                                        @Valid @RequestBody CompanyDTO companyDTO, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(companyService.updateCompany(id, companyDTO), HttpStatus.OK);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
        return response(HttpStatus.OK, "Company with ID '" + id + "' was deleted successfully.");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, message), httpStatus);
    }
}
