package assignments.assignment3;

/**
 * Kelas yang mengextends manusia.
 */
public class CleaningService extends Manusia{
    // Menyiapkan atribut yang dibutuhkan
    private int jumlahDibersihkan;

    /**
     * Constructor kelas CleaningService.
     * @param nama -> nama si objek.
     */
    public CleaningService(String nama){
        // Memanggil constructor manusia
        super(nama);
        this.jumlahDibersihkan = 0;
    }

    /**
     * Membersihkan benda yang terkena covid.
     * @param benda -> objek yang terkan covid.
     */
    public void bersihkan(Benda benda){
        // Mengubah status dari benda jika positif
        if (benda.getStatusCovid().equalsIgnoreCase("positif"))
            benda.ubahStatus("negatif");

        // Mengubah persentase menjadi nol dan jumlah dibersihkan ditambah 1
        benda.setPersentaseMenular(0);
        this.jumlahDibersihkan++;
    }

    /**
     * Getter jumlah benda yang dibersihkan dari objek cleaning service ini.
     * @return jumlah benda yang dibersihkan.
     */
    public int getJumlahDibersihkan(){
        return this.jumlahDibersihkan;
    }

    /**
     * Mengimplementasikan method toString yang ada di kelas carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "CLEANING SERVICE " + super.getNama();
    }
}