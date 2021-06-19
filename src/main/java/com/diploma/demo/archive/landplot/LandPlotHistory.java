package com.diploma.demo.archive.landplot;

import com.diploma.demo.archive.abstraction.AbstractRevEntity;
import com.diploma.demo.core.landplot.Address;
import com.diploma.demo.core.landplot.LandPlot;
import com.diploma.demo.core.revinfo.RevisionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.*;

@Data
@Entity
@Table(name = "land_plot_aud")
@NoArgsConstructor
public class LandPlotHistory extends AbstractRevEntity {
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

    @Column(name = "the_year")
    @Nullable
    private Integer year;

    public LandPlotHistory(LandPlot landPlot, RevisionEntity revisionEntity) {

        // this();
        this.setId(landPlot.getId());
        this.setRevisionEntity(revisionEntity);
        this.setRev(revisionEntity.getId());
        this.setRevtype((short) 3);
        /*this.address = landPlot.getAddress();
        this.cadastralNumber = landPlot.getCadastralNumber();
        this.category = landPlot.getCategory();
        this.intendedUse = landPlot.getIntendedUse();
        this.notes = landPlot.getNotes();
        this.surface = landPlot.getSurface();
        this.year = landPlot.getYear();

        System.out.println("Get rev info with id" + revisionEntity.getId());
        this.setRevisionEntity(revisionEntity);
        this.setRevtype((short) 3);
        // this.setRev(revisionEntity.getId());*/
    }

    public static String getRevtypeString(short revtypeShort) {
        if (revtypeShort == 0) {
            return "Создание";//"ADD";
        }
        if (revtypeShort == 1) {
            return "Изменение";//"MOD";
        }
        if (revtypeShort == 1) {
            return "Удаление";//"DEL"; 2
        }
        return "" + revtypeShort;
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
