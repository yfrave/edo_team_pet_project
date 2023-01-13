package com.education.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@AllArgsConstructor@Getter
@Setter
@Table(name = "employee")
public class Employee extends BaseEntity {

}
