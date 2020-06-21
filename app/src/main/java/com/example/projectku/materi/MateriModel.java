package com.example.projectku.materi;

public class MateriModel {
    private String id_materi;
    private String namamateri;
    private String namapengajar;
    private String imageUrl;

    public MateriModel(String id, String namamateri, String namapengajar, String imageUrl) {
        this.id_materi = id;
        this.namamateri = namamateri;
        this.namapengajar = namapengajar;
        this.imageUrl = imageUrl;
    }

    public String getIdMateri() {
        return id_materi;
    }

    public void setIdMateri(String id_materi) {
        this.id_materi = id_materi;
    }

    public String getNamamateri() {
        return namamateri;
    }

    public void setNamamateri(String namamateri) {
        this.namamateri = namamateri;
    }

    public String getNamapengajar() {
        return namapengajar;
    }

    public void setNamapengajar(String namapengajar) {
        this.namapengajar = namapengajar;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
