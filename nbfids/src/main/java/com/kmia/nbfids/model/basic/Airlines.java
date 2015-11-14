package com.kmia.nbfids.model.basic;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by mac86cy on 15/11/14.
 */
@Table(name = "AIRLINES")
public class Airlines {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "fid")
    private String fid;

    @Column(name = "ficaoCode")
    private String ficaoCode;// ICAO码

    @Column(name = "fiataCode")
    private String fiataCode;// IATA码

    @Column(name = "fname")
    private String fname;// 航空公司名称

    @Column(name = "fhandlineAgent")
    private String fhandlineAgent;// 地服代理

    @Column(name = "fdescription")
    private String fdescription;// 描述

    @Column(name = "fpubDspCode")
    private String fpubDspCode;// 承运人代码(IATA)

    public Airlines() {
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

    public String getFicaoCode() {
        return ficaoCode;
    }

    public void setFicaoCode(String ficaoCode) {
        this.ficaoCode = ficaoCode;
    }

    public String getFiataCode() {
        return fiataCode;
    }

    public void setFiataCode(String fiataCode) {
        this.fiataCode = fiataCode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFhandlineAgent() {
        return fhandlineAgent;
    }

    public void setFhandlineAgent(String fhandlineAgent) {
        this.fhandlineAgent = fhandlineAgent;
    }

    public String getFdescription() {
        return fdescription;
    }

    public void setFdescription(String fdescription) {
        this.fdescription = fdescription;
    }

    public String getFpubDspCode() {
        return fpubDspCode;
    }

    public void setFpubDspCode(String fpubDspCode) {
        this.fpubDspCode = fpubDspCode;
    }
}
