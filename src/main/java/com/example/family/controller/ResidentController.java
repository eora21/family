package com.example.family.controller;

import com.example.family.domain.ResidentDto;
import com.example.family.domain.ResidentForm;
import com.example.family.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService service;

    @PostMapping
    public HttpEntity<Void> insertResident(@RequestBody ResidentForm residentForm) {
        service.saveResident(residentForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{serialNumber}")
    public HttpEntity<Void> editResident(@PathVariable int serialNumber, @RequestBody ResidentForm residentForm) {
        service.modifyResident(serialNumber, residentForm);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public HttpEntity<List<ResidentDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("{serialNumber}")
    public HttpEntity<Void> deleteResident(@PathVariable int serialNumber) {
        service.deleteResident(serialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
