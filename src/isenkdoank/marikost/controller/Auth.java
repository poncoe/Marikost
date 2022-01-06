/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.controller;

import isenkdoank.marikost.model.Autentikasi;
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
    public boolean tambahAkun(Autentikasi auth) throws SQLException {

        // membuka koneksi ke database
        con = koneksiAuth.getConnection();

        // membuat query untuk tambah data akun
        String kueri = "INSERT INTO autentikasi (username,password,jenis_akun,nama,alamat,notelp) VALUES (?,?,?,?,?,?)";

        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, auth.getUsername());
        ps.setString(2, auth.getPassword());
        ps.setString(3, auth.getJenisAkun());
        ps.setString(4, auth.getNama());
        ps.setString(5, auth.getAlamat());
        ps.setInt(6, auth.getNotelp());

        // mengeksekusi query
        int rowAffected = ps.executeUpdate();

        // menutup koneksi
        ps.close();
        con.close();

        // mengembalikan nilai data untuk dirubah ke database mysql
        return rowAffected == 1;

    }

    // Login Akun
    public ArrayList<Auth> loginAkun(String username, String password) throws SQLException {
        ArrayList<Auth> login = new ArrayList<>();

        // membuka koneksi
        con = koneksiAuth.getConnection();

        // membuat query untuk lihat kosan & kontrakan
        String kueri = "SELECT * FROM `autentikasi` WHERE `username` =? AND `password` =?";

        PreparedStatement ps = con.prepareStatement(kueri);

        // mengeksekusi query
        ResultSet rs = ps.executeQuery();

        ps = Koneksi.getConnection().prepareStatement(kueri);

        ps.setString(1, username);
        ps.setString(2, password);

        // mengeksekusi queri
        rs = ps.executeQuery();

        // jika ada & tervalidasi sesuai jenis, maka akan berhasil login dan masuk ke halaman admin / user
        if (rs.next()) {
            if (rs.getString("level").equals("admin")) {
                ProfileMitra mainAdmin = new ProfileMitra();
                mainAdmin.setVisible(true);
                mainAdmin.pack();
                mainAdmin.setLocationRelativeTo(null);
                mainAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                mainAdmin.nama_mitra.setText("Hi, " + username + "");
                mainAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            } else if (rs.getString("level").equals("user")) {
                MainActivity mainUser = new MainActivity();
                mainUser.setVisible(true);
                mainUser.pack();
                mainUser.setLocationRelativeTo(null);
                mainUser.setExtendedState(JFrame.MAXIMIZED_BOTH);
                mainUser.nama_user.setText("Hi, " + username + "");

                // jika tidak berhasil / tidak terdaftar maka akan muncul dialog jika user belom terdaftar & salah
            } else {
                JOptionPane.showMessageDialog(null, "Akun Belum Terdaftar / Nama Pengguna atau Kata Sandi Salah!", "Login Gagal!", 2);
            }
        }

        // menutup result set, preparedstatement dan koneksi
        rs.close();
        ps.close();
        con.close();

        // mengembalikan nilai
        return login;

    }
}
