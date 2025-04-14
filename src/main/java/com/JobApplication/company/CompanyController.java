package com.JobApplication.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    CompanyService service;
    CompanyController(CompanyService service){
        this.service=service;
    }
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        service.addCompany(company);
        return new ResponseEntity<>("Operation success",HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Company>> getALLCompanies(){
        return new ResponseEntity<>(service.findAllCompanies(), HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){
        Company company=service.findCompanyBYId(id);
        if(company!=null) return ResponseEntity.ok(company);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        if(service.updateCompany(id,company)){
            return new ResponseEntity<>("update success",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("update fails",HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        if(service.deleteCompany(id)){
            return new ResponseEntity<>("deletion success",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("deletion fails",HttpStatus.NOT_FOUND);
        }
    }
}
