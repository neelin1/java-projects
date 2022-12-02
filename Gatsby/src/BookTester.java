import java.io.IOException;
import java.util.ArrayList;

public class BookTester {
    public static void main(String[] args) throws IOException
    {
        String filePath="./thegreatgatsby.txt";
        //filePath = System.in("Enter the path to a file");
        Continuation newBook = new Continuation(6, filePath, 2000);
    }
}