import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.lang.Double.parseDouble;


public class DewPoint {
    /**
     * Assignment 3 program 4.13
     * @author Sergei Chekhonin
     * This program computes dew point
     */
    /***
     * Stores humidity value
     */
    public static double humidity;
    /***
     * Stores temperature value
     */
    public static double temperature;
    /***
     * First const function index
     */
    public static final double A= 17.27;
    /***
     * Second const function index
     */
    public static final double B= 237.7;

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter relative humidity (0...1):");
        humidity = parseDouble(input);
        input = JOptionPane.showInputDialog("Enter temperature (in degrees C):");
        temperature = parseDouble(input);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        PrintWriter out = null;
        try {
            out = new PrintWriter("Assignment3_p4.13output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double dewPoint = computeDewPoint(humidity, temperature);
        out.println(dtf.format(now));
        out.printf("Relative humidity is:  %.2f\n", humidity);
        out.printf("Temperature is:  %.2f\n", temperature);
        out.printf("Dew point value is:  %.2f\n", dewPoint);
        out.close();
    }

    /***
     * Computes dew point
     * @param humidity present relative humidity (between 0 and 1)
     * @param temperature temperature (in degrees C)
     * @return "Dew point" temperature (in degrees C)
     */
    public static double computeDewPoint(double humidity, double temperature){
        double func = (A*temperature)/(B+temperature) + Math.log(humidity);
        double dewPoint=(B*func)/(A-func) ;
        return dewPoint;
    }

}
