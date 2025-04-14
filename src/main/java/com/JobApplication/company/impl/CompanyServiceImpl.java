package com.JobApplication.company.impl;

import com.JobApplication.company.Company;
import com.JobApplication.company.CompanyRepository;
import com.JobApplication.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository repository;
    CompanyServiceImpl(CompanyRepository repository){
        this.repository=repository;
    }
    @Override
    public List<Company> findAllCompanies() {
        return repository.findAll();
    }

    @Override
    public Company findCompanyBYId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void addCompany(Company company) {
        repository.save(company);
    }

    @Override
    public Boolean updateCompany(Long id, Company company) {
        Company company1=repository.findById(id).orElse(null);
        if(company1!=null){
            company1.setName(company.getName());
            company1.setDescription(company.getDescription());
            repository.save(company1);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean deleteCompany(Long id) {
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
