package assignments.assignment3;

/**
 * Kelas yang mengextends Benda.
 */
public class PeganganTangga extends Benda{
    /**
     * Constructor dari kelas PeganganTangga.
     * @param name -> nama dari objek.
     */
    public PeganganTangga(String name){
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
     * Mengimplementasikan method toString yang ada di kelas Carrier.
     * @return detail dari si objek.
     */
    @Override
    public String toString() {
        return "PEGANGAN TANGGA " + super.getNama();
    }

}