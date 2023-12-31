package com.testsigma.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_onboarding_details")
@Getter
@Setter
@ToString
public class UserOnboardingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "is_community_access")
    private Boolean isCommunityAccess;
    @Column(name = "is_send_update")
    private Boolean isSendUpdates;

    @Column(name = "resgitration_type")
    private String registrationType;

    @Column(name = "resgitration_type_medium")
    private String registrationMedium;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "jwt_secret")
    private String jwtSecret;
    @Column(name = "is_api_enabled")
    private Boolean isApiEnabled;

    @Column(name = "authentication_type")
    private String authenticationType;

    @Column(name = "google_client_id")
    private String googleClientId;

    @Column(name = "google_client_secret")
    private String googleClientSecret;

    @Column(name = "company_id", nullable = false, columnDefinition = "int default 1")
    private Integer companyId;

    @Column(name = "role")
    private String role;

    @Column(name= "role_id")
    private Integer roleId;
}
