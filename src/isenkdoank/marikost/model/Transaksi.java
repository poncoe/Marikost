/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.model;

/**
 *
 * @author poncoe
 */
public class Transaksi extends Autentikasi  {
    String statusbayar;

    public Transaksi(String username, String password, String statusbayar) {
        super(username,password);
        this.statusbayar = statusbayar;
    }

    public String getStatusbayar() {
        return statusbayar;
    }
    
}
