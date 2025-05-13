package lk.ijse.sciencelab.Dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class EquipmentDto {
    private String equipmentId;
    private String equipmentName;
    private String quantity;
    private String type;
    private String supplierId;

  }