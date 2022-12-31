
public class Calc {
    //Поля
    int a;
    int b;

    //конструкторы
    Calc(int a, int b) {
        this.a = a;
        this.b = b;
    }

    //методы
    public int toAdd() {
        //сложение
        return this.a + this.b;
    }
    public int toSubtract() {
        //вычитание
        return this.a - this.b;
    }
    public int toMultiply() {
        //умножение
        return this.a * this.b;
    }
    public int toDevide() throws CalcExceptions {
        //деление
        if (this.b==0) {
            throw new CalcExceptions("деление на 0");
        }
        return this.a / this.b;
    }

}
