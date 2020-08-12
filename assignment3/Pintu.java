package assignments.assignment3;

/**
 * Kelas yang mengextends Benda.
 */
public class Pintu extends Benda{
    /**
     * Constructor dari kelas Pintu.
     * @param name -> nama dari objek.
     */
    public Pintu(String name){
        // Memanggil constructor benda
        super(name);
    }

    /**
     * Mengimplementasikan method tambahPersentase yang ada di kelas Benda (sesuai ketentuan subclas masing masing).
     */
    @Override
    public void tambahPersentase() {
        setPersentaseMenular(getPersentaseMenular() + 30);
    }

    /**
     * Mengimplementasikan method toString yang ada di kelas carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "PINTU " + super.getNama();
    }
}