package ai;

import data.Berat;
import data.Tinggi;
import rules.SehatRules;

import java.util.HashMap;

public class Fuzzy {
    private double degreeTinggi[]       = new double[2];
    private double degreeBerat[]        = new double[2];
    private String degreeStatusTinggi[] = new String[2];
    private String degreeStatusBerat[]  = new String[2];

    private double fValue[]             = new double[4];
    private String fStatus[]            = new String[4];

    private double maxValue             = 0;
    private String maxStatus            = null;

    private HashMap<String, Double> sugenoSet   = new HashMap<String, Double>();
    private double cripsIndex = 0;

    Berat   berat   = null;
    Tinggi  tinggi  = null;

    public Fuzzy(double inpTinggi, double inpBerat) {
        this.tinggi  = new Tinggi(inpTinggi);
        this.berat   = new Berat(inpBerat);
    }

    public void analyze() {
        if (this.tinggi.isExactValue() && this.berat.isExactValue()) {
            this.findExactEvaluation();
        } else {
            this.setSugenoValue();
            this.findDOM();
            this.deFuzzyficate();
            this.findMaxMethod();
            this.findSugenoMethod();
            this.showFuzzyValue();
        }
    }

    private void setSugenoValue() {
        sugenoSet.put("Tidak Sehat", 0.2);
        sugenoSet.put("Agak Sehat", 0.4);
        sugenoSet.put("Sehat", 0.6);
        sugenoSet.put("Sangat Sehat", 0.8);
    }

    private void findExactEvaluation() {
        for (int i = 0; i < SehatRules.rules.length; i++) {
            for (int j = 0; j < SehatRules.rules.length; j++) {
                this.checkExactValue(i, j);
            }
        }
    }

    private void checkExactValue(int i, int j) {
        if ( (this.tinggi.getxStatusIndex()) == i && (this.berat.getxStatusIndex() == j) ) {
            System.out.println("Status : " + SehatRules.rules[i][j]);
        }
    }

    private void findDOM() {
        this.DomTinggi();
        this.DomBerat();
    }

    private void DomTinggi() {
        for (int i = 0; i < this.tinggi.getRanges().length; i++) {
            if (this.tinggi.getxTinggi() >= this.tinggi.getRanges()[i]) {
                this.degreeStatusTinggi[0]  = this.tinggi.getStatus()[i/2];
                this.degreeStatusTinggi[1]  = this.tinggi.getStatus()[i/2 + 1];

                this.degreeTinggi[0]        = (this.tinggi.getRanges()[i+1] - this.tinggi.getxTinggi())
                        / (this.tinggi.getRanges()[i+1] - this.tinggi.getRanges()[i]);
                this.degreeTinggi[1]        = (this.tinggi.getxTinggi() - this.tinggi.getRanges()[i])
                        / (this.tinggi.getRanges()[i+1] - this.tinggi.getRanges()[i]);
            }
        }
    }

    private void DomBerat() {
        for (int i = 0; i < this.berat.getRanges().length; i++) {
            if (this.berat.getxBerat() >= this.berat.getRanges()[i]) {
                this.degreeStatusBerat[0]  = this.berat.getStatus()[i/2];
                this.degreeStatusBerat[1]  = this.berat.getStatus()[i/2 + 1];

                this.degreeBerat[0]        = (this.berat.getRanges()[i+1] - this.berat.getxBerat())
                        / (this.berat.getRanges()[i+1] - this.berat.getRanges()[i]);
                this.degreeBerat[1]        = (this.berat.getxBerat() - this.berat.getRanges()[i])
                        / (this.berat.getRanges()[i+1] - this.berat.getRanges()[i]);
            }
        }
    }

    private void deFuzzyficate() {
        int x = 0;
        for (int i = 0; i < degreeTinggi.length; i++) {
            for (int j = 0; j < degreeBerat.length; j++) {
                if (degreeTinggi[i] < degreeBerat[j]){
                    fValue[x]   = degreeTinggi[i];
                } else {
                    fValue[x]   = degreeBerat[j ];
                }
                fStatus[x]  = SehatRules.rules
                        [this.getIndexArrayOfString(this.tinggi.getStatus(), degreeStatusTinggi[i])]
                        [this.getIndexArrayOfString(this.berat.getStatus(), degreeStatusBerat[j])];
                x++;
            }
        }
    }

    private void findMaxMethod() {
        System.out.println();
        for (int i = 0; i < fValue.length; i++) {
            if (fValue[i] > maxValue) {
                maxValue    = fValue[i];
                maxStatus   = fStatus[i];
            }
            System.out.println(fStatus[i] + ":" + fValue[i]);
        }
    }

    public void findSugenoMethod() {
        double f = 0;
        for (int i = 0; i < fValue.length; i++) {
            cripsIndex += fValue[i] * sugenoSet.get(fStatus[i]);
            System.out.println("item : " + fValue[i] + "| " + sugenoSet.get(fStatus[i]));
            f += fValue[i];
        }
        cripsIndex /= f;
        System.out.println("\nCrisp Index : " + cripsIndex);
    }

    private void showFuzzyValue() {
        System.out.println("\nResult =>");
        System.out.println("Status : " + maxStatus);
        System.out.println("Value  : " + maxValue);
    }

    public int getIndexArrayOfString(String[] strings, String item) {
        for (int i = 0; i < strings.length; i++) {
            if (item.equals(strings[i])) return i;
        }
        return -1;
    }

}
