/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.controller;

import isenkdoank.marikost.model.Autentikasi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     public boolean tambahAkun(Autentikasi auth) throws SQLException{
        
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
        ps.close(); con.close();
        
        // mengembalikan nilai data untuk dirubah ke database mysql
        return rowAffected == 1;
        
    }
}
