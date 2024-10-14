package tr.com.huseyinaydin.di;

public class Test {

    public static void main(String[] args) {

        A a=new A();
        B b=new B();

        a.setB(b);
        b.setA(a);

    }
}
