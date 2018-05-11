package rules;

public class SehatRules {
    public static String[][] rules = {
            // Rows = TInggi, Column = Berat
            {"Sangat Sehat","Sehat",        "Agak Sehat",   "Tidak Sehat",  "Tidak Sehat"},
            {"Sehat",       "Sangat Sehat", "Sehat",        "Agak Sehat",   "Tidak Sehat"},
            {"Agak Sehat",  "Sangat Sehat", "Sangat Sehat", "Agak Sehat",   "Tidak Sehat"},
            {"Tidak Sehat", "Sehat",        "Sangat Sehat", "Sehat",        "Tidak Sehat"},
            {"Tidak Sehat", "Agak Sehat",   "Sangat Sehat", "Sehat",        "Agak Sehat"}
    };
}
