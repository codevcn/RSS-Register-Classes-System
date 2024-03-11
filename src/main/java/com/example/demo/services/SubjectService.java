package com.example.demo.services;

import com.example.demo.models.Subject;
import com.example.demo.repositories.SubjectRepository;
import com.example.demo.utils.exceptions.CustomBaseException;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject getSubjectInfo(@NonNull HttpServletRequest httpServletRequest, String id)
            throws CustomBaseException {
        Subject subject = subjectRepository.findSubject(id);
        if (subject != null) {
            return subject;
        } else {
            throw new CustomBaseException("Không tìm thấy môn học");
        }

    }
}