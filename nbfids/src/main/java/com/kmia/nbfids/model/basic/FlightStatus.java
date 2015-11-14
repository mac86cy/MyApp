package com.kmia.nbfids.model.basic;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by mac86cy on 15/11/14.
 */
@Table(name = "FLIGHT_STATUS")
public class FlightStatus {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "fid")
    private String fid;

    @Column(name = "fstatus")
    private String fstatus;// 状态

    @Column(name = "faod")
    private String faod;// 进出港标志

    @Column(name = "fpriority")
    private int fpriority;// 优先顺序

    @Column(name = "fdescription")
    private String fdescription;// 状态描述

    public FlightStatus() {
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

    public String getFstatus() {
        return fstatus;
    }

    public void setFstatus(String fstatus) {
        this.fstatus = fstatus;
    }

    public String getFaod() {
        return faod;
    }

    public void setFaod(String faod) {
        this.faod = faod;
    }

    public int getFpriority() {
        return fpriority;
    }

    public void setFpriority(int fpriority) {
        this.fpriority = fpriority;
    }

    public String getFdescription() {
        return fdescription;
    }

    public void setFdescription(String fdescription) {
        this.fdescription = fdescription;
    }
}
