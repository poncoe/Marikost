/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.controller;

import isenkdoank.marikost.view.MainActivity;
import isenkdoank.marikost.view.Pembayaran;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author poncoe
 */
public class PassingDataController {

    private Connection con;
    private final Koneksi koneksiPassData = new Koneksi();

    // Login Akun
    public boolean PassDataPembayaran(String username) {
        PreparedStatement ps;
        ResultSet rs;

        boolean cekData = false;

        // queri untuk mengecek apakah username & password ada di database di table autentikasi
        String query = "SELECT * FROM `autentikasi` WHERE `username` =?";

        // esepsi untuk pengecekan data
        try {
            ps = Koneksi.getConnection().prepareStatement(query);

            ps.setString(1, username);

            // mengeksekusi queri
            rs = ps.executeQuery();

            // jika ada & dicek, maka akan berhasil login dan masuk ke halaman selanjutnya
            if (rs.next()) {
                MainActivity mainAdmin = new MainActivity();
                mainAdmin.setVisible(true);
                mainAdmin.pack();
                mainAdmin.setLocationRelativeTo(null);
                mainAdmin.nama_user.setText("Hi, " + rs.getString("nama") + "");
                cekData = true;
            } else {
                JOptionPane.showMessageDialog(null, "Gagal Memuat Data!", "Peringatan", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cekData;
    }
}
