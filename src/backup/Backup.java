/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import Main.Data;
import static Main.Display.data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author ezizu
 */
public class Backup {

    private BufferedReader read;
    private BufferedWriter write;
    private final String url = "src\\Main\\Database.txt";
    private File file;

    private void Export() throws IOException {
        Writer writer = null;
        try {
            file = new File(url);
            writer = new BufferedWriter(new FileWriter(file));

            String judl = "mapcol.getText() + \" | \" + jamcol.getText() + \" | \" + gedcol.getText() + \" | \" + ruacol.getText() + \" | \" + doscol.getText() + \n";
            writer.write(judl);

            for (Data save : data) {
                String text = save.getMapel() + " | " + save.getWaktu() + " | " + save.getGdung() + " | " + save.getRuang() + " | " + save.getDosen() + "\n";

                writer.write(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }
    }

    private void Import() throws IOException {

        try {
            file = new File(url);
            read = new BufferedReader(new FileReader(file));

            Object[] ambil = read.lines().toArray();

            for (int i = 0; i < ambil.length; i++) {
                String baris = ambil[i].toString();
                String[] hasil = baris.split(" | ");

                data.add(new Data(hasil[i], hasil[i + 1], hasil[i + 2], hasil[i + 3], hasil[i + 4]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            read.close();
        }
    }

    public static void main(String args[]) {
        // TODO code application logic here
    }
}
