/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.controller;

import isenkdoank.marikost.view.MainActivity;
import isenkdoank.marikost.view.ProfileAkun;
import isenkdoank.marikost.view.Pembayaran;
import isenkdoank.marikost.view.ProfileDataKos;
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

    // Passing Data Pembayaran
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

    // Passing Data Profil
    public boolean PassDataProfil(String username) {
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

            // jika ada & dicek sesuai role
            if (rs.next()) {
                // Admin
                if (rs.getString("jenis_akun").equals("Admin") && rs.getString("status_bayar").equals("Sudah")) {
                    ProfileAkun mainAdmin = new ProfileAkun();
                    mainAdmin.setVisible(true);
                    mainAdmin.pack();
                    mainAdmin.setLocationRelativeTo(null);
                    mainAdmin.nama_user.setText("Hi, " + rs.getString("nama") + "");
                    mainAdmin.txtUsername.setText("" + rs.getString("username") + "");
                    mainAdmin.txtPassword.setText("" + rs.getString("password") + "");
                    mainAdmin.txtEmail.setText("" + rs.getString("email") + "");
                    mainAdmin.txtJenisKelamin.setText("" + rs.getString("jenis_kelamin") + "");
                    mainAdmin.txtNotelp.setText("" + rs.getString("notelp") + "");
                    mainAdmin.txtAlamat.setText("" + rs.getString("alamat") + "");
                    cekData = true;

                    // User
                } else if (rs.getString("jenis_akun").equals("User")) {
                    ProfileAkun mainUser = new ProfileAkun();
                    mainUser.setVisible(true);
                    mainUser.pack();
                    mainUser.setLocationRelativeTo(null);
                    mainUser.nama_user.setText("Hi, " + rs.getString("nama") + "");
                    mainUser.txtUsername.setText("" + rs.getString("username") + "");
                    mainUser.txtPassword.setText("" + rs.getString("password") + "");
                    mainUser.txtEmail.setText("" + rs.getString("email") + "");
                    mainUser.txtJenisKelamin.setText("" + rs.getString("jenis_kelamin") + "");
                    mainUser.txtNotelp.setText("" + rs.getString("notelp") + "");
                    mainUser.txtAlamat.setText("" + rs.getString("alamat") + "");
                    mainUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    mainUser.btnDataKos.setVisible(false);
                    cekData = true;
                    
                    // jika Data Tidak Ditemukan
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal Memuat Data!", "Peringatan", 2);
                    cekData = false;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Gagal Memuat Data!", "Peringatan", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cekData;
    }
    
    // Passing Data Profil
    public boolean PassDataKos(String username) {
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

            // jika ada & dicek sesuai role
            if (rs.next()) {
                // Admin
                if (rs.getString("jenis_akun").equals("Admin") && rs.getString("status_bayar").equals("Sudah")) {
                    ProfileDataKos mainData = new ProfileDataKos();
                    mainData.setVisible(true);
                    mainData.pack();
                    mainData.setLocationRelativeTo(null);
                    mainData.txtNamaMitra.setText("" + rs.getString("username") + "");
                    mainData.txtPemilik.setText("" + rs.getString("nama") + "");
                    mainData.nama_mitra.setText("Hi, " + rs.getString("nama") + "");
                    cekData = true;

                } else {
                    JOptionPane.showMessageDialog(null, "Gagal Memuat Data!", "Peringatan", 2);
                    cekData = false;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Gagal Memuat Data!", "Peringatan", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cekData;
    }

}
