package site.elven.test.other;

import java.util.ArrayList;
import java.util.List;

public class ListNoGeneric {

    public static void main(String[] args) {
        List a1 = new ArrayList();
        a1.add(new Object());
        a1.add(new Integer(1111));
        a1.add(new String("hello 111"));

        System.out.println(a1);

        List<Object> a2 = a1;
        a2.add(new Object());
        a2.add(new Integer(222));
        a2.add(new String("hello 222"));
        System.out.println(a2);

        List<Integer> a3 = a1;
        a3.add(new Integer(333));
//        a3.add(new Object());
//        a3.add(new String("hello 333"));
        System.out.println(a3);

        List<?> a4 = a1;
//        a4.add(new Object());
        a4.remove(0);
        a4.clear();
        System.out.println(a4);
    }
}
