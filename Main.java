import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] arr = { "meow", "woof", "quack", "oink" };

        System.out.println("Task 1\n" + Arrays.toString(arr));
        swapElements(arr, 0, 3);
        System.out.println(Arrays.toString(arr));

        System.out.println("Task 2");
        Orange orange = new Orange();
        Apple apple = new Apple();
        Box<Orange> orangeBox1 = new Box();
        Box<Orange> orangeBox2 = new Box();
        Box<Apple> appleBox = new Box();

        for (int i = 0; i < 6; i++) {
            orangeBox1.add(new Orange());
        }
        for (int i = 0; i < 8; i++) {
            orangeBox2.add(new Orange());
        }
        for (int i = 0; i < 12; i++) {
            appleBox.add(new Apple());
        }

        orangeBox1.info();
        orangeBox2.info();
        appleBox.info();

        Float orange1Weigth = orangeBox1.getWeight();
        Float orange2Weigth = orangeBox2.getWeight();
        Float appleWeigth = appleBox.getWeight();
        System.out.println("Orange box number 1 weights: " + orange1Weigth);
        System.out.println("Orange box number 2 weights: " + orange2Weigth);
        System.out.println("Apple box weighs: " + appleWeigth);

        System.out.println("Compare weights of orange box 1 and apple box: " + orangeBox1.compare(appleBox));
        System.out.println("Compare weights of orange box 2 and apple box: " + orangeBox2.compare(appleBox));

        orangeBox1.moveAt(orangeBox2);

        orangeBox1.info();
        orangeBox2.info();
        appleBox.info();
    }

    private static <T> void swapElements(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static <E> List<E> convertToList(E[] array) {
        return Arrays.asList(array);
    }
}