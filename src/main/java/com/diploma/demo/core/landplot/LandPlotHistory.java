package com.diploma.demo.core.landplot;


import com.diploma.demo.core.revinfo.RevisionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.envers.DefaultRevisionEntity;
import	javax.persistence.Transient;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "land_plot_aud")
@NoArgsConstructor
public class LandPlotHistory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "rev")
    private int rev;
    @NonNull
    @NotEmpty
    @Column(name = "revtype")
    private short revtype;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rev")
    private RevisionEntity revisionEntity;

    @Column(name = "id")
    private Long id;

    @Embedded
    private Address address;

    @Column(name = "cadastral_number")
    private String cadastralNumber;

    @Column(name="category")
    private String category;

    @Column(name="current_marks")
    private String currentMarks;

    @Column(name="intended_use")
    private String intendedUse;

    @Column(name="land_plot_purpose")
    private String landPlotPurpose;

    @Column(name="notes")
    private String notes;

    @Column(name = "surface")
    private Double surface;

    public LandPlotHistory(LandPlot landPlot, RevisionEntity entity, short revtype) {
        this.rev = entity.getId();
        this.revtype = revtype;
        this.revisionEntity =  entity;

        setId(landPlot.getId());
        setAddress(landPlot.getAddress());
        setCadastralNumber(landPlot.getCadastralNumber());
        setCategory(landPlot.getCategory());
        setCurrentMarks(landPlot.getCurrentMarks());
        setIntendedUse(landPlot.getIntendedUse());
        setLandPlotPurpose(landPlot.getLandPlotPurpose());
        setNotes(landPlot.getNotes());
        setSurface(landPlot.getSurface());
    }

    public LandPlotHistory(LandPlot landPlot, RevisionEntity entity, String revtype) {
        this(landPlot, entity, getRevtypeNumb(revtype));
    }

    public static String getRevtypeString(short revtypeShort) {
        if (revtypeShort == 0) {
            return "Создание";//"ADD";
        }
        if (revtypeShort == 1) {
            return "Изменение";//"MOD";
        }
        return "Удаление";//"DEL"; 2
    }

    public static short getRevtypeNumb(String revtypeString) {
        if (revtypeString.equals("ADD")) {
            return 0;
        }
        if (revtypeString.equals("MOD")) {
            return 1;
        }
        // (obj[2].equals("DEL"))
        return 2;
    }

}
