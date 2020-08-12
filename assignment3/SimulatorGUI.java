package assignments.assignment3;

// Mengimpor module yang dibutuhkan
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;

import java.io.*;

public class SimulatorGUI extends Application {
    // Atribut objek kelasi ini
    private Stage window = new Stage();
    private String tipeInput = "";
    private String fileInput = "NOTHING";
    private String tipeOutput = "";
    private String fileOutput = "NOTHING";
    private String scene = "utama";
    private String query = "";
    private InputOutputGUI inputOutputGUI;
    // Atribut kelas
    private static String hasilQuery ="";

    /**
     * Method utama untuk mengatur scene.
     * @param stage -> stage utama.
     */
    @Override
    public void start(Stage stage) {
        // Mengganti scene sesuai kebutuhan dengan default awal sceneutama.
        if (scene.equals("utama")) {
            window.setScene(getSceneUtama());
        } else if (scene.equals("guigui")) {
            window.setScene(getSceneGuiGui());
        } else if (scene.equals("texttext")) {
            window.setScene(getSceneTextText());
        }

        // Membuat judul dari window dan menampilkannya
        window.setTitle("Program Simulasi Covid-19");
        window.show();
    }

    /**
     * Scene halaman utama.
     * @return scene yang dibutuhkan.
     */
    public Scene getSceneUtama() {
        // Membuat gridpane dengan rata tengah dan mengset paddingnya
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Membuat Welcoming text
        Text welcomingText = new Text("Selamat Datang di Program Simulasi COVID-19 !");
        welcomingText.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        gridPane.add(welcomingText, 0, 0, 2, 1);

        // Membuat array pilihan input output
        String[] choice = {"GUI", "TEXT"};

        // Tampilan bagian input

        // Menampilkan text mengenai perintah input
        Text pilihanInput = new Text("Silahkan masukkan metode input yang anda inginkan (GUI/TEXT)");
        pilihanInput.setFont(Font.font("Verdana", 10));

        // Membuat text dan menampilkan choice box pilihan input
        Text perintahInputType = new Text("Masukkan pilihan input    :");
        perintahInputType.setFont(Font.font("Verdana", 10));
        ChoiceBox inputType = new ChoiceBox(FXCollections.observableArrayList(choice[0], choice[1]));
        inputType.setValue("GUI");

        // Menerima masukan file jika user memilih text, "NOTHING" sebagai default nama file
        Text perintahInputFile = new Text("Masukkan pilihan file       :");
        perintahInputFile.setFont(Font.font("Verdana", 10));
        TextField inputFile = new TextField();
        inputFile.setPromptText("Isi Jika Memilih Text");

        // Membuat barisan input type dari node yang diperlukan
        HBox barisInputType = new HBox(5);
        barisInputType.getChildren().addAll(perintahInputType, inputType);
        barisInputType.setAlignment(Pos.BASELINE_LEFT);

        // Membuat barisan input file dari node yang diperlukan
        HBox barisInputFile = new HBox(5);
        barisInputFile.getChildren().addAll(perintahInputFile, inputFile);
        barisInputFile.setAlignment(Pos.BASELINE_LEFT);

        // Membuat kolom dari text perintah input dan barisan yang telah dibuat
        VBox kolomPerintahInput = new VBox(5);
        kolomPerintahInput.getChildren().addAll(pilihanInput, barisInputType, barisInputFile);
        gridPane.add(kolomPerintahInput, 0,1);

        // Tampilan bagian output

        // Menampilkan text mengenai perintah output
        Text pilihanOutput = new Text("Silahkan masukkan metode output yang anda inginkan (GUI/TEXT)");
        pilihanOutput.setFont(Font.font("Verdana", 10));

        // Membuat text dan menampilkan choice box pilihan output
        Text perintahOutputType = new Text("Masukkan piliihan output :");
        perintahOutputType.setFont(Font.font("Verdana", 10));
        ChoiceBox outputType = new ChoiceBox(FXCollections.observableArrayList(choice[0], choice[1]));
        outputType.setValue("GUI");

        // Menerima masukan file jika user memilih text, "NOTHING" sebagai default nama file
        Text perintahOutputFile = new Text("Masukkan pilihan file       :");
        perintahOutputFile.setFont(Font.font("Verdana", 10));
        TextField outputFile = new TextField();
        outputFile.setPromptText("Isi Jika Memilih Text");

        // Membuat barisan output type dari node yang diperlukan
        HBox barisOuputType = new HBox(5);
        barisOuputType.getChildren().addAll(perintahOutputType, outputType);
        barisOuputType.setAlignment(Pos.BASELINE_LEFT);

        // Membuat barisan output file dari node yang diperlukan
        HBox barisOutputFile = new HBox(5);
        barisOutputFile.getChildren().addAll(perintahOutputFile, outputFile);
        barisOutputFile.setAlignment(Pos.BASELINE_LEFT);

        // Membuat kolom dari text perintah input dan barisan yang telah dibuat
        VBox kolomPerintahOutput = new VBox(5);
        kolomPerintahOutput.getChildren().addAll(pilihanOutput, barisOuputType, barisOutputFile);
        gridPane.add(kolomPerintahOutput, 0,2);

        // Submit
        Text perintahSubmit = new Text("Sudah yakin? Tekan ->");
        perintahSubmit.setFont(Font.font("Verdana", 10));
        Button tombolSubmit = new Button("Submit");

        // Fungsi yang akan dijalankan jika tombol submit ditekan
        tombolSubmit.setOnAction(e -> {
            // Mendapatkan masukan dari user
            tipeInput = inputType.getValue().toString();
            fileInput = inputFile.getText();
            tipeOutput = outputType.getValue().toString();
            fileOutput = outputFile.getText();

            // Program yang akan dijalankan nantinya sesuai tipe input dan output yang dimasukkan oleh user
            inputOutputGUI = new InputOutputGUI(tipeInput, fileInput, tipeOutput, fileOutput);
            if (tipeInput.equals("GUI") && tipeOutput.equals("GUI")) {
                scene = "guigui";
            } else if (tipeInput.equals("GUI") && tipeOutput.equals("TEXT")) {
                inputOutputGUI.setPrintwriter();
                scene = "guigui";
            } else if (tipeInput.equals("TEXT") && tipeOutput.equals("GUI")) {
                inputOutputGUI.setBufferedReader();
                scene = "texttext";
            } else if (tipeInput.equals("TEXT") && tipeOutput.equals("TEXT")) {
                inputOutputGUI.setBufferedReader();
                inputOutputGUI.setPrintwriter();
                scene = "texttext";
            }
            // Merestate window yang telah dibuat
            start(window);
        });

        // Membuat baris submit dari node yang diperlukan
        HBox barisSubmit = new HBox(20);
        barisSubmit.getChildren().addAll(perintahSubmit, tombolSubmit);
        barisSubmit.setAlignment(Pos.BASELINE_LEFT);
        gridPane.add(barisSubmit, 0, 3);

        // Setting scene dan mengembalikannya
        Scene scene = new Scene(gridPane, 640, 480);
        return scene;
    }

