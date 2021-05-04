package com.diploma.demo.core.rightofuse;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.organization.Organization;
import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.stateregistration.StateRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="right_of_use")
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class RightOfUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Описание права, ограничения (обременения) прав:
    @Column(name="description")
    private String description;

    //Доля
    @Column(name = "proportion")
    private Double proportion;

    //Срок действия с
    @Column(name = "validity_period")
    private LocalDate validityPeriod;

    //Стоимость обязательств или данные о порядке и условиях ее определения
    @Column(name = "cost_desceription")
    private String costDescription;

    @OneToMany(mappedBy = "rightOfUse",
            fetch = FetchType.LAZY)
    private List<StateRegistration> stateRegistrations;

    @OneToOne
    @JoinColumn(name="land_plot_id")
    private LandPlot landPlot;

    //организации правообладатели
    @OneToMany(mappedBy = "rightOfUse",
            fetch = FetchType.LAZY)
    private List<Organization> organization;

    @OneToOne
    @JoinColumn(name="owner_id")
    private Owner owner;
}
