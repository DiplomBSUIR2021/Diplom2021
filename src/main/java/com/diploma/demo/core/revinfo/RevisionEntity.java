package com.diploma.demo.core.revinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;

@Table(name = "revinfo")
@Entity
@NoArgsConstructor
@org.hibernate.envers.RevisionEntity(RevisionListener.class)
@Getter
@Setter
public class RevisionEntity {
    @Id
    @GeneratedValue
    @RevisionNumber
    @Column(name = "rev")
    private int id;

    @RevisionTimestamp
    @Column(name = "revtstmp")
    private long timestamp = System.currentTimeMillis();

    public RevisionEntity(int id, long timestamp) {
        setId(id);
        setTimestamp(timestamp);
    }

    @Transient
    public Date getRevisionDate() {
        return new Date(this.timestamp);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof RevisionEntity)) {
            return false;
        } else {
            RevisionEntity that = (RevisionEntity)o;
            return this.id == that.id && this.timestamp == that.timestamp;
        }
    }

    public int hashCode() {
        int result = this.id;
        result = 31 * result + (int)(this.timestamp ^ this.timestamp >>> 32);
        return result;
    }

    public String toString() {
        return "RevisionEntity(id = " + this.id + ", revisionDate = " + DateFormat.getDateTimeInstance().format(this.getRevisionDate()) + ")";
    }
}