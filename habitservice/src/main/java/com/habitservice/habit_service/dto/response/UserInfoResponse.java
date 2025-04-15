package com.habitservice.habit_service.dto.response;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private String userId;
    private String name;
    private String emailId;

    public boolean isHaveNullFilled() {
        return StringUtils.isEmpty(this.userId) && StringUtils.isEmpty(this.name) && StringUtils.isEmpty(this.emailId);
    }
}
