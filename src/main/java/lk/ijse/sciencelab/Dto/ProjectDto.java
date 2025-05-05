package lk.ijse.sciencelab.Dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Project {
    private String projectId;
    private String startDate;
    private String endDate;
    private double fundingAmount;
    private String title;
    private String description;

}