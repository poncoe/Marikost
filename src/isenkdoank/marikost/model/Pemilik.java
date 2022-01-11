/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.model;

/**
 *
 * @author poncoe
 */
public class Pemilik {
    String username, pemilik, alamat;
    int kontakpemilik;
    
    public Pemilik(String username, String pemilik, int kontakpemilik, String alamat) {
        this.username = username;
        this.pemilik = pemilik;
        this.alamat = alamat;
        this.kontakpemilik = kontakpemilik;
    }

    public int getKontakPemilik() {
        return kontakpemilik;
    }

    public String getUsername() {
        return username;
    }

    public String getPemilik() {
        return pemilik;
    }

    public String getAlamat() {
        return alamat;
    }
}
