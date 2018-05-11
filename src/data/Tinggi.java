package data;

public class Tinggi {
    private double[]    ranges  = {0, 115, 120, 140, 145, 160, 165, 180, 185};
    private String[]    status  = {"Sangat Pendek", "Pendek", "Sedang", "Tinggi", "Sangat Tinggi"};

    private double xTinggi;
    private String xStatusExact = null;
    private int xStatusIndex;

    public Tinggi(double xTinggi) {
        this.xTinggi = xTinggi;
    }

    public double[] getRanges() {
        return ranges;
    }

    public String[] getStatus() {
        return status;
    }

    public String getxStatusExact() {
        return xStatusExact;
    }

    public int getxStatusIndex() {
        return xStatusIndex;
    }

    public double getxTinggi() {
        return xTinggi;
    }

    public void setxTinggi(double xTinggi) {
        this.xTinggi = xTinggi;
    }

    public boolean isExactValue() {
        boolean isExact = false;

        for (int i = 0; i < ranges.length; i++) {
            if ( (i % 2 == 0) && (this.xTinggi > ranges[i]) && (this.xTinggi <= ranges[i+1]) ) {
                isExact         = true;
                xStatusExact    = status[i/2];
                xStatusIndex    = i/2;
            }
        }
        return isExact;
    }
}
