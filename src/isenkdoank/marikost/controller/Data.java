/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isenkdoank.marikost.controller;

import isenkdoank.marikost.model.Homey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author poncoe
 */
public class Data {

    private Connection con;
    private final Koneksi koneksiData = new Koneksi();

    // mengecek apakah username sudah ada atau belum
    public boolean cekUsername(String nama) {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkNama = false;

        // queri sql untuk mengecek nama
        String query = "SELECT * FROM `homey` WHERE `nama_kost` =?";

        // melakukan eksepsi untuk mengetahui jika ada error (try & catch)
        try {
            // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
            ps = Koneksi.getConnection().prepareStatement(query);
            ps.setString(1, nama);

            // mengeksekusi query
            rs = ps.executeQuery();

            // pengecekan apakah usernamenya sudah ada atau tidak
            if (rs.next()) {
                checkNama = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        // mengembalikan nilai untuk pengecekan nama
        return checkNama;
    }

    // TAMPIL DATA kosan & kontrakan
    public ArrayList<Homey> tampilData() throws SQLException {
        ArrayList<Homey> tampil = new ArrayList<>();

        // membuka koneksi
        con = koneksiData.getConnection();

        // membuat query untuk lihat kosan & kontrakan
        String kueri = "SELECT * FROM homey";
        PreparedStatement ps = con.prepareStatement(kueri);

        // mengeksekusi query
        ResultSet rs = ps.executeQuery();

        // melakukan perulangan untuk menampilkan seluruh data yang ada di tabel homey
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String pemilik = rs.getString("pemilik");
            String jeniskos = rs.getString("jenis_kost");
            String namakos = rs.getString("nama_kost");
            String deskripsi = rs.getString("deskripsi_kost");
            String sisakos = rs.getString("sisa");
            String wilayah = rs.getString("wilayah_kost");
            String alamat = rs.getString("alamat");
            int kontak = rs.getInt("kontak_penilik");
            int harga = rs.getInt("harga");
            String gambar = rs.getString("gambar");

            Homey listdata = new Homey(id, username, pemilik, jeniskos, namakos, deskripsi, sisakos, wilayah,
            alamat, kontak, harga, gambar);
            tampil.add(listdata);
        }
        
        // menutup result set, preparedstatement dan koneksi
        rs.close();
        ps.close();
        con.close();
        
        // mengembalikan nilai
        return tampil;
    }

    // TAMBAH DATA kosan & kontrakan
    public boolean tambahData(Homey data) throws SQLException {

        // membuka koneksi ke database
        con = koneksiData.getConnection();

        // membuat query untuk tambah data akun
        String kueri = "INSERT INTO homey (username, pemilik, jenis_kost, nama_kost, deskripsi_kost, sisa, wilayah_kost, alamat, kontak_pemilik, harga, gambar) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, data.getUsername());
        ps.setString(2, data.getPemilik());
        ps.setString(3, data.getJenisKos());
        ps.setString(4, data.getNamaKos());
        ps.setString(5, data.getDeskripsi());
        ps.setString(6, data.getSisa());
        ps.setString(7, data.getWilayah());
        ps.setString(8, data.getAlamat());
        ps.setInt(9, data.getKontakPemilik());
        ps.setInt(10, data.getHarga());
        ps.setString(11, data.getGambar());

        // mengeksekusi query
        int rowAffected = ps.executeUpdate();

        // menutup koneksi
        ps.close();
        con.close();

        // mengembalikan nilai data untuk dirubah ke database mysql
        return rowAffected == 1;
    }
    
    public boolean ubahData(Homey data) throws SQLException{
        
        // membuka koneksi ke database
        con = koneksiData.getConnection();
        
        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        String kueri = "UPDATE homey SET nama_kost=?, deskripsi_kost=?, sisa=?, wilayah_kost=?, alamat=?, kontak_pemilik=?, harga=?, gambar=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(kueri);
//        ps.setString(1, data.getUsername());
//        ps.setString(2, data.getPemilik());
//        ps.setString(3, data.getJenisKos());
        ps.setString(1, data.getNamaKos());
        ps.setString(2, data.getDeskripsi());
        ps.setString(3, data.getSisa());
        ps.setString(4, data.getWilayah());
        ps.setString(5, data.getAlamat());
        ps.setInt(6, data.getKontakPemilik());
        ps.setInt(7, data.getHarga());
        ps.setString(8, data.getGambar());
        
        // mengeksekusi query
        int rowAffected = ps.executeUpdate();
        
        // menutup preparedstatement & koneksi
        ps.close(); con.close();
        
        // mengembalikan nilai data untuk dirubah ke database mysql
        return rowAffected == 1;
    }
    
    public boolean hapusData(String id) throws SQLException{
        
        // membuka koneksi ke database
        con = koneksiData.getConnection();
        
        // Menyiapkan database / memanipulasi data untuk dikiirm kedatabase untuk dieksekusi
        String kueri = "DELETE FROM homey WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, id); 
        
        // mengeksekusi query
        int rowAffected = ps.executeUpdate();
        
        // menutup preparedstatement & koneksi
        ps.close(); con.close();
        
        // mengembalikan nilai data untuk dirubah ke database mysql
        return rowAffected == 1;
    }
    
    // CARI DATA Wilayah kos & kontrakan
    public ArrayList<Homey> cariPelanggan(String keyword) throws SQLException{
        ArrayList<Homey> caridata = new ArrayList<>();
        
        //langkah 1
        con = koneksiData.getConnection();
        
        //langkah 2
        String kueri = "SELECT * FROM homey WHERE wilayah LIKE ?";
        PreparedStatement ps = con.prepareStatement(kueri);
        ps.setString(1, "%"+keyword+"%"); ps.setString(2, keyword);
        ps.setString(3, keyword);  
        
        //langkah 3
        ResultSet rs = ps.executeQuery();
        
        //langkah 4
        while(rs.next()){
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String pemilik = rs.getString("pemilik");
            String jeniskos = rs.getString("jenis_kost");
            String namakos = rs.getString("nama_kost");
            String deskripsi = rs.getString("deskripsi_kost");
            String sisakos = rs.getString("sisa");
            String wilayah = rs.getString("wilayah_kost");
            String alamat = rs.getString("alamat");
            int kontak = rs.getInt("kontak_penilik");
            int harga = rs.getInt("harga");
            String gambar = rs.getString("gambar");

            Homey listcaridata = new Homey(id, username, pemilik, jeniskos, namakos, deskripsi, sisakos, wilayah,
            alamat, kontak, harga, gambar);
            caridata.add(listcaridata);
        }
        
        //langkah 5
        rs.close(); ps.close();con.close();
        return caridata;
        
    }
    
    // Filter Harga Termurah - Termahal data kosan & kontrakan
    public ArrayList<Homey> filterHargaTermurah() throws SQLException {
        ArrayList<Homey> tampil = new ArrayList<>();

        // membuka koneksi
        con = koneksiData.getConnection();

        // membuat query untuk lihat kosan & kontrakan
        String kueri = "SELECT * FROM homey order by harga asc";
        PreparedStatement ps = con.prepareStatement(kueri);

        // mengeksekusi query
        ResultSet rs = ps.executeQuery();

        // melakukan perulangan untuk menampilkan seluruh data yang ada di tabel homey
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String pemilik = rs.getString("pemilik");
            String jeniskos = rs.getString("jenis_kost");
            String namakos = rs.getString("nama_kost");
            String deskripsi = rs.getString("deskripsi_kost");
            String sisakos = rs.getString("sisa");
            String wilayah = rs.getString("wilayah_kost");
            String alamat = rs.getString("alamat");
            int kontak = rs.getInt("kontak_penilik");
            int harga = rs.getInt("harga");
            String gambar = rs.getString("gambar");

            Homey listdata = new Homey(id, username, pemilik, jeniskos, namakos, deskripsi, sisakos, wilayah,
            alamat, kontak, harga, gambar);
            tampil.add(listdata);
        }
        
        // menutup result set, preparedstatement dan koneksi
        rs.close();
        ps.close();
        con.close();
        
        // mengembalikan nilai
        return tampil;
    }
    
