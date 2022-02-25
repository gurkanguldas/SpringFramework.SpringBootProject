package com.gurkanguldas.personsevice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "person")
public class Person extends BaseEntity implements Serializable,IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "person_identification_number", nullable = false, unique = true, columnDefinition = "BIGINT(11)" )
    private Long personIdentificationNumber;

    @Column(name = "person_name", nullable = false)
    private String personName;

    @Column(name = "person_surname", nullable = false)
    private String personSurname;

    @Column(name = "person_monthly_income", nullable = false)
    private Long personMonthlyIncome;

    @Column(name = "person_phone_number", nullable = false)
    private Long personPhoneNumber;

    @Column(name = "person_credic_score", nullable = false)
    private Long personCredicScore;


}
