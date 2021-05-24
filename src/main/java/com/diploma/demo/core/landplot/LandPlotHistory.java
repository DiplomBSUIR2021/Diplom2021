package com.diploma.demo.core.landplot;

import lombok.Data;
import org.hibernate.envers.DefaultRevisionEntity;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
/*@Table(name = "land_plot_aud")
@RevisionEntity()*/
public class LandPlotHistory extends LandPlot {

    private final int rev;

    private final short revtype;

    @Transient
    @NotEmpty
    private Date revisionDate;
    // private final LandPlot landPlot;

    public LandPlotHistory(DefaultRevisionEntity entity, short revtype) {
        this.rev = entity.getId();
        this.revtype = revtype;
        this.revisionDate = entity.getRevisionDate();
    }

    public LandPlotHistory(LandPlot landPlot, DefaultRevisionEntity entity, short revtype) {
        this.rev = entity.getId();
        this.revtype = revtype;
        this.revisionDate = entity.getRevisionDate();

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

    public LandPlotHistory(LandPlot landPlot, DefaultRevisionEntity entity, String revtype) {
        this(landPlot, entity, getRevtypeNumb(revtype));
    }

    public static String getRevtypeString(short revtypeShort) {
        if (revtypeShort == 1) {
            return "Создание";//"ADD";
        }
        if (revtypeShort == 2) {
            return "Изменение";//"MOD";
        }
        return "Удаление";//"DEL";
    }

    public static short getRevtypeNumb(String revtypeString) {
        if (revtypeString.equals("ADD")) {
            return 1;
        }
        if (revtypeString.equals("MOD")) {
            return 2;
        }
        // (obj[2].equals("DEL"))
        return 3;
    }

}
