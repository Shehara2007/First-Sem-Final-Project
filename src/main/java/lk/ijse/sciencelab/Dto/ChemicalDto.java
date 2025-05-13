package lk.ijse.sciencelab.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChemicalDto {
    private String chemicalId;
    private String chemicalName;
    private String quantity;
    private String concentration;
    private String supplierId;


}