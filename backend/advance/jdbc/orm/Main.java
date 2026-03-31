public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        int[] numbers = {1, 2, 3, 4, 5};
        printNumbers(numbers);
        String[] names = {"John", "Jane", "Jim", "Jill"};
        printNames(names);
    }

    public static void printNumbers(int[] numbers) {
        //print the numbers
        for (int number : numbers) {
            System.out.println(number);
        }
    }

    public static void printNames(String[] names) {
        //print the names
        for (String name : names) {
            System.out.println(name);
        }
    }

}