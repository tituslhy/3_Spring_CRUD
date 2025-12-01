package com.luv2code.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Create a base POJO class
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;
}
