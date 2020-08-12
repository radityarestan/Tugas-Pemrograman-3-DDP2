package assignments.assignment3;

/**
 * Kelas yang mengimplement interface status.
 */
public class Negatif implements Status{

    /**
     * Memberi nilai balik status covid si objek.
     * @return status dari objek yang negatif corona.
     */
    public String getStatus(){
        return "Negatif";
    }

    /**
     * Tidak melakukan apa-apa karena pasien negatif tidak menularkan corona.
     * @param penular -> carrier yang menjadi penular.
     * @param tertular -> carrier yang menjadi tertular.
     */
    public void tularkan(Carrier penular, Carrier tertular){
        return;
    }
}