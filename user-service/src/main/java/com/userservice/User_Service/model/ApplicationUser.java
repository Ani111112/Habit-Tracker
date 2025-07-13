package com.userservice.User_Service.model;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String keyClockId;
    private String name;
    private String emailId;
    private Date createdOn;
    private Date modifiedOn;

    public boolean mandatoryFilledCheck() {
        return StringUtils.isNotBlank(name) && StringUtils.isNotBlank(emailId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ApplicationUser that = (ApplicationUser) object;
        return Objects.equals(name, that.name) && Objects.equals(emailId, that.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailId);
    }
}
