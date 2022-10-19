package com.example.ptb_10_a;

public class ListPeserta{

    private String nama_peserta;
    private String nim_peserta;
    private Integer foto;

    public String getNama_peserta() {
        return nama_peserta;
    }

    public String getNim_peserta() {
        return nim_peserta;
    }

    public Integer getFoto() {return foto; }

    public void setNama_peserta(String nama_peserta) {
        this.nama_peserta = nama_peserta;
    }

    public void setNim_peserta(String nim_peserta) {
        this.nim_peserta = nim_peserta;
    }

    public void setFoto(Integer foto) {
        this.foto = foto;
    }
}
