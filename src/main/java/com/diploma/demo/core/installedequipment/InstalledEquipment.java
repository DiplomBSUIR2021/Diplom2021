package com.diploma.demo.core.installedequipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited

public class InstalledEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="placement")
    private Integer placement;

    @Column(name="model")
    private String model;

    @Column(name="number")
    private Integer number;

    @Column(name="validity")
    private Integer validity;

    @Column(name="installation_date")
    private LocalDate installationDate;

    @Column(name="replacement_date")
    private LocalDate replacementDate;

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setInstallationDate(LocalDate installationDate) {
        this.installationDate = installationDate;
    }

    public void setPlacement(Integer placement) {
        this.placement = placement;
    }

    public void setReplacementDate(LocalDate replacementDate) {
        this.replacementDate = replacementDate;
    }

    public String getModel() {
        return model;
    }

    public Integer getValidity() {
        return validity;
    }

    public Integer getNumber() {
        return number;
    }

    public Long getId() {
        return id;
    }

    public Integer getPlacement() {
        return placement;
    }

    public LocalDate getInstallationDate() {
        return installationDate;
    }

    public LocalDate getReplacementDate() {
        return replacementDate;
    }
}

