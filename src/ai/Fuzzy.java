package ai;

import data.Berat;
import data.Tinggi;
import rules.SehatRules;

public class Fuzzy {
    private double degreeTinggi[]       = new double[2];
    private double degreeBerat[]        = new double[2];
    private String degreeStatusTinggi[] = new String[2];
    private String degreeStatusBerat[]  = new String[2];

    private double fValue[]             = new double[4];
    private String fStatus[]            = new String[4];

    private double maxValue             = 0;
    private String maxStatus            = null;

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
            this.findDOM();
            this.deFuzzyficate();
            this.findMaxMethod();
            this.showFuzzyValue();
        }
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
                    fStatus[x]  = degreeStatusTinggi[i];
                } else {
                    fValue[x]   = degreeBerat[j];
                    fStatus[x]  = degreeStatusBerat[j];
                }
                x++;
            }
        }
    }

    private void findMaxMethod() {
        for (int i = 0; i < fValue.length; i++) {
            if (fValue[i] > maxValue) {
                maxValue    = fValue[i];
                maxStatus   = fStatus[i];
            }
        }
    }

    private void showFuzzyValue() {
        System.out.println("Status : " + maxStatus);
        System.out.println("Value  : " + maxValue);
    }

}
