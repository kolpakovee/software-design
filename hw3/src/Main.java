import kolpakovee.IntegerArrayList;


public class Main {
    public static void main(String[] args) {
        IntegerArrayList ial = new IntegerArrayList();
//        System.out.println(ial.isEmpty());
//        System.out.print(ial.size());
        ial.add(10);
        ial.add(10);
        ial.add(15);
        ial.add(0, 25);
        //System.out.print(ial.size());
        System.out.println(ial.contains(15));
        ial.remove(0);
        Integer a = 10;
        ial.remove(a);
        //System.out.print(ial.size());
    }
}