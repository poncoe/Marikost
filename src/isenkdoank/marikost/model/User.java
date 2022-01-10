/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.model;

/**
 *
 * @author poncoe
 */
public class User extends Autentikasi {
    int notelp;
    String jeniskelamin, nama, alamat, email, statusbayar, jenisakun;
    
     public User(String username, String password, String jenisakun, String nama, String jeniskelamin, String alamat, int notelp, String email, String statusbayar){
        super(username,password);
        this.jenisakun = jenisakun;
        this.nama = nama;
        this.jeniskelamin = jeniskelamin;
        this.alamat = alamat;
        this.notelp = notelp;
        this.email = email;
        this.statusbayar = statusbayar;
    }

    public int getNotelp() {
        return notelp;
    }
    
    public String getJenisKelamin() {
        return jeniskelamin;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getJenisAkun() {
        return jenisakun;
    }

    public String getStatusbayar() {
        return statusbayar;
    }
    
}