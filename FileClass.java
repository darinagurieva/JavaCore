import java.io.*;
import java.util.*;

public class FileClass {
    public static void main(String[] args) throws IOException {
        File fileCSV = new File("/Users/Darina/Desktop/JavaCore/HW5/data.csv");

        PrintWriter pw = new PrintWriter(fileCSV);
        StringBuffer csvHeader = new StringBuffer("");
        StringBuffer csvData = new StringBuffer("");
        csvHeader.append("Header1; Header2; Header3\n");
        csvData.append("123; 456; 789\n");
        csvData.append("150; 250; 350\n");
        pw.write(csvHeader.toString());
        pw.write(csvData.toString());
        pw.close();

        Scanner sc = new Scanner(fileCSV);
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            System.out.print(sc.next());
        }

        sc.close();
    }
}
