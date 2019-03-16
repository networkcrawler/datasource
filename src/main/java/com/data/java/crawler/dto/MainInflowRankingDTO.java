package com.data.java.crawler.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.print.DocFlavor;
import java.nio.DoubleBuffer;
import java.util.Date;

/**
 * �������������� ʵ��
 */
@Document(collection = "main_inflow_ranking")
public class MainInflowRankingDTO {
    @Id
    private String id;
    private String symbol;//����
    private String name;//����
    private String lasttrade;//���¼�
    private String todayNetPrimary;//����������ռ��
    private Integer todayRanking;//��������
    private String todayChg;//�����ǵ�
    private String fireNetPrimary;//5��������ռ��
    private Integer fireRanking;//5������
    private String fireChg;//5���ǵ�
    private String tenNetPrimary;//10��������ռ��
    private Integer tenRanking;//10������
    private String tenChg;//10���ǵ�
    private String plateName;//�������
    private String plateSymbol;//������
    private String created;//����ʱ��
    private String updated;//����ʱ��

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
