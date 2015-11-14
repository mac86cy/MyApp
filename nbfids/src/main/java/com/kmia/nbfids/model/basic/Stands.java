package com.kmia.nbfids.model.basic;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by mac86cy on 15/11/14.
 */
@Table(name = "STANDS")
public class Stands {
    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "fid")
    private String fid;

    @Column(name = "fstand")
    private String fstand;// 机位编号

    @Column(name = "fterminal")
    private String fterminal;// 航站楼

    @Column(name = "fgate")
    private String fgate;// 对应登机门

    @Column(name = "fdescription")
    private String fdescription;// 描述

    @Column(name = "ftype")
    private String ftype;// 机位类型C D E F

    public Stands() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFstand() {
        return fstand;
    }

    public void setFstand(String fstand) {
        this.fstand = fstand;
    }

    public String getFterminal() {
        return fterminal;
    }

    public void setFterminal(String fterminal) {
        this.fterminal = fterminal;
    }

    public String getFgate() {
        return fgate;
    }

    public void setFgate(String fgate) {
        this.fgate = fgate;
    }

    public String getFdescription() {
        return fdescription;
    }

    public void setFdescription(String fdescription) {
        this.fdescription = fdescription;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }
}

