package com.HospitalManagementSystem.HospitalManagementSystem.ReportScheduleTest.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "analysis")
@Data
public class Analysis {
    @Id
    private String analysisNo;
    private List<String> data;
    private List<String> labels;
}
