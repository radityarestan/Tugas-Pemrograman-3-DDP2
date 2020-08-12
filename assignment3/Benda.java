package assignments.assignment3;

/**
 * Kelas yang mengextends kelas Carrier.
 */
public abstract class Benda extends Carrier{
    // Atribut kelas
    protected int persentaseMenular;

    /**
     * Constructor kelas Benda.
     * @param nama -> nama dari objek.
     */
    public Benda(String name){
        // Memanggil constructor kelas Carrier
        super(name, "Benda");
    }

    /**
     * Membuat abstract method untuk diimplementasikan pada subclass.
     * @return identitas objek.
     */
    public abstract void tambahPersentase();

    /**
     * Getter dari persentase menular.
     * @return persentase menular dari objek ini.
     */
    public int getPersentaseMenular(){
        return this.persentaseMenular;
    }

    /**
     * Setter persentase menular.
     * @param persentase -> persentase baru setelah diupdate.
     */
    public void setPersentaseMenular(int persentase) {
        this.persentaseMenular = persentase;
    }
}