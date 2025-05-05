package lk.ijse.sciencelab.Dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ProgressDto {
    private String progressId;
    private String projectId;
    private String status;
    private String lastUpdatedDate;


}