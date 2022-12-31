
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CalcExceptions {

        //тест конвертера
        /*
        String romanNumber = NumberConverter.arabicToRoman(12);
        int arabicNumber = NumberConverter.romanToArabic("XXI");

        System.out.println(romanNumber);
        System.out.println(arabicNumber);
         */

        Scanner scanner = new Scanner(System.in);
        int number1, number2;
        char operation=' ';
        int result;

        System.out.println("Введите выражение [2+2] или два римских числа от I до X:[V+V] + Enter ");
        String userInput = scanner.nextLine();
        char[] under_char = new char[10];

        for (int i = 0; i < userInput.length(); i++) {
            under_char[i] = userInput.charAt(i);
            if (under_char[i] == '+') {
                operation = '+';
            }
            if (under_char[i] == '-') {
                operation = '-';
            }
            if (under_char[i] == '*') {
                operation = '*';
            }
            if (under_char[i] == '/') {
                operation = '/';
            }
        }
        String under_charString = String.valueOf(under_char);
        String[] op = under_charString.split("[+-/*]");
        if (op.length>2) {
            throw new CalcExceptions("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else if (op.length==1) {
            throw new CalcExceptions("строка не является математической операцией");
        }

        String stable01 = op[0].trim();
        String stable02 = op[1].trim();

        boolean isRoman01 = stable01.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        boolean isRoman02 = stable02.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        //System.out.println(isRoman01);
        //System.out.println(isRoman02);
        if (!(isRoman01 == isRoman02)) {
            throw new CalcExceptions("используются одновременно разные системы счисления");
        }

        if (isRoman01) {
            number1 = NumberConverter.romanToArabic(stable01);
            number2 = NumberConverter.romanToArabic(stable02);
        } else {
            try {
                number1 = Integer.parseInt(stable01);
                number2 = Integer.parseInt(stable02);
            } catch (Exception e) {
                throw new CalcExceptions(e.getMessage());
            }

        }
        if (number1<=0 || number1>10) {
            throw new CalcExceptions("первое число должно быть в диапазоне {1;10}");
        }
        if (number2<=0 || number2>10) {
            throw new CalcExceptions("второе число должно быть в диапазоне {1;10}");
        }

        Calc myCalc = new Calc(number1, number2);

        switch (operation) {
            case '+':
                result = myCalc.toAdd();
                break;
            case '-':
                result = myCalc.toSubtract();
                break;
            case '/':
                result = myCalc.toDevide();
                break;
            case '*':
                result = myCalc.toMultiply();
                break;
            default:
                throw new CalcExceptions("нет такой операции в калькуляторе: "+operation);
        }
        if (isRoman01) {
            if (result<0) {
                throw new CalcExceptions("в римской системе нет отрицательных чисел");
            } else if (result==0) {
                throw new CalcExceptions("в римской системе нет 0");
            }
            System.out.println(NumberConverter.arabicToRoman(result));
        } else {
            System.out.println(result);
        }

    }
}