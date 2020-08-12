package assignments.assignment3;

/**
 * Kelas yang mengextends manusia.
 */
public class PetugasMedis extends Manusia{
    // Menyiapkan atribut yang dibutuhkan
    private int jumlahDisembuhkan;

    /**
     * Constructor kelas PetugasMedis.
     * @param nama -> nama si objek.
     */
    public PetugasMedis(String nama){
        // Memanggil constructor manusia
        super(nama);
        this.jumlahDisembuhkan = 0;
    }

    /**
     * Mengobati manusia yang terkena covid.
     * @param manusia -> objek manusia yang positif corona.
     */
    public void obati(Manusia manusia) {
        // Mengubah status objek positif corona, menambahkan jumlah yang disembuhkan
        // Menambahkan jumlah sembuh juga
        manusia.ubahStatus("negatif");
        this.jumlahDisembuhkan++;
        tambahSembuh();
    }

    /**
     * Getter jumlah manusia yang disembuhkan dari objek PetugasMedis ini.
     * @return jumlah manusia yang disembuhkan.
     */
    public int getJumlahDisembuhkan(){
        // TODO: Kembalikan nilai dari atribut jumlahDisembuhkan
        return this.jumlahDisembuhkan;
    }

    /**
     * Mengimplementasikan method toString yang ada di kelas carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "PETUGAS MEDIS " + super.getNama();
    }
}