    // Filter Harga Termahal - Termurah data kosan & kontrakan
    public ArrayList<Homey> filterHargaTermahal() throws SQLException {
        ArrayList<Homey> tampil = new ArrayList<>();

        // membuka koneksi
        con = koneksiData.getConnection();

        // membuat query untuk lihat kosan & kontrakan
        String kueri = "SELECT * FROM homey order by harga desc";
        PreparedStatement ps = con.prepareStatement(kueri);

        // mengeksekusi query
        ResultSet rs = ps.executeQuery();

        // melakukan perulangan untuk menampilkan seluruh data yang ada di tabel homey
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String pemilik = rs.getString("pemilik");
            String jeniskos = rs.getString("jenis_kost");
            String namakos = rs.getString("nama_kost");
            String deskripsi = rs.getString("deskripsi_kost");
            String sisakos = rs.getString("sisa");
            String wilayah = rs.getString("wilayah_kost");
            String alamat = rs.getString("alamat");
            int kontak = rs.getInt("kontak_penilik");
            int harga = rs.getInt("harga");
            String gambar = rs.getString("gambar");

            Homey listdata = new Homey(id, username, pemilik, jeniskos, namakos, deskripsi, sisakos, wilayah,
            alamat, kontak, harga, gambar);
            tampil.add(listdata);
        }
        
        // menutup result set, preparedstatement dan koneksi
        rs.close();
        ps.close();
        con.close();
        
        // mengembalikan nilai
        return tampil;
    }

}
