import ai.Fuzzy;
import data.Berat;
import data.Tinggi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    BufferedReader keyboard;

    public static void main(String args[])throws IOException, Exception {
        double inputTinggi, inputBerat;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Masukkan tinggi badan anda \t: ");
        inputTinggi = Double.parseDouble(keyboard.readLine());
        System.out.print("Masukkan berat badan anda \t: ");
        inputBerat  = Double.parseDouble(keyboard.readLine());

        Fuzzy fuzzy = new Fuzzy(inputTinggi, inputBerat);
        fuzzy.analyze();
    }
}
