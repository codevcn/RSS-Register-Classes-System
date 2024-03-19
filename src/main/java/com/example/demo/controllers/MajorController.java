package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.demo.DTOs.response.MajorResDTOs.GetMajorInfoResDTO;
import com.example.demo.models.Major;
import com.example.demo.services.MajorService;
import com.example.demo.DTOs.response.MajorResDTOs;
import com.example.demo.DTOs.response.MajorResDTOs.GetMajorInfoResDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("major")
public class MajorController {

    @Autowired
    private MajorService majorService;
    
    @GetMapping("get-all-major")
    public ResponseEntity<List<GetMajorInfoResDTO>> getAllMajors() {
        List<Major> majors = majorService.getAllMajors();
        List<GetMajorInfoResDTO> majorInfoList = majors.stream()
            .map(major -> new GetMajorInfoResDTO(
                major.getId(),
                major.getName()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(majorInfoList);
    }

}
