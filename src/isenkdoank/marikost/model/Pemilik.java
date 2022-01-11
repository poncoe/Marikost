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
    String username, pemilik, statusModerator;
    int kontakpemilik;
    
    public Pemilik(String username, String pemilik, int kontakpemilik, String statusmoderator) {
        this.username = username;
        this.pemilik = pemilik;
        this.kontakpemilik = kontakpemilik;
        this.statusModerator = statusmoderator;
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

    public String getStatusModerator() {
        return statusModerator;
    }
}
