
public class Main {
    public static void main(String[] args) {
        Date dt = new Date.Builder(22).month(Month.MAY).build();
        dt.out();
    }
}