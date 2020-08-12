package assignments.assignment3;

/**
 * Kelas yang mengextends kelas Carrier.
 */
public abstract class Manusia extends Carrier{
    // Attribute kelas
    private static int jumlahSembuh = 0;

    /**
     * Constructor kelas Manusia.
     * @param nama -> nama dari objek.
     */
    public Manusia(String nama){
        // Memanggil constrtucor kelas carrier
        super(nama, "Manusia");
    }

    /**
     * Setter jumlah sembuh.
     */
    public void tambahSembuh(){
        jumlahSembuh++;
    }

    /**
     * Getter jumlah sembuh.
     * @return jumlah sembuh manusia dari covid.
     */
    public static int getJumlahSembuh() {
        return jumlahSembuh;
    }

}