    /**
     * Scene yang akan dibuat jika inputnya dalam GUI.
     * @return scene yang dibutuhkan
     */
    public Scene getSceneGuiGui() {
        // Membuat stackpane yang dibutuhkan
        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(25,25,25,25));

        // Membuat welcoming text
        Text welcomingText = new Text("Program Simulasi COVID-19 !");
        welcomingText.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));

        // Membuat perintah masukkan query dan menyediakan textfield
        Text perintah = new Text("Masukkan query: ");
        perintah.setFont(Font.font("Verdana", 10));
        TextField queryInput = new TextField();

        // Membuat tombol untuk memasukkan query
        Button submit = new Button("Submit");

        // Membuat baris query dari node yang diperlukan
        HBox barisQuery = new HBox(5);
        barisQuery.getChildren().addAll(perintah, queryInput, submit);
        barisQuery.setAlignment(Pos.BASELINE_LEFT);

        // Menampilkan hasil dari query yang dimasukkan
        Text hasil = new Text(hasilQuery);

        // Membuat kolom query dari node yang diperlukan
        VBox kolomQuery = new VBox(5);
        kolomQuery.getChildren().addAll(welcomingText, barisQuery, hasil);
        stackPane.getChildren().add(kolomQuery);

        // Fungsi yang akan dijalankan jika tombol submit ditekan
        submit.setOnAction(e -> {
            // Mendapatkan query dari textfield yang disediakan
            query = queryInput.getText();

            // Mencoba menjalankan query
            try {
                inputOutputGUI.run(query);
            } catch (Exception exception) {
                System.out.println(exception);
            }

            // Menentukan kelanjutan window, jika exit maka akan ditutup
            if (!hasilQuery.equalsIgnoreCase("exit"))
                start(window);
            else
                window.close();
        });

        // Setting scene dan mengembalikannya
        Scene scene = new Scene(stackPane, 800, 200);
        return scene;
    }

    /**
     * Scene yang akan dibuat jika inputnya berupa text.
     * @return scene yang dibutuhkan.
     */
    public Scene getSceneTextText() {

        // Membuat gridpane
        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(25,25,25,25));

        // Membuat welcoming text
        Text welcomingText = new Text("Program Simulasi COVID-19 !");
        welcomingText.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));

        // Membuat text penjelasan yang sesuai dengan tipeoutput
        Text penjelasan = new Text();
        if (tipeOutput.equalsIgnoreCase("gui")) {
            penjelasan.setText("Berikut hasil query: ");
            penjelasan.setFont(Font.font("Verdana", 10));
        } else {
            penjelasan.setText("Program Sukeses! Hasil dapat dilihat di " + fileOutput);
            penjelasan.setFont(Font.font("Verdana", 10));
        }

        // Membuat kolomquery dari node yang dibutuhkan
        VBox kolomQuery = new VBox(5);
        kolomQuery.getChildren().addAll(welcomingText,penjelasan);

        // Menampilkan text hasil dari query yang dijalankan
        try {
            // Membaca file dari text
            query = inputOutputGUI.getBufferedReader().readLine();
            while (!query.equalsIgnoreCase("exit")) {
                // Mencoba menjalankan program
                try {
                    inputOutputGUI.run(query);
                } catch (Exception e) {
                    System.out.println(e);
                }
                // Jika output yang diminta berupa gui
                if (tipeOutput.equalsIgnoreCase("gui")) {
                    // Menampilkan hasilnya jika querynya menghasilkan output
                    if (!hasilQuery.equals("")) {
                        Text text = new Text(hasilQuery);
                        text.setFont(Font.font("Verdana", 10));
                        kolomQuery.getChildren().add(text);
                    }
                }
                query = inputOutputGUI.getBufferedReader().readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        stackPane.getChildren().add(kolomQuery);

        // Setting scene dan mengembalikannya
        Scene scene = new Scene(stackPane, 800, 400);
        return scene;
    }

    /**
     * Setter hasilquery.
     * @param hasilQueryBaru -> update hasilquery baru.
     */
    public static void setHasilQuery(String hasilQueryBaru) {
        hasilQuery = hasilQueryBaru;
    }

    /**
     * Program utama.
     * @param args -> parameter default.
     */
    public static void main(String[] args) {
        launch();
    }

}

