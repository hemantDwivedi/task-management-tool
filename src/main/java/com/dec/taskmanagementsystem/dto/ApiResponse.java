package com.dec.taskmanagementsystem.dto;

import lombok.*;


/**
 * The ApiResponse class provides custom response
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private String message;
    private Object object;
}
