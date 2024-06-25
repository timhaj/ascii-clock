import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ASCII_clock {
    static void kazalec(double r, double fi, double debelina, int t) {
        double praviKot = 90- fi;
        double x = 0, y = 0;
        for(int i = 10;i<=r;i+=10){
            x = i * Math.cos(Math.toRadians(praviKot));
            y = i * Math.sin(Math.toRadians(praviKot));
            StdDraw.text(x, y, Integer.toString(t));
        }
        StdDraw.filledCircle(0,0, 3.5);
    }
    static void ura() {
        StdDraw.setScale(-100,100);
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss:SS");
        StdDraw.enableDoubleBuffering();
        for(;;) {
            StdDraw.clear(Color.white);
            LocalDateTime time = LocalDateTime.now();
            String cas = time.format(f);
            StdDraw.text(-83, 95, cas);
            double fi = 60;
            for (int i=1; i<=12; i++) {
                double x = 90 * Math.cos(Math.toRadians(fi));
                double y = 90 * Math.sin(Math.toRadians(fi));
                StdDraw.text(x,y,Integer.toString(i));
                fi = fi - 30;
            }
            int sec = time.getSecond();
            double ss  = (double) time.getNano() /10000000;
            kazalec(85, sec*6 + ss/100*6, 0.001, sec);
            int min = time.getMinute();
            kazalec(75, 6*min + (double) sec*6/60, 0.005, min);
            int h = time.getHour();
            kazalec(60, ((h%12) * 30) + ((double) 6*5*min/60) + (double) sec*6/3600, 0.01, h);
            for(int kot = 0; kot < 360; kot = kot + 6) {
                double xo = Math.cos(Math.toRadians(kot));
                double yo = Math.sin(Math.toRadians(kot));
                StdDraw.line(80*xo, 80*yo, 83*xo, 83*yo);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }

    public static void main(String[] args) {
        ura();
    }

}
