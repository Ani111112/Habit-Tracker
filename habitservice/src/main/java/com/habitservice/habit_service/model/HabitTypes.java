//package com.habitservice.habit_service.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import io.micrometer.common.util.StringUtils;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Entity
//public class HabitTypes {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String habitName;
//    private String habitDescription;
//    private Date createdOn;
//    private Date modifiedOn;
//
//    @OneToMany(mappedBy = "habitTypes", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JsonManagedReference
//    private List<HabitCategory> habitCategory;
//
//    @Override
//    public boolean equals(Object object) {
//        if (this == object) return true;
//        if (object == null || getClass() != object.getClass()) return false;
//        HabitTypes that = (HabitTypes) object;
//        return Objects.equals(habitName, that.habitName) && Objects.equals(habitDescription, that.habitDescription);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(habitName, habitDescription);
//    }
//
//    public boolean haveMandatoryFilled() {
//        return StringUtils.isNotBlank(this.habitName) && StringUtils.isNotBlank(this.habitDescription);
//    }
//}
