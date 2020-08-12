package assignments.assignment3;

/**
 * Kelas yang mengextends manusia.
 */
public class Jurnalis extends Manusia{

    /**
     * Constructor kelas Jurnalis.
     * @param nama -> nama si objek.
     */
    public Jurnalis(String nama){
        // Memanggil constructor manusia
        super(nama);
    }

    /**
     * Mengimplementasikan method toString yang ada di kelas carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "JURNALIS " + super.getNama();
    }

}