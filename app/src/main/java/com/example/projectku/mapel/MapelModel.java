package com.example.projectku.mapel;

public class MapelModel {
    private String id_mapel;
    private String hari;
    private String jamMulai;
    private String jamSelesai;
    private String namapengajar;
    private String namaMapel;

    public MapelModel(String id_mapel, String hari, String jamMulai, String jamSelesai, String namapengajar, String namaMapel) {
        this.id_mapel = id_mapel;
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.namapengajar = namapengajar;
        this.namaMapel = namaMapel;
    }

    public String getId_mapel() {
        return id_mapel;
    }

    public void setId_mapel(String id_mapel) {
        this.id_mapel = id_mapel;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getNamapengajar() {
        return namapengajar;
    }

    public void setNamapengajar(String namapengajar) {
        this.namapengajar = namapengajar;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }
}
