/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.model;

/**
 *
 * @author poncoe
 */
public class Homey extends Pemilik {
    int id, harga;
    String jeniskos, namakos, deskripsi, sisa, wilayah, alamat;

    public Homey(int id, String username, String pemilik, String jeniskos, String namakos, String deskripsi, String sisa, String wilayah, String alamat, int kontakpemilik, int harga, String statusmoderator) {
        super(username, pemilik, kontakpemilik, statusmoderator);
        this.id = id;
        this.jeniskos = jeniskos;
        this.namakos = namakos;
        this.deskripsi = deskripsi;
        this.sisa = sisa;
        this.wilayah = wilayah;
        this.alamat = alamat;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public int getHarga() {
        return harga;
    }

    public String getJenisKos() {
        return jeniskos;
    }

    public String getNamaKos() {
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
}
