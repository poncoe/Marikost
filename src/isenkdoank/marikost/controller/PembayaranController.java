/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.controller;

import isenkdoank.marikost.model.Transaksi;
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
public class PembayaranController {

    private Connection con;
    private final Koneksi koneksiPembayaran = new Koneksi();

    // tambah data pembayaran
    public boolean tambahPembayaran(Transaksi pembayaran) throws SQLException {

        // membuka koneksi ke database
        con = koneksiPembayaran.getConnection();

        // membuat query untuk tambah data akun
        String kueri = "INSERT INTO pembayaran (username,password,status_bayar) VALUES (?,?,?)";

        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, pembayaran.getUsername());
        ps.setString(2, pembayaran.getPassword());
        ps.setString(3, pembayaran.getStatusbayar());

        // mengeksekusi query
        int rowAffected = ps.executeUpdate();

        // menutup koneksi
        ps.close();
        con.close();

        // mengembalikan nilai data untuk dirubah ke database mysql
        return rowAffected == 1;

    }

    // rubah status pembayaran jika sudah melakuakn pembayaran
    public boolean changeStatusPayments(String username, String statusbayar) throws SQLException {

        // membuka koneksi ke database
        con = koneksiPembayaran.getConnection();

        // queri sql untuk mengecek username dan merubah status bayarnya
        String queryPayments = "update `pembayaran` set `status_bayar`=? WHERE `username` =?";

        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        PreparedStatement ps = con.prepareStatement(queryPayments);
        ps.setString(1, statusbayar);
        ps.setString(2, username);

        // mengeksekusi query
        int rowAffected = ps.executeUpdate();

        // mengembalikan nilai untuk rubah status
        return rowAffected == 1;
    }

    // rubah status pembayaran jika sudah melakuakn pembayaran
    public boolean changeDataPayments(String username, String statusbayar) throws SQLException {
        
        // membuka koneksi ke database
        con = koneksiPembayaran.getConnection();

        // queri sql untuk mengecek username dan merubah status bayarnya
        String queryAuth = "update `autentikasi` set `status_bayar`=? WHERE `username` =?";

        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        PreparedStatement ps = con.prepareStatement(queryAuth);
        ps.setString(1, statusbayar);
        ps.setString(2, username);

        // mengeksekusi query
        int rowAffected = ps.executeUpdate();

        // mengembalikan nilai untuk rubah status
        return rowAffected == 1;
    }
}
