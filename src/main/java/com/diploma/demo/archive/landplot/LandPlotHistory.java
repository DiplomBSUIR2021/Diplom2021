package com.diploma.demo.archive.landplot;

import com.diploma.demo.archive.abstraction.AbstractRevEntity;
import com.diploma.demo.core.landplot.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "land_plot_aud")
@NoArgsConstructor
public class LandPlotHistory extends AbstractRevEntity implements Cloneable{
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

    public static String getRevtypeString(short revtypeShort) {
        if (revtypeShort == 0) {
            return "Создание";//"ADD";
        }
        if (revtypeShort == 1) {
            return "Изменение";//"MOD";
        }
        if (revtypeShort == 2) {
            return "Удаление";//"DEL"; 2
        }

        if (revtypeShort == 3) {
            return "Изменено супервизором";
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

    public LandPlotHistory clone() throws CloneNotSupportedException{
        LandPlotHistory landPlotHistory = (LandPlotHistory) super.clone();
        landPlotHistory.address = address.clone();
        return landPlotHistory;
    }

}
