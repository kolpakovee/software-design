package kolpakovee;

public class IntegerArrayList implements IntegerList {
    private int size = 0;
    private Integer[] array = new Integer[1];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (o.getClass() != Integer.class) {
            throw new ClassCastException();
        }
        for (Integer el : array) {
            if (el.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    private boolean canAdd() {
        if (array.length == size) {
            Integer[] newArray = new Integer[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        return true;
    }

    @Override
    public void add(int index, Integer element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        if (canAdd()) {
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = element;
            size++;
        }
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Integer element = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return element;
    }

    @Override
    public int indexOf(Integer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean add(Integer e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (canAdd()) {
            array[size] = e;
            size++;
        }
        return true;
    }

    @Override
    public boolean remove(Integer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                remove(i);
                break;
            }
        }
        return true;
    }
}
