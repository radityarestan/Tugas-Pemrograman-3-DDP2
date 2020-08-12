package assignments.assignment3;

// Mengimpor module yang dibutuhka
import java.io.*;

public class InputOutputGUI {
	// Menyiapkan atribut
    private String tipeInput;
    private String fileInput;
    private String tipeOutput;
    private String fileOutput;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private World world;

    /**
     * Constructor kelas InputOutputGUI.
     * @param tipeInput -> tipe dari input.
     * @param fileInput -> nama file input.
     * @param tipeOutput -> tipe dari output.
     * @param fileOutput -> nama file output.
     */
    public InputOutputGUI(String tipeInput, String fileInput, String tipeOutput, String fileOutput) {
        this.tipeInput = tipeInput;
        this.fileInput = fileInput;
        this.tipeOutput = tipeOutput;
        this.fileOutput = fileOutput;
        this.world = new World();
    }

    /**
     * Setter dari attribute bufferedReader.
     */
    public void setBufferedReader() {
        try {
            bufferedReader = new BufferedReader(new FileReader(fileInput));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Getter attribute bufferedReader.
     * @return objek bufferedReader.
     */
    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

    /**
     * Setter attribute printWriter.
     */
    public void setPrintwriter() {
        try {
            printWriter = new PrintWriter(new FileWriter(fileOutput));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Program utama untuk memproses query.
     * @param query -> perintah dari user.
     */
    public void run(String query) {
        // Mengsplit data data pada input yang dimasukkan
        String hasil = "";
        String[] data_query = query.split(" ");
        String perintah = data_query[0];

        // Perintah untuk membentuk objek dan membuat status dari objek tersebut default negatif
        if (perintah.equalsIgnoreCase("add")) {
            String tipe = data_query[1];
            String nama = data_query[2];
            Carrier carrier = world.createObject(tipe, nama);
            carrier.ubahStatus("Negatif");
            // Merefresh hasil
            hasil = "";
        }
        // Perintah untuk mempositifkan sebuah objek
        else if (perintah.equalsIgnoreCase("positifkan")) {
            Carrier carrier = world.getCarrier(data_query[1]);
            carrier.ubahStatus("Positif");
            // Merefresh hasil
            hasil = "";
        }
        // Perintah untuk melakukan interaksi antar objek
        else if (perintah.equalsIgnoreCase("interaksi")) {
            Carrier carrier1 = world.getCarrier(data_query[1]);
            Carrier carrier2 = world.getCarrier(data_query[2]);
            carrier1.interaksi(carrier2);
            // Merefresh hasil
            hasil = "";
        }
        // Perintah untuk mendapatkan rantai penyebaran dari sebuah objek carrier
        else if (perintah.equalsIgnoreCase("rantai")) {
            Carrier carrier = world.getCarrier(data_query[1]);
            hasil = "Rantai penyebaran " + carrier.toString() + ": ";

            // Mencoba untuk mendapatkan rantai, jika ternyata objek belum tertular akan mengthrow exception
            try {
                // Jika rantai penularan belum ada (si objek belum tertular)
                if (carrier.getRantaiPenular().size() == 0) {
                    throw new BelumTertularException(carrier.toString() + " berstatus negatif");
                }
                // Mendapatkan strirng rantai penularan dari list rantai penular si objek
                for (Carrier siCarrier : carrier.getRantaiPenular()) {
                    // Kasus jika ini adalah objek terakhir yang tertular
                    if (siCarrier.equals(carrier.getRantaiPenular().get(carrier.getRantaiPenular().size() - 1))) {
                        hasil += siCarrier.toString();
                    }
                    // Kasus jika selain yang terakhir
                    else {
                        hasil += siCarrier.toString() + " -> ";
                    }
                }
            }
            // Menuliskan pesan exception ke dalam string hasil
            catch (BelumTertularException e) {
                hasil = e.toString();
            }
        }
        // Perintah untuk mendapatkan total kasus yang disebabkan oleh si objek
        else if (perintah.equalsIgnoreCase("total_kasus_dari_objek")) {
            Carrier carrier = world.getCarrier(data_query[1]);
            hasil = String.format("%s telah menyebarkan virus COVID ke %d objek", carrier.toString(), carrier.getTotalKasusDisebabkan());
        }
        // Perintah untuk mendapatkan total kasus yang disebabkan oleh si objek dan masih positif
        else if (perintah.equalsIgnoreCase("aktif_kasus_dari_objek")) {
            Carrier carrier = world.getCarrier(data_query[1]);
            hasil = String.format("%s telah menyebarkan virus COVID dan masih teridentifikasi positif " +
                    "sebanyak %d objek", carrier.toString(), carrier.getAktifKasusDisebabkan());
        }
        // Perintah unutk menyembuhkan objek yang positif corona oleh petugas medis
        else if (perintah.equalsIgnoreCase("sembuhkan")) {
            // Melakukan downcasting menjadi objek kelas petugas medis (dipastikan petugas medis)
            Carrier carrier1 = world.getCarrier(data_query[1]);
            PetugasMedis petugasMedis = (PetugasMedis) carrier1;

            // Melakukan downcasting menjadi objek kelas manusia (dipastikan manusia positif corona)
            Carrier carrier2 = world.getCarrier(data_query[2]);
            Manusia pasienPositif = (Manusia) carrier2;

            // Mengobati pasien positif
            petugasMedis.obati(pasienPositif);

            // Membuat kasus aktif dari rantai pasien positif yang sembuh berkurang 1
            for (Carrier carrier : pasienPositif.getRantaiPenular()) {
                if (!carrier.equals(pasienPositif)) {
                    carrier.setAktifKasusDisebabkan(carrier.getAktifKasusDisebabkan() - 1);
                }
            }

            // Mengosongkan rantai penular karena si objek sudah sembuh
            pasienPositif.getRantaiPenular().clear();

            // Merefresh hasil
            hasil = "";
        }
        // Perintah untuk mendapatkan total pasien yang sudah disembuhkan oleh suatu objek petugas medis
        else if (perintah.equalsIgnoreCase("total_sembuh_petugas_medis")) {
            Carrier carrier = world.getCarrier(data_query[1]);
            PetugasMedis petugasMedis = (PetugasMedis) carrier;
            hasil = String.format("%s menyembuhkan %d manusia", petugasMedis.toString(), petugasMedis.getJumlahDisembuhkan());
        }
        // Perintah untuk mendapatkan total pasien yang sudah sembuh secara keseluruhan
        else if (perintah.equalsIgnoreCase("total_sembuh_manusia")) {
            hasil = String.format("Total sembuh dari kasus COVID yang menimpa manusia ada sebanyak: %d kasus", Manusia.getJumlahSembuh());
        }
        // Perintah untuk membersihkan objek benda oleh objek cleaning service
        else if (perintah.equalsIgnoreCase("bersihkan")) {
            // Melakukan downcasting menjadi objek kelas cleaning service (dipastikan cleaning service)
            Carrier carrier1 = world.getCarrier(data_query[1]);
            CleaningService cleaningService = (CleaningService) carrier1;

            // Melakukan downcasting menjadi objek kelas benda (dipastikan benda positif corona)
            Carrier carrier2 = world.getCarrier(data_query[2]);
            Benda benda = (Benda) carrier2;

            // Membersihkan benda
            cleaningService.bersihkan(benda);

            // Membuat kasus aktif dari rantai benda positif yang sudah negatif berkurang 1
            for (Carrier carrier : benda.getRantaiPenular()) {
                if (!carrier.equals(benda)) {
                    carrier.setAktifKasusDisebabkan(carrier.getAktifKasusDisebabkan() - 1);
                }
            }

            // Mengosongkan rantai penularan akibat benda sudah negatif
            benda.getRantaiPenular().clear();

            // Merefresh hasil
            hasil = "";
        }
        // Perintah untuk mendapatkan total benda yang sudah dibersihkan cleaning servie
        else if (perintah.equalsIgnoreCase("total_bersih_cleaning_service")) {
            CleaningService cleaningService = (CleaningService) world.getCarrier(data_query[1]);
            hasil = String.format("%s membersihkan %d benda", cleaningService.toString(), cleaningService.getJumlahDibersihkan());
        } else {
            hasil = "exit";
            // Menutup printwriter dan buffered reader yang telah digunakan
            if (printWriter != null)
                printWriter.close();
            else if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        // Menset hasilQuery
        SimulatorGUI.setHasilQuery(hasil);
        if (tipeOutput.equalsIgnoreCase("text") && !hasil.equals("")) {
            // Menulis hasilquery di file
            printWriter.println(hasil);
            printWriter.flush();
            // Merefresh hasilQuery
            if (!hasil.equals("exit"))
                SimulatorGUI.setHasilQuery("");
        }
    }
}

