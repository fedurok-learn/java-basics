import java.util.Scanner;
import java.util.*;

public class Animal {
    public static final double FAVNUMBER = 1.6180;

    private String name;
    private int weight;
    private boolean hasOwner = false;
    private byte age;
    private long uniqueId;
    private char favoriteChar;
    private double speed;
    private float height;


    protected static int numberOfAnimals = 0;

    static Scanner userinput = new Scanner(System.in);

    public Animal() {
        ++numberOfAnimals;

        int sumOfNumbers = 5 + 1;
        System.out.println("5 + 1 = " + sumOfNumbers);

        int divOfNumbers = 5 / 1;
        System.out.println("5 / 1 = " + divOfNumbers);
        int minusOfNumbers = 5 - 1;
        System.out.println("5 + 1 = " + minusOfNumbers);
        int productOfNumbers = 5 * 1;
        System.out.println("5 * 1 = " + productOfNumbers);

        System.out.print("Enter name: \n");
        if (userinput.hasNextLine()) {
            this.setName(userinput.nextLine());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public char getFavoriteChar() {
        return favoriteChar;
    }

    public void setFavoriteChar(char favoriteChar) {
        this.favoriteChar = favoriteChar;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public static void main(String[] args) {
        Animal theAnimal = new Animal();
    }

}
