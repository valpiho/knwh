package com.pibox.knwh.repository;

import com.pibox.knwh.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyById(Long id);

    Company findCompanyByTitle(String title);

    Company findCompanyByEmail(String email);

    Company findCompanyByVatNumber(String vatNumber);
}
