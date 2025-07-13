package com.userservice.User_Service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Long userId;
    private String name;
    private String emailId;
    private String keyClockId;
}
