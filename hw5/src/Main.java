import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "Aleksandr", "Abramov", 0, "russian"));
        users.add(new User(2, "Egor", "Kolpakov", 19, "russian"));
        users.add(new User(2, "Alexander", "Kuchuk", 25, "the best"));

        // Отсортировать  список по свойству age и вывести на экран
        System.out.println("Отсортированный список по свойству age:");
        users.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // Сгруппировать по id
        System.out.println("\nСгрупированный список по id:");
        Map<Long, List<User>> usersById = users.stream()
                .collect(Collectors.groupingBy(User::getId));

        System.out.println(usersById);

        // Посчитать количество разных национальностей
        long countDifferentNationalities = users.stream()
                .map(User::getNationality)
                .distinct()
                .count();
        System.out.println("\nКол-во различных национальностей: " + countDifferentNationalities);

        // Распечатать User-ов старше 10 лет, у которых первый символ firstName не равен M
        System.out.println("\nЮзеры старше 10 лет, имя которых начинается не на М:");
        users.stream()
                .filter(user -> user.getAge() > 10)
                .filter(user -> user.getFirstName().charAt(0) != 'M')
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}