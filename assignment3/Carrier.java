package assignments.assignment3;

// Mengimpor module yang dibutuhkam
import java.util.ArrayList;
import java.util.List;

public abstract class Carrier{

    // Menyiapkan atribut yang dibutuhkan
    private String nama;
    private String tipe;
    private Status statusCovid;
    private int aktifKasusDisebabkan;
    private int totalKasusDisebabkan;
    private List<Carrier> rantaiPenular;

    /**
     * Constructor dari kelas Carrier.
     * @param nama -> nama dari objek carrier.
     * @param tipe -> tipe dari objek carrier.
     */
    public Carrier(String nama, String tipe){
        this.nama = nama;
        this.tipe = tipe;
        this.aktifKasusDisebabkan = 0;
        this.totalKasusDisebabkan = 0;
        this.rantaiPenular = new ArrayList<>();
    }

    /**
     * Getter dari atribut nama objek.
     * @return nama carrier.
     */
    public String getNama(){
        return this.nama;
    }

    /**
     * Getter dari atribut tipe objek.
     * @return tipe carrier.
     */
    public String getTipe(){
        return this.tipe;
    }

    /**
     * Getter status covid si objek.
     * @return status covid dari si objek.
     */
    public String getStatusCovid(){
        return statusCovid.getStatus();
    }

    /**
     * Getter dari atribut aktifKasusDisebabkan.
     * @return jumlah aktif kasus disebabkan si objek.
     */
    public int getAktifKasusDisebabkan(){
        return this.aktifKasusDisebabkan;
    }

    /**
     * Setter untuk atribut aktifKasusDisebabkan.
     * @param updateJumlahKasus -> mengassign jumlah kasus yang telah diupdate.
     */
    public void setAktifKasusDisebabkan(int updateJumlahKasus){
        this.aktifKasusDisebabkan = updateJumlahKasus;
    }

    /**
     * Getter dari atribut totalKasusDisebabkan.
     * @return jumlah total kasus disebabkan si objek.
     */
    public int getTotalKasusDisebabkan(){
        return this.totalKasusDisebabkan;
    }

    /**
     * Setter untuk atribut totalKasusDisebabkan.
     * @param updateJumlahKasus -> mengassign jumlah kasus yang telah diupdate.
     */
    public void setTotalKasusDisebabkan(int updateJumlahKasus) {
        this.totalKasusDisebabkan = updateJumlahKasus;
    }

    /**
     * Getter untuk atribut rantaiPenular si objek.
     * @return rantaiPenular si objek.
     */
    public List<Carrier> getRantaiPenular(){
        return this.rantaiPenular;
    }

    /**
     * Setter untuk atribut statusCovid dari objek.
     * @param status -> status covid dari si objek.
     */
    public void ubahStatus(String status){
        if (status.equalsIgnoreCase("positif")) {
            statusCovid = new Positif();
            rantaiPenular.add(this);
        } else {
            statusCovid = new Negatif();
        }
    }

    /**
     * Interaksi antar objek.
     * @param lain -> carrier lain yang berinteraksi dengan objek carrier ini.
     */
    public void interaksi(Carrier lain) {
        // Membuah default null
        Carrier penular = null;
        Carrier tertular = null;

        // Membuat kondisi objek yang positif menjadi penular
        if (this.getStatusCovid().equalsIgnoreCase("positif")) {
            penular = this;
            tertular = lain;
        } else if (lain.getStatusCovid().equalsIgnoreCase("positif")) {
            penular = lain;
            tertular = this;
        } else {
            // Jika kasus objek keduanya negatif tidak terjadi apa apa
            return;
        }

        // Menjalankan penularan virus corona
        penular.statusCovid.tularkan(penular, tertular);

    }

    /**
     * Membuat abstract method untuk diimplementasikan pada subclass.
     * @return identitas objek.
     */
    public abstract String toString();

}
