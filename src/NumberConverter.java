import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

enum RomanNumbers {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40),L(50), XC(90), C(100), CD(400),
    D(500), CM(900), M(1000);

    private int arabValue;

    RomanNumbers(int value) {
        this.arabValue = value;
    }

    public int getValue() {
        return arabValue;
    }

    public static List getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumbers e) -> e.arabValue).reversed())
                .collect(Collectors.toList());
    }

}

public class NumberConverter {

    public static int romanToArabic(String number) {

        String romanNumber = number.toUpperCase();
        int result = 0;

        List romanNumbers = RomanNumbers.getReverseSortedValues();

        int i = 0;

        while ((romanNumber.length() > 0) && (i < romanNumbers.size())) {
            RomanNumbers symbol = (RomanNumbers) romanNumbers.get(i);
            if (romanNumber.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumber = romanNumber.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumber.length() > 0) {
            throw new IllegalArgumentException(number + " не может быть преобразовано в римское число");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " может бытьв границах (0,100]");
        }

        List romanNumerals = RomanNumbers.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumbers currentSymbol = (RomanNumbers) romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
