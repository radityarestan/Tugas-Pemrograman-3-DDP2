package assignments.assignment3;

// Mengimpor module untuk input
import java.util.Scanner;

public class Simulator{
    //Ini adalah class utama
    public static void main(String[] args) {
        // Membuat default input output file, objek scanner dan memberi pesan selamat datang
        Scanner scanner = new Scanner(System.in);
        String inputFile = "NOTHING";
        String outputFile = "NOTHING";
        System.out.println("Selamat datang di program simulasi COVID-19!");

        // Meminta input pathfile text jika user memilih masukan text
        System.out.println("Silahkan masukkan metode input yang anda inginkan (TEXT/TERMINAL):");
        String inputType = scanner.nextLine();
        if(inputType.equalsIgnoreCase("text")){
            inputFile = scanner.nextLine();
        }

        // Meminta input pathfile text jika user memilih keluaran text
        System.out.println("Silahkan masukkan metode output yang anda inginkan (TEXT/TERMINAL):");
        String outputType = scanner.nextLine();
        if(outputType.equalsIgnoreCase("text")){
            outputFile = scanner.nextLine();
        }

        // Membuat objek io dan mencoba menjalankan dengan mengantisipasi exception yang terjadi
        InputOutput io = new InputOutput(inputType, inputFile, outputType, outputFile);
        try {
            io.run();
        }
        catch (Exception e){
            System.out.println(e);
        }

        // Menutup kembali scanner yang telah digunakan
        scanner.close();
    }

}