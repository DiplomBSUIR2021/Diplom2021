package com.diploma.demo.core.owner;

import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.ownership.Ownership;
import com.diploma.demo.core.rightofuse.RightOfUse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Audited
public abstract class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name="ownership_id")
    private Ownership ownership;

    @OneToOne
    @JoinColumn(name = "right_of_use_id")
    private RightOfUse rightOfUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_plot_id")
    private LandPlot landPlot;

}
