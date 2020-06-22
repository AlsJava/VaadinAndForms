package edu.alsjava.example.vaadinform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by aluis on 5/17/20.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String username;
    private String password;

    private String description;

    private LocalDate expiration;
}
