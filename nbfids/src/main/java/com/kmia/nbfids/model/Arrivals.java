package com.kmia.nbfids.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Date;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：到港航班表
 *  
 */
@Table(name = "ARRIVALS")
public class Arrivals {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "fid")
    private String fid;

    @Column(name = "fsdt")
    private Date fsdt;//运营日

    @Column(name = "ffln")
    private String ffln;// 航班号

    @Column(name = "forg")
    private String forg;// 始发站IATA码

    @Column(name = "fdes")
    private String fdes;// 本站IATA码

    @Column(name = "fstp")
    private Date fstp;// 前站计划起飞时间

    @Column(name = "fabp")
    private Date fabp;// 前站起飞时间

    @Column(name = "fsta")
    private Date fsta;// 计划到港时间

    @Column(name = "ftdt")
    private Date ftdt;// 实际到港时间

    @Column(name = "ffsa")
    private String ffsa;// 航班状态

    @Column(name = "fgvf")
    private String fgvf;// 通用飞行标识

    @Column(name = "fcan")
    private String fcan;// 取消航班标识

    @Column(name = "ftys")
    private String ftys;// 子机型IATA码

    @Column(name = "freg")
    private String freg;// 飞机注册号

    @Column(name = "ftar")
    private String ftar;// 机位

    @Column(name = "fgat")
    private String fgat;// 1号登机门

    @Column(name = "fgt2")
    private String fgt2;// 2号登机门

    @Column(name = "fcar")
    private String fcar;// 行李提取转盘1

    @Column(name = "fflc")
    private String fflc;// 承运人IATA码

    @Column(name = "fcsf")
    private String fcsf;// 共享航班代码

    @Column(name = "fmff")
    private String fmff;// 主航班标识

    @Column(name = "fflt")
    private String fflt;// 航班标识

    @Column(name = "fflx")
    private String fflx;// 航班后缀

    @Column(name = "fct1")
    private String fct1;// 第一经停机场全称

    @Column(name = "fct2")
    private String fct2;// 第二经停机场全称

    @Column(name = "fcla")
    private String fcla;// 飞行分类码 对应表classifications  W/Z 客班正班

    @Column(name = "ffst")
    private String ffst;// 航班服务类型 对应表service_types J 正常旅客服务 配合PAX

    @Column(name = "fnat")
    private String fnat;// 航班性质 对应表nature CGO	货机，GEN	通用，PAX	客机，SPE	特殊航班

    @Column(name = "ftof")
    private String ftof;// 航段性质 对应表sectors  D	 国内， I 国际，M 混合，R 地区

    public Arrivals() {
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

    public Date getFsdt() {
        return fsdt;
    }

    public void setFsdt(Date fsdt) {
        this.fsdt = fsdt;
    }

    public Date getFabp() {
        return fabp;
    }

    public void setFabp(Date fabp) {
        this.fabp = fabp;
    }

    public Date getFtdt() {
        return ftdt;
    }

    public void setFtdt(Date ftdt) {
        this.ftdt = ftdt;
    }

    public String getFfsa() {
        return ffsa;
    }

    public void setFfsa(String ffsa) {
        this.ffsa = ffsa;
    }

    public String getFfln() {
        return ffln;
    }

    public void setFfln(String ffln) {
        this.ffln = ffln;
    }

    public String getForg() {
        return forg;
    }

    public void setForg(String forg) {
        this.forg = forg;
    }

    public Date getFstp() {
        return fstp;
    }

    public void setFstp(Date fstp) {
        this.fstp = fstp;
    }

    public Date getFsta() {
        return fsta;
    }

    public void setFsta(Date fsta) {
        this.fsta = fsta;
    }

    public String getFdes() {
        return fdes;
    }

    public void setFdes(String fdes) {
        this.fdes = fdes;
    }

    public String getFgvf() {
        return fgvf;
    }

    public void setFgvf(String fgvf) {
        this.fgvf = fgvf;
    }

    public String getFcan() {
        return fcan;
    }

    public void setFcan(String fcan) {
        this.fcan = fcan;
    }

    public String getFtys() {
        return ftys;
    }

    public void setFtys(String ftys) {
        this.ftys = ftys;
    }

    public String getFreg() {
        return freg;
    }

    public void setFreg(String freg) {
        this.freg = freg;
    }

    public String getFtar() {
        return ftar;
    }

    public void setFtar(String ftar) {
        this.ftar = ftar;
    }

    public String getFgat() {
        return fgat;
    }

    public void setFgat(String fgat) {
        this.fgat = fgat;
    }

    public String getFgt2() {
        return fgt2;
    }

    public void setFgt2(String fgt2) {
        this.fgt2 = fgt2;
    }

    public String getFcar() {
        return fcar;
    }

    public void setFcar(String fcar) {
        this.fcar = fcar;
    }

    public String getFflc() {
        return fflc;
    }

    public void setFflc(String fflc) {
        this.fflc = fflc;
    }

    public String getFcsf() {
        return fcsf;
    }

    public void setFcsf(String fcsf) {
        this.fcsf = fcsf;
    }

    public String getFmff() {
        return fmff;
    }

    public void setFmff(String fmff) {
        this.fmff = fmff;
    }

    public String getFflt() {
        return fflt;
    }

    public void setFflt(String fflt) {
        this.fflt = fflt;
    }

    public String getFflx() {
        return fflx;
    }

    public void setFflx(String fflx) {
        this.fflx = fflx;
    }

    public String getFct1() {
        return fct1;
    }

    public void setFct1(String fct1) {
        this.fct1 = fct1;
    }

    public String getFct2() {
        return fct2;
    }

    public void setFct2(String fct2) {
        this.fct2 = fct2;
    }

    public String getFcla() {
        return fcla;
    }

    public void setFcla(String fcla) {
        this.fcla = fcla;
    }

    public String getFfst() {
        return ffst;
    }

    public void setFfst(String ffst) {
        this.ffst = ffst;
    }

    public String getFnat() {
        return fnat;
    }

    public void setFnat(String fnat) {
        this.fnat = fnat;
    }

    public String getFtof() {
        return ftof;
    }

    public void setFtof(String ftof) {
        this.ftof = ftof;
    }
}
