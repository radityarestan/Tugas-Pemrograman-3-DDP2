package assignments.assignment3;

// Mengimpor module yang dibutuhkan
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas yang mengimplement interface status.
 */
public class Positif implements Status{

    /**
     * Memberi nilai balik status covid si objek.
     * @return status dari objek yang negatif corona.
     */
    public String getStatus(){
        return "Positif";
    }

    /**
     * Menularkan corona dari objek penular ke tertular.
     * @param penular -> objek yang positif corona.
     * @param tertular -> objek yang ditularkan oleh si penular.
     */
    public void tularkan(Carrier penular, Carrier tertular){
        // Kasus jika objek penular dan tertular adalah benda
        if (penular.getTipe().equals("Benda") && tertular.getTipe().equals("Benda")) {
            return;
        }

        // Kasus jika objek yang tertular adalah benda
        if (tertular.getTipe().equals("Benda")) {
            Benda benda = (Benda) tertular;
            benda.tambahPersentase();
            // Kasus jika persentase belum 100 akan memberhentikan method
            if (benda.getPersentaseMenular() < 100)
                return;
        }

        // Menambahkan rantai penularan dari si penular ke tertular
        for (Carrier carrier : penular.getRantaiPenular()) {
            tertular.getRantaiPenular().add(carrier);
        }

        // Mengubah status si tertular
        tertular.ubahStatus("Positif");

        // Menambah total kasus disebabkan dan aktif kasus disebabkan dari rantai penular si penular
        List<Carrier> rantaiPenularTemp = new ArrayList<>();
        for (Carrier carrier : penular.getRantaiPenular()) {
            if (!rantaiPenularTemp.contains(carrier)) {
                rantaiPenularTemp.add(carrier);
                carrier.setTotalKasusDisebabkan(carrier.getTotalKasusDisebabkan() + 1);
                carrier.setAktifKasusDisebabkan(carrier.getAktifKasusDisebabkan() + 1);
            }
        }
    }
}