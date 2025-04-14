package com.JobApplication.company;

import java.util.List;

public interface CompanyService {
    public List<Company> findAllCompanies();
    public Company findCompanyBYId(Long id);
    public void addCompany(Company company);
    public Boolean updateCompany(Long id,Company company);
    public Boolean deleteCompany(Long id);
}
