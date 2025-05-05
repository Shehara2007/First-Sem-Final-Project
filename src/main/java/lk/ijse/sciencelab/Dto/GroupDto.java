package lk.ijse.sciencelab.Dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class GroupDto {
    private String groupId;
    private String groupName;
    private String progress;
    private String member;
    private String researchProgress;
    private String scientistId;

   }