package com.data.java.crawler.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.print.DocFlavor;
import java.nio.DoubleBuffer;
import java.util.Date;

/**
 * 主力净流入排名 实体
 */
@Document(collection = "main_inflow_ranking")
public class MainInflowRankingDTO {
    @Id
    private String id;
    private String symbol;//编码
    private String name;//名称
    private String lasttrade;//最新价
    private String todayNetPrimary;//今日主力净占比
    private Integer todayRanking;//今日排名
    private String todayChg;//今日涨跌
    private String fireNetPrimary;//5日主力净占比
    private Integer fireRanking;//5日排名
    private String fireChg;//5日涨跌
    private String tenNetPrimary;//10日主力净占比
    private Integer tenRanking;//10日排名
    private String tenChg;//10日涨跌
    private String plateName;//板块名称
    private String plateSymbol;//板块编码
    private String created;//创建时间
    private String updated;//更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLasttrade() {
        return lasttrade;
    }

    public void setLasttrade(String lasttrade) {
        this.lasttrade = lasttrade;
    }

    public String getTodayNetPrimary() {
        return todayNetPrimary;
    }

    public void setTodayNetPrimary(String todayNetPrimary) {
        this.todayNetPrimary = todayNetPrimary;
    }

    public Integer getTodayRanking() {
        return todayRanking;
    }

    public void setTodayRanking(Integer todayRanking) {
        this.todayRanking = todayRanking;
    }

    public String getTodayChg() {
        return todayChg;
    }

    public void setTodayChg(String todayChg) {
        this.todayChg = todayChg;
    }

    public String getFireNetPrimary() {
        return fireNetPrimary;
    }

    public void setFireNetPrimary(String fireNetPrimary) {
        this.fireNetPrimary = fireNetPrimary;
    }

    public Integer getFireRanking() {
        return fireRanking;
    }

    public void setFireRanking(Integer fireRanking) {
        this.fireRanking = fireRanking;
    }

    public String getFireChg() {
        return fireChg;
    }

    public void setFireChg(String fireChg) {
        this.fireChg = fireChg;
    }

    public String getTenNetPrimary() {
        return tenNetPrimary;
    }

    public void setTenNetPrimary(String tenNetPrimary) {
        this.tenNetPrimary = tenNetPrimary;
    }

    public Integer getTenRanking() {
        return tenRanking;
    }

    public void setTenRanking(Integer tenRanking) {
        this.tenRanking = tenRanking;
    }

    public String getTenChg() {
        return tenChg;
    }

    public void setTenChg(String tenChg) {
        this.tenChg = tenChg;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getPlateSymbol() {
        return plateSymbol;
    }

    public void setPlateSymbol(String plateSymbol) {
        this.plateSymbol = plateSymbol;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
