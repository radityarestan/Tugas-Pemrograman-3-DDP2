package assignments.assignment3;

/**
 * Kelas yang mengextends Benda.
 */
public class TombolLift extends Benda{
    /**
     * Constructor dari kelas TombolLift.
     * @param name -> nama dari objek.
     */
    public TombolLift(String name){
        // Memanggil constructor benda
        super(name);
    }

    /**
     * Mengimplementasikan method tambahPersentase yang ada di kelas Benda (sesuai ketentuan subclas masing masing).
     */
    @Override
    public void tambahPersentase() {
        setPersentaseMenular(getPersentaseMenular() + 20);
    }

    /**
     * Mengimplementasikan method toString yang ada di kelas carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "TOMBOL LIFT " + super.getNama();
    }
}