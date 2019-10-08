/** Class that uses builder pattern to set optional parameters */

public class Date {
    /** Parameters */
    private final int day;    
    private final Month month;    
    private final int year;

    public static class Builder {
        /** Parameters */
        private int day;    
        /** Optional parameters */
        private Month month = Month.JAN;    
        private int year = 2000;

        public Builder(int day) {
            this.day = day;
        }
        public Builder month(Month month) {
            this.month = month;
            return this;
        }
        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Date build() {
            return new Date(this.day, this.month, this.year);
        }
    }

    private Date(int day, Month month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public int getDay() {
        return day;
    }
    public Month getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public void out() {
        System.out.println(this.day + "/" + this.month + "/" + this.year);
    }
}