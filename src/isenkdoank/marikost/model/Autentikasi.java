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
    int id, notelp;
    String username, password, jenisakun, nama, alamat;
    
     public Autentikasi(int id, String username, String password, String jenisakun, String nama, String alamat, int notelp){
        this.id = id;
        this.nama = nama;
        this.password = password;
        this.jenisakun = jenisakun;
        this.nama = nama;
        this.alamat = alamat;
        this.notelp = notelp;
    }
     
     public int getId() {
        return id;
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

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }
    
}
