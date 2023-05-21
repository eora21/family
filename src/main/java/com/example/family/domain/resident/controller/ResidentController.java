package com.example.family.domain.resident.controller;

import com.example.family.domain.resident.model.dto.ResidentDto;
import com.example.family.domain.resident.model.dto.ResidentViewDto;
import com.example.family.domain.resident.model.form.ResidentForm;
import com.example.family.domain.resident.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService service;

    @ResponseBody
    @PostMapping
    public HttpEntity<Void> insertResident(@RequestBody ResidentForm residentForm) {
        service.saveResident(residentForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping("{serialNumber}")
    public HttpEntity<Void> editResident(@PathVariable int serialNumber, @RequestBody ResidentForm residentForm) {
        service.modifyResident(serialNumber, residentForm);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping
    public String findAll(Model model, @PageableDefault(size = 5) Pageable pageable) {
        model.addAttribute("residents", service.findAll(pageable));
        return "residents";
    }

    @ResponseBody
    @DeleteMapping("{serialNumber}")
    public HttpEntity<Void> deleteResident(@PathVariable int serialNumber) {
        service.deleteResident(serialNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("view/{serialNumber}")
    public String deleteResidentInView(@PathVariable int serialNumber) {
        service.deleteResident(serialNumber);
        return "redirect:/residents";
    }
}
