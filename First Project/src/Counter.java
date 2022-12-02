
public class Counter {
    private int count;

    public void bar() {
        count= count + 1;
        System.out.println(count);
        int count= 2;
        System.out.println(count);
        count= count + 2;
        System.out.println(count);
    }

    public void baz(int count) {
        count= count + 1;
        System.out.println(count);
        int c= count + 2;
        System.out.println(c);
        count= 2 * c;
    }

    void m(int p) {
        try {
            char c= "".charAt(p);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("one");
            int x= 5 / p;
        } catch (ArithmeticException e) {
            System.out.println("two");
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Counter c= new Counter();
        c.bar();
        // c.baz(1);
        // c.m(0);
        int i= 1;
        double d= 1.0;
        char ch= 'c';
        float fl= 1f;
        System.out.println(i + ch * d / fl);
        double f= 0.1 * (int) 11.5;
        System.out.println(10 / (2 / 5));
    }

}
