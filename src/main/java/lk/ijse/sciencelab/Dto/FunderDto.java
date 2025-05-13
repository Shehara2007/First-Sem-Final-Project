package lk.ijse.sciencelab.Dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class FunderDto {
    private String funderId;
    private String funderName;
    private double amount;
    private String project;
    private String organization;

   }