import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

class FileIterator implements Closeable, Iterator<String> {
    private final BufferedReader bufferedReader;
    private String nextString;

    public FileIterator(String path) {
        try{
            FileReader fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return nextString != null;
    }

    @Override
    public String next() {
        String nextLine = nextString;

        if (nextLine == null){
            throw new NoSuchElementException();
        }

        try {
            nextString = bufferedReader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return nextLine;
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }
}
