package com.diploma.demo.core.landplot;

import com.diploma.demo.core.act.Contract;
import com.diploma.demo.core.owner.Owner;
import com.diploma.demo.core.restriction.Restriction;
import com.diploma.demo.core.stateregistration.StateRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "land_plot")
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class LandPlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "cadastral_number")
    private String cadastralNumber;

    @Column(name="category")
    private String category;

    //актуальные отметки
    @Column(name="current_marks")
    private String currentMarks;

    //Целевое назначение земельного участка
    @Column(name="intended_use")
    private String intendedUse;

    //Назначение земельного участка в соответствии с единой классификацией назначения объектов недвижимого имущества
    @Column(name="land_plot_purpose")
    private String landPlotPurpose;

    // примечания
    @Column(name="notes")
    private String notes;

    @Column(name = "surface")
    private Double surface;

    @OneToMany(mappedBy = "landPlot",
            fetch = FetchType.LAZY)
    private List<StateRegistration> registrations;

    @OneToMany(mappedBy = "landPlot",
            fetch = FetchType.LAZY)
    private List<Restriction> restrictions;

    //основание государственной регистрации
    @OneToMany(mappedBy = "landPlot",
            fetch = FetchType.LAZY)
    private List<Contract> contracts;

    @OneToMany(mappedBy = "landPlot")
    private List<Owner> owner;
}
