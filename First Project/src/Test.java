
//import java.util.*; - imports all the packages in util
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
        System.out.println(args);

        String s= "string"; // don't need to import anything because part of the base java package
        // can either define the new package like this
        java.util.Date d= new java.util.Date();
        // or have an import statement above
        Date da= new Date();

        // chars
        // correspond to integers
        char fred= 'a';
        System.out.println((int) fred); // should print 97, because that is the corresponding
                                        // integer
        // can do arithmetic operations
        System.out.println((char) ('b' + 1));
        // can cast back and forth to go from representation to another
        // cannot do operations on a char
        // check if it is in the alphabet
        System.out.println(Character.isAlphabetic('r'));
        System.out.println(Character.isAlphabetic('0'));
        System.out.println(Character.toLowerCase('K'));

        int x= 9;
        System.out.println("string:" + s + ", " + "fred: " + fred + ", " + "x: " + x);
        String str= "CS 2110";
        System.out.println(str.length());
        System.out.println(str.substring(2));
        System.out.println(str.substring(2, 5));
        System.out.println(str.charAt(0));
    }

}
