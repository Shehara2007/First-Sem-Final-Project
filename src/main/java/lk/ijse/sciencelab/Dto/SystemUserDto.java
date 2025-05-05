package lk.ijse.sciencelab.Dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class SystemUserDto {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String role;

}