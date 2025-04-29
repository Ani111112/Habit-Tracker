package org.habittracker.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String keyClockId;
    private String name;
    private String emailId;
}
