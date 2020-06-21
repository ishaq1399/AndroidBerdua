package com.example.projectku.kelas;

public class KelasModel {
    private String nomor;
    private String namaSiswa;

    public KelasModel(String nomor, String namaSiswa) {
        this.nomor = nomor;
        this.namaSiswa = namaSiswa;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) { this.nomor = nomor; }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }



}
