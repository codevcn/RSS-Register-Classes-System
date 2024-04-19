package com.example.demo.DTOs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRegistrationInfor {

    private Integer studentID;
    private Integer subjectID;
    private String subjectCode;
    private String subjectName;
    private Integer creditsCount;
    private Integer receiptID;
    private Integer ReceiptSubjectID;

    public CourseRegistrationInfor(Integer studentID, Integer subjectID, String subjectCode, String subjectName, Integer creditsCount, Integer receiptID, Integer ReceiptSubjectID) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.creditsCount = creditsCount;
        this.receiptID = receiptID;
        this.ReceiptSubjectID = ReceiptSubjectID;
    }
    
}
