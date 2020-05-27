package com.c.lte46;

public class Mahasiswa {
    private String stb, nama, angkatan;

    public Mahasiswa(String stb, String nama, String angkatan) {
        this.stb = stb;
        this.nama = nama;
        this.angkatan = angkatan;
    }

    public String getStb() {
        return stb;
    }

    public String getNama() {
        return nama;
    }

    public String getAngkatan() {
        return angkatan;
    }
}
