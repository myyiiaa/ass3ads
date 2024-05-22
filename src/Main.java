import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Тестирование MyHashTable
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(10);

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            table.put(new MyTestingClass(id), new Student("Student" + id));
        }

        table.printBucketSizes();

        // Тестирование MyBinarySearchTree
        MyBinarySearchTree<Integer, String> tree = new MyBinarySearchTree<>();

        tree.insert(5, "five");
        tree.insert(3, "three");
        tree.insert(4, "four");
        tree.insert(2, "two");
        tree.insert(6, "six");
        tree.insert(7, "seven");

        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}