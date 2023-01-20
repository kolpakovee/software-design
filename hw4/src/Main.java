import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "/Users/macbookair/Desktop/test.txt";
        FileIterator fileIterator = new FileIterator(path);
        while (fileIterator.hasNext()){
            System.out.println(fileIterator.next());
        }
    }
}