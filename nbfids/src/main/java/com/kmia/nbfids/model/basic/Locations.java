package com.kmia.nbfids.model.basic;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：地名表
 *  
 */
@Table(name = "LOCATIONS")
public class Locations {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "fid")
    private String fid;

    @Column(name = "fiataCode")
    private String fiataCode;// IATA码

    @Column(name = "ficaoCode")
    private String ficaoCode;// ICAO码

    @Column(name = "fname")
    private String fname;// 机场名称EN

    @Column(name = "fdescription")
    private String fdescription;// 机场名称CN

    @Column(name = "fabbc")
    private String fabbc;// 机场名称的中文缩写

    @Column(name = "fcountrySymbol")
    private String fcountrySymbol;// 国家代码

    public Locations() {
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

    public String getFiataCode() {
        return fiataCode;
    }

    public void setFiataCode(String fiataCode) {
        this.fiataCode = fiataCode;
    }

    public String getFicaoCode() {
        return ficaoCode;
    }

    public void setFicaoCode(String ficaoCode) {
        this.ficaoCode = ficaoCode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFdescription() {
        return fdescription;
    }

    public void setFdescription(String fdescription) {
        this.fdescription = fdescription;
    }

    public String getFabbc() {
        return fabbc;
    }

    public void setFabbc(String fabbc) {
        this.fabbc = fabbc;
    }

    public String getFcountrySymbol() {
        return fcountrySymbol;
    }

    public void setFcountrySymbol(String fcountrySymbol) {
        this.fcountrySymbol = fcountrySymbol;
    }
}
