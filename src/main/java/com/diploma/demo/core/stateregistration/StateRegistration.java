package com.diploma.demo.core.stateregistration;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.rightofuse.RightOfUse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "state_registration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class StateRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "date")
    private LocalDate date;

    @Column(name ="issue_organization")
    private String issueOrganization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_plot_id")
    private LandPlot landPlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="right_of_use_id")
    private RightOfUse rightOfUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Owner owner;


}
