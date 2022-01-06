/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.model;

/**
 *
 * @author poncoe
 */
public class Homey {
    int id, kontakpemilik, harga;
    String username, pemilik, jeniskos, namakos, deskripsi, sisa, wilayah, alamat, gambar;

    public Homey(int id, int kontakpemilik, int harga, String username, String pemilik, String jeniskos, String namakos, String deskripsi, String sisa, String wilayah, String alamat, String gambar) {
        this.id = id;
        this.kontakpemilik = kontakpemilik;
        this.harga = harga;
        this.username = username;
        this.pemilik = pemilik;
        this.jeniskos = jeniskos;
        this.namakos = namakos;
        this.deskripsi = deskripsi;
        this.sisa = sisa;
        this.wilayah = wilayah;
        this.alamat = alamat;
        this.gambar = gambar;
    }

    public int getId() {
        return id;
    }

    public int getKontakpemilik() {
        return kontakpemilik;
    }

    public int getHarga() {
        return harga;
    }

    public String getUsername() {
        return username;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getJeniskos() {
        return jeniskos;
    }

    public String getNamakos() {
        return namakos;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getSisa() {
        return sisa;
    }

    public String getWilayah() {
        return wilayah;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getGambar() {
        return gambar;
    }
}
