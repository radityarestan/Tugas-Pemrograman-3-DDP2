package assignments.assignment3;

/**
 * Kelas yang mengextends manusia.
 */
public class PekerjaJasa extends Manusia{

    /**
     * Constructor kelas PekerjaJasa.
     * @param nama -> nama si objek.
     */
    public PekerjaJasa(String nama){
        // Memanggil constructor manusia
        super(nama);
    }

    /**
     * Mengimplementasikan method toString yang ada di kelas carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "PEKERJA JASA " + super.getNama();
    }

}