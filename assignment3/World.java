package assignments.assignment3;

// Mengimpor module yang dibutuhkan
import java.util.ArrayList;
import java.util.List;

public class World{
    // Menyiapkan atribut yang akan digunakan
    public List<Carrier> listCarrier;

    /**
     * Constructor dari kelas World.
     */
    public World(){
        listCarrier = new ArrayList<>();
    }

    /**
     * Membuat objek berdasarkan tipe yang diinginkan user.
     * @param tipe -> tipe yang akan menentukan pembuatan objek.
     * @param nama -> nama dari objek yang sudah dibuat.
     * @return objek carrier yang telah dibuat.
     */
    public Carrier createObject(String tipe, String nama){
        // Membuat default null pada objek carrier lalu membuatnya sesuai dengan tipenya
        Carrier carrier = null;
        if (tipe.equalsIgnoreCase("cleaning_service")) {
            carrier = new CleaningService(nama);
        } else if (tipe.equalsIgnoreCase("jurnalis")) {
            carrier = new Jurnalis(nama);
        } else if (tipe.equalsIgnoreCase("ojol")) {
            carrier = new Ojol(nama);
        } else if (tipe.equalsIgnoreCase("pekerja_jasa")) {
            carrier = new PekerjaJasa(nama);
        } else if (tipe.equalsIgnoreCase("petugas_medis")) {
            carrier = new PetugasMedis(nama);
        } else if (tipe.equalsIgnoreCase("angkutan_umum")) {
            carrier = new AngkutanUmum(nama);
        } else if (tipe.equalsIgnoreCase("pegangan_tangga")) {
            carrier = new PeganganTangga(nama);
        } else if (tipe.equalsIgnoreCase("pintu")) {
            carrier = new Pintu(nama);
        } else if (tipe.equalsIgnoreCase("tombol_lift")) {
            carrier = new TombolLift(nama);
        }

        // Menambahkan carrier yang telah dibuat ke dalam list carrier dan mengembalikan objek yang telah dibuat
        listCarrier.add(carrier);
        return carrier;
    }

    /**
     * Mendapatkan carrier yang sesuai dengan nama yang diinginkan.
     * @param nama -> nama dari objek carrier yang ingin didapatkan.
     * @return objek carrier yang diinginkan.
     */
    public Carrier getCarrier(String nama){
        Carrier carrier = null;
        for (Carrier carrier1 : listCarrier) {
            if (carrier1.getNama().equalsIgnoreCase(nama)){
                carrier = carrier1;
            }
        }
        return carrier;
    }
}

