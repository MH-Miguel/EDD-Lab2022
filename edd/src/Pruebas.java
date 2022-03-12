import java.sql.SQLOutput;
import java.util.List;

public class Pruebas<T>{
    public static void main(String[] args) {

        Lista<Integer> list1 = new Lista<>();
        Lista<Integer> list2 = new Lista<>();
        Lista<String> list3 = new Lista<>();


        for (int i = 0; i < 3; i++) {
            list1.add(i);
        }

        for (int i = 4; i < 7; i++) {
            list2.add(i);
        }

        list3.add("hhhh");

        System.out.println(list1.size());

    }
}
