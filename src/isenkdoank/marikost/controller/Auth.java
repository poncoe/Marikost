/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.controller;

import isenkdoank.marikost.model.Autentikasi;
import isenkdoank.marikost.model.User;
import isenkdoank.marikost.view.Login;
import isenkdoank.marikost.view.MainActivity;
import isenkdoank.marikost.view.ProfileMitra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author poncoe
 */
public class Auth {

    private Connection con;
    private final Koneksi koneksiAuth = new Koneksi();

    // mengecek apakah username sudah ada atau belum
    public boolean cekUsername(String username) {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkUsername = false;

        // queri sql untuk mengecek username
        String query = "SELECT * FROM `autentikasi` WHERE `username` =?";

        // melakukan eksepsi untuk mengetahui jika ada error (try & catch)
        try {
            // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
            ps = Koneksi.getConnection().prepareStatement(query);
            ps.setString(1, username);

            // mengeksekusi query
            rs = ps.executeQuery();

            // pengecekan apakah usernamenya sudah ada atau tidak
            if (rs.next()) {
                checkUsername = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }

        // mengembalikan nilai untuk pengecekan username
        return checkUsername;
    }

    // tambah data akun
    public boolean tambahAkun(User auth) throws SQLException {

        // membuka koneksi ke database
        con = koneksiAuth.getConnection();

        // membuat query untuk tambah data akun
        String kueri = "INSERT INTO autentikasi (username,password,jenis_akun,jenis_kelamin,nama,alamat,notelp,email,status_bayar) VALUES (?,?,?,?,?,?,?,?,?)";

        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, auth.getUsername());
        ps.setString(2, auth.getPassword());
        ps.setString(3, auth.getJenisAkun());
        ps.setString(4, auth.getJenisKelamin());
        ps.setString(5, auth.getNama());
        ps.setString(6, auth.getAlamat());
        ps.setInt(7, auth.getNotelp());
        ps.setString(8, auth.getEmail());
        ps.setString(9, auth.getStatusbayar());

        // mengeksekusi query
        int rowAffected = ps.executeUpdate();

        // menutup koneksi
        ps.close();
        con.close();

        // mengembalikan nilai data untuk dirubah ke database mysql
        return rowAffected == 1;

    }

    // Login Akun
    public boolean LoginAkun(String username, String password) {
        PreparedStatement ps;
        ResultSet rs;

        boolean cekLogin = false;

        // queri untuk mengecek apakah username & password ada di database di table autentikasi
        String query = "SELECT * FROM `autentikasi` WHERE `username` =? AND `password` =?";

        // kodingan untuk pengecekan (jika user tidak mengisi field)
        if (username.equals("")) {
            JOptionPane.showMessageDialog(null, "Masukan Username!");
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Masukan Kata Sandi!");
        } else {
            try {
                ps = Koneksi.getConnection().prepareStatement(query);

                ps.setString(1, username);
                ps.setString(2, password);

                // mengeksekusi queri
                rs = ps.executeQuery();

                // jika ada & dicek, maka akan berhasil login dan masuk ke halaman selanjutnya
                if (rs.next()) {

                    // Jika Admin Sudah Bayar ke Marikost
                    if (rs.getString("jenis_akun").equals("Admin") && rs.getString("status_bayar").equals("Sudah")) {
                        MainActivity mainAdmin = new MainActivity();
                        mainAdmin.setVisible(true);
                        mainAdmin.pack();
                        mainAdmin.setLocationRelativeTo(null);
                        mainAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        mainAdmin.nama_user.setText("Hi, " + rs.getString("nama") + "");
                        cekLogin = true;

                        // Jika Admin Belum Bayar ke Marikost
                    } else if (rs.getString("jenis_akun").equals("Admin") && rs.getString("status_bayar").equals("Belum")) {
                        MainActivity mainPreAdmin = new MainActivity();
                        mainPreAdmin.setVisible(true);
                        mainPreAdmin.pack();
                        mainPreAdmin.setLocationRelativeTo(null);
                        mainPreAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        mainPreAdmin.nama_user.setText("Hi, " + rs.getString("nama") + "");
                        mainPreAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        cekLogin = true;

                        // User
                    } else if (rs.getString("jenis_akun").equals("User")) {
                        MainActivity mainUser = new MainActivity();
                        mainUser.setVisible(true);
                        mainUser.pack();
                        mainUser.setLocationRelativeTo(null);
                        mainUser.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        mainUser.nama_user.setText("Hi, " + rs.getString("nama") + "");
                        mainUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        cekLogin = true;

                        // jika tidak berhasil / tidak terdaftar maka akan muncul dialog jika user belom terdaftar & salah
                    } else {
                        JOptionPane.showMessageDialog(null, "Akun Belum Terdaftar / Nama Pengguna atau Kata Sandi Salah!", "Login Gagal!", 2);
                        cekLogin = false;
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cekLogin;
    }

}
