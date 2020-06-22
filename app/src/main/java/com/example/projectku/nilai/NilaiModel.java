package com.example.projectku.nilai;

public class NilaiModel {
    private String id_tugas;
    private String tugas;
    private String nilai;
    private String jammengerjakan;

    public NilaiModel(String id_nilai, String tugas, String nilai, String jammengerjakan) {
        this.id_tugas = id_nilai;
        this.tugas = tugas;
        this.nilai = nilai;
        this.jammengerjakan = jammengerjakan;
    }

    public String getId_nilai() {
        return id_tugas;
    }

    public void setId_nilai(String id_tugas) {
        this.id_tugas = id_tugas;
    }

    public String getTugas() {
        return tugas;
    }

    public void setTugas(String tugas) {
        this.tugas = tugas;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getJammengerjakan() {
        return jammengerjakan;
    }

    public void setJammengerjakan(String jammengerjakan) {
        this.jammengerjakan = jammengerjakan;
    }

}
