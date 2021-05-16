package com.diploma.demo.core.owner;

import com.diploma.demo.core.stateregistration.StateRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "individual_owner")
@DiscriminatorValue("INDIVIDUAL")
public class IndividualOwner extends Owner{

    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name ="doc_type")
    private String docType;

    @Column(name="doc_n")
    private String docN;

    @OneToOne
    @JoinColumn(name="owner_id")
    private StateRegistration docRegistration;

    @Column(name = "personal_number")
    private String personalNumber;


}
