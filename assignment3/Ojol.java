package assignments.assignment3;

/**
 * Kelas yang mengextends manusia.
 */
public class Ojol extends Manusia{

    /**
     * Constructor kelas Ojol.
     * @param nama -> nama si objek.
     */
    public Ojol(String nama){
        // Memanggil constructor manusia
        super(nama);
    }

    /**
     * Mengimplementasikan method toString yang ada di kelas carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "OJOL " + super.getNama();
    }

}