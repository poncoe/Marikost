/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.model;

/**
 *
 * @author poncoe
 */
public class Autentikasi {
    int notelp;
    String username, password, jenisakun, jeniskelamin, nama, alamat, email;
    
     public Autentikasi(String username, String password, String jenisakun, String jeniskelamin, String nama, String alamat, int notelp, String email){
        this.username = username;
        this.password = password;
        this.jenisakun = jenisakun;
        this.jeniskelamin = jeniskelamin;
        this.nama = nama;
        this.alamat = alamat;
        this.notelp = notelp;
        this.email = email;
    }

    public int getNotelp() {
        return notelp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJenisAkun() {
        return jenisakun;
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
    
}
