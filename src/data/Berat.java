package data;

public class Berat {
    private double[]    ranges  = {0, 40, 45, 50, 55, 60, 65, 80, 85};
    private String[]    status  = {"Sangat Kurus", "Kurus", "Biasa", "Berat", "Sangat Berat"};

    private double xBerat;
    private String xStatusExact = null;
    private int xStatusIndex;

    public Berat(double xBerat) {
        this.xBerat = xBerat;
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

    public double getxBerat() {
        return xBerat;
    }

    public void setxBerat(double xBerat) {
        this.xBerat = xBerat;
    }

    public boolean isExactValue() {
        boolean isExact = false;

        for (int i = 0; i < ranges.length; i++) {
            if ( (i % 2 == 0) && (this.xBerat > ranges[i]) && (this.xBerat <= ranges[i+1]) ) {
                isExact         = true;
                xStatusExact    = status[i/2];
                xStatusIndex    = i/2;
            }
        }
        return isExact;
    }
}
