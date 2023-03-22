package com.example.ContactManagementSystemProject.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
        @Column(name = "id")
        private Long id;
        @Column(name = "firstName")
        private String firstName;
        @Column(name = "lastName")
        private String lastName;
        @Column(name = "email")
        private String email;
        @Column(name = "phoneNumber")
        private String phoneNumber;
    }