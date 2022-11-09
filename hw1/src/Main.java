import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        countLetters("abCDA");
    }

    static void fizzBuzz() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    static void fibonacci(int num) {
        int el1 = 1, el2 = 1;
        System.out.println(el1);
        System.out.println(el2);
        for (int i = 3; i <= num; i++) {
            int next_el = el1 + el2;
            System.out.println(next_el);
            el1 = el2;
            el2 = next_el;
        }
    }

    static void factorial(int num) {
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        System.out.println(result);
    }

    static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }
            }
        }
        System.out.print(Arrays.toString(array));
    }

    static void countLetters(String input) {
        String vowels = "AEIOUY";
        String consonants = "BCDFGHJKLMNPQRSTVWXYZ";

        int vowelsCount = 0, consonantsCount = 0;

        for (char ch : input.toCharArray()) {
            String s = String.valueOf(ch);
            if (vowels.contains(s.toUpperCase())) {
                vowelsCount++;
            }
            if (consonants.contains(s.toUpperCase())) {
                consonantsCount++;
            }
        }

        System.out.print("Vowels count: " + vowelsCount + "\n" + "Consonants count: " + consonantsCount);
    }
}