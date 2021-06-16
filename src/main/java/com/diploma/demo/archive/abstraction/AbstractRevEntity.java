package com.diploma.demo.archive.abstraction;

import com.diploma.demo.core.revinfo.RevisionEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class AbstractRevEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "rev")
    private int rev;

    @NotEmpty
    @Column(name = "revtype")
    private short revtype;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rev")
    private RevisionEntity revisionEntity;
}