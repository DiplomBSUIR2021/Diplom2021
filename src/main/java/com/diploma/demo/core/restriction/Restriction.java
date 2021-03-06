package com.diploma.demo.core.restriction;

import com.diploma.demo.core.act.Act;
import com.diploma.demo.core.landplot.LandPlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

//Ограничения (обременения) прав в использовании земель (иные)
@Entity
@Table(name="restriction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class Restriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "restriction",
            fetch = FetchType.LAZY)
    private List<Act> acts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "land_plot_id")
    private LandPlot landPlot;


}
