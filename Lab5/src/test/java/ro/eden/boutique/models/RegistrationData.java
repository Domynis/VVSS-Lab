package ro.eden.boutique.models;

import lombok.Data;

@Data
public class RegistrationData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String expectedResult;
    private String errorType;
}