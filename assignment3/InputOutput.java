package assignments.assignment3;

// Mengimpor semua module yang ada di java.io
import java.io.*;

public class InputOutput{

    // Menyiapkan atribut yang akan digunakan
    private BufferedReader br;
    private PrintWriter pw;
    private String inputFile;
    private String outputFile;
    private World world;

    /**
     * Constructor dari kelas Input Output.
     * @param inputType -> sebagai parameter untuk mengset BufferedReader.
     * @param inputFile -> untuk mengset atribut inputFile.
     * @param outputType -> sebagai parameter untuk mengset PrintWriter.
     * @param outputFile -> untuk mengset atribut outputFile.
     */
    public InputOutput(String inputType, String inputFile, String outputType, String outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        setBufferedReader(inputType);
        setPrintWriter(outputType);
    }

    /**
     * Setter dari atribut br.
     * @param inputType -> untuk menyesuaikan BufferedReader yang akan dibuat.
     */
    public void setBufferedReader(String inputType){
        // Jika inputan yang diminta berasal dari text
        if (inputType.equalsIgnoreCase("text")) {
            // Mencoba membuka file
            try {
                br = new BufferedReader(new FileReader(inputFile));
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
        // Jika inputan yang diminta berasal dari terminal
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

    }

    /**
     * Setter dari atribut pr.
     * @param outputType -> untuk menyesuakan PrintWriter yang akan dibuat.
     */
    public void setPrintWriter(String outputType){
        // Jika outputan yang diminta berupa text
        if (outputType.equalsIgnoreCase("text")) {
            // Mencoba membuat file
            try {
                pw = new PrintWriter(new FileWriter(outputFile));
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
        // Jika outputan yang diminta dibuat di terminal
        else {
            pw = new PrintWriter(System.out);
        }
    }

    /**
     * Program utama untuk memnerima input dan membentuk output.
     * @throws IOException -> menghandle exception yang dihasilkan jika ternyata tidak terdapat file.
     */
    public void run() throws IOException {
        // Membuat objek world dan membaca perintah dari input
        this.world = new World();
        String query = br.readLine();

        // Program akan terus berjalan sampai user memasukkan exit
        while (!query.equalsIgnoreCase("exit")) {

            // Mengsplit data data pada input yang dimasukkan
            String[] data_query = query.split(" ");
            String perintah = data_query[0];

            // Perintah untuk membentuk objek dan membuat status dari objek tersebut default negatif
            if (perintah.equalsIgnoreCase("add")) {
                String tipe = data_query[1];
                String nama = data_query[2];
                Carrier carrier = world.createObject(tipe, nama);
                carrier.ubahStatus("Negatif");
            }
            // Perintah untuk mempositifkan sebuah objek
            else if (perintah.equalsIgnoreCase("positifkan")) {
                Carrier carrier = world.getCarrier(data_query[1]);
                carrier.ubahStatus("Positif");
            }
            // Perintah untuk melakukan interaksi antar objek
            else if (perintah.equalsIgnoreCase("interaksi")) {
                Carrier carrier1 = world.getCarrier(data_query[1]);
                Carrier carrier2 = world.getCarrier(data_query[2]);
                carrier1.interaksi(carrier2);
            }
            // Perintah untuk mendapatkan rantai penyebaran dari sebuah objek carrier
            else if (perintah.equalsIgnoreCase("rantai")) {
                Carrier carrier = world.getCarrier(data_query[1]);
                String rantai = "Rantai penyebaran " + carrier.toString() + ": ";

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
                            rantai += siCarrier.toString();
                        }
                        // Kasus jika selain yang terakhir
                        else {
                            rantai += siCarrier.toString() + " -> ";
                        }
                    }
                }
                // Menuliskan pesan exception ke dalam string rantai
                catch (BelumTertularException e) {
                    rantai = e.toString();
                }

                // Mengprint atau menulis output
                pw.println(rantai);
                pw.flush();
            }
            // Perintah untuk mendapatkan total kasus yang disebabkan oleh si objek
            else if (perintah.equalsIgnoreCase("total_kasus_dari_objek")) {
                Carrier carrier = world.getCarrier(data_query[1]);
                pw.printf("%s telah menyebarkan virus COVID ke %d objek%n", carrier.toString(), carrier.getTotalKasusDisebabkan());
                pw.flush();
            }
            // Perintah untuk mendapatkan total kasus yang disebabkan oleh si objek dan masih positif
            else if (perintah.equalsIgnoreCase("aktif_kasus_dari_objek")) {
                Carrier carrier = world.getCarrier(data_query[1]);
                pw.printf("%s telah menyebarkan virus COVID dan masih teridentifikasi positif " +
                        "sebanyak %d objek%n", carrier.toString(), carrier.getAktifKasusDisebabkan());
                pw.flush();
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
                        carrier.setAktifKasusDisebabkan(carrier.getAktifKasusDisebabkan()-1);
                    }
                }

                // Mengosongkan rantai penular karena si objek sudah sembuh
                pasienPositif.getRantaiPenular().clear();

            }
            // Perintah untuk mendapatkan total pasien yang sudah disembuhkan oleh suatu objek petugas medis
            else if (perintah.equalsIgnoreCase("total_sembuh_petugas_medis")) {
                Carrier carrier = world.getCarrier(data_query[1]);
                PetugasMedis petugasMedis = (PetugasMedis) carrier;
                pw.printf("%s menyembuhkan %d manusia%n", petugasMedis.toString(), petugasMedis.getJumlahDisembuhkan());
                pw.flush();
            }
            // Perintah untuk mendapatkan total pasien yang sudah sembuh secara keseluruhan
            else if (perintah.equalsIgnoreCase("total_sembuh_manusia")) {
                pw.printf("Total sembuh dari kasus COVID yang menimpa manusia ada sebanyak: %d kasus%n", Manusia.getJumlahSembuh());
                pw.flush();
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
                        carrier.setAktifKasusDisebabkan(carrier.getAktifKasusDisebabkan()-1);
                    }
                }

                // Mengosongkan rantai penularan akibat benda sudah negatif
                benda.getRantaiPenular().clear();
            }
            // Perintah untuk mendapatkan total benda yang sudah dibersihkan cleaning servie
            else if (perintah.equalsIgnoreCase("total_bersih_cleaning_service")) {
                CleaningService cleaningService = (CleaningService) world.getCarrier(data_query[1]);
                pw.printf("%s membersihkan %d benda%n", cleaningService.toString(), cleaningService.getJumlahDibersihkan());
                pw.flush();
            }
            // Membaca baris baru
            query = br.readLine();
        }
        // Menutup kembali objek bufferedreader dan printwriter
        br.close();
        pw.close();
    }

}