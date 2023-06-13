import java.util.ArrayList;
import java.util.List;

public class SequenceGenerator {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide at least 2 integers in the sequence.");
            return;
        }

        List<Integer> sequence = new ArrayList<>();
        for (String arg : args) {
            try {
                sequence.add(Integer.parseInt(arg));
            } catch (NumberFormatException e) {
                System.out.println("Please provide only integers in the sequence.");
                return;
            }
        }

        if (isArithmeticSequence(sequence)) {
            System.out.println("The sequence follows an arithmetic pattern.");
            generateArithmeticSequence(sequence);
        } else if (isGeometricSequence(sequence)) {
            System.out.println("The sequence follows a geometric pattern.");
            generateGeometricSequence(sequence);
        } else if (isCustomSequence(sequence)) {
            System.out.println("The sequence follows a custom pattern.");
            generateCustomSequence(sequence);
        } else if (isSquareRootSequence(sequence)) {
            System.out.println("The sequence follows a square root pattern.");
            generateSquareRootSequence(sequence);
        } else {
            System.out.println("The sequence does not follow any specified pattern.");
        }
    }

    private static boolean isArithmeticSequence(List<Integer> sequence) {
        int difference = sequence.get(1) - sequence.get(0);

        for (int i = 2; i < sequence.size(); i++) {
            if ((sequence.get(i) - sequence.get(i - 1)) != difference) {
                return false;
            }
        }

        return true;
    }

    private static void generateArithmeticSequence(List<Integer> sequence) {
        int difference = sequence.get(1) - sequence.get(0);
        int lastElement = sequence.get(sequence.size() - 1);

        for (int i = 1; i <= 10; i++) {
            System.out.print((lastElement + i * difference) + " ");
        }
    }

    private static boolean isGeometricSequence(List<Integer> sequence) {
        double ratio = (double) sequence.get(1) / (double) sequence.get(0);

        for (int i = 2; i < sequence.size(); i++) {
            if ((double) sequence.get(i) / (double) sequence.get(i - 1) != ratio) {
                return false;
            }
        }

        return true;
    }

    private static void generateGeometricSequence(List<Integer> sequence) {
        int ratio = sequence.get(1) / sequence.get(0);
        int lastElement = sequence.get(sequence.size() - 1);

        for (int i = 1; i <= 10; i++) {
            System.out.print((int) (lastElement * Math.pow(ratio, i)) + " ");
        }
    }

    private static boolean isSquareRootSequence(List<Integer> sequence) {
        for (int term : sequence) {
            int squareRoot = (int) Math.sqrt(term);
            if (squareRoot * squareRoot != term) {
                return false;
            }
        }

        return true;
    }

    private static void generateSquareRootSequence(List<Integer> sequence) {
        int lastTerm = sequence.get(sequence.size() - 1);
        for (int i = 1; i <= 10; i++) {
            int nextTerm = (int) Math.pow((int) Math.sqrt(lastTerm) + 1, 2);
            System.out.print(nextTerm + " ");
            lastTerm = nextTerm;
        }
    }

    private static boolean isCustomSequence(List<Integer> sequence) {
        for (int i = 2; i < sequence.size(); i++) {
            int currentNumber = sequence.get(i);
            int previousNumber = sequence.get(i - 1);

            if (currentNumber != (2 * previousNumber + 6)) {
                return false;
            }
        }

        return true;
    }

    private static void generateCustomSequence(List<Integer> sequence) {
        int lastNumber = sequence.get(sequence.size() - 1);
        for (int i = 0; i < 10; i++) {
            lastNumber = lastNumber * 2 + 6;
            System.out.print(lastNumber + " ");
        }
    }
}