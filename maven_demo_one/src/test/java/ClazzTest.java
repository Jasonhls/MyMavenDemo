import com.cn.clazz.Person;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-02 16:43
 **/
public class ClazzTest {
    @Test
    public void test1() {
        try {
            Class clazz = Person.class;
            Constructor constructor = clazz.getDeclaredConstructor();

            Person p = (Person) BeanUtils.instantiateClass(constructor, new Object[0]);
            Method m = BeanUtils.findMethod(clazz, "eat", String.class);
            m.setAccessible(true);
            Object haha = m.invoke(p, "haha");
            System.out.println(haha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
//        HashSet<Integer> ss = new HashSet();
        TreeSet<Integer> ts = new TreeSet<>();
        for(int i = 0; i < num; i++) {
            int m = in.nextInt();
//            ss.add(m);
            ts.add(m);
        }
        Iterator<Integer> iterator = ts.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
//        int[] arr = ss.stream().mapToInt(Integer::intValue).toArray();
//        int[] arrNew = sort(arr, 0, arr.length - 1);
//        for(int i = 0; i < arrNew.length; i++) {
//            System.out.println(arrNew[i]);
//        }

    }*/

    public static int[] sort(int[] arr, int start, int end) {
        if(start < end) {
            int middle = getMiddle(arr, start, end);
            sort(arr, start, middle - 1);
            sort(arr, middle + 1, end);
        }
        return arr;
    }

    public static int getMiddle(int[] arr, int left, int right) {
        int a = arr[left];
        int leftPoint = left;
        int rightPoint = right;
        while(leftPoint < rightPoint) {
            while(leftPoint < rightPoint && a <= arr[rightPoint]) {
                rightPoint--;
            }
            while(leftPoint < rightPoint && arr[leftPoint] <= a) {
                leftPoint++;
            }
            //如果没有交叉
            if(leftPoint < rightPoint) {
                int temp = arr[rightPoint];
                arr[rightPoint] = arr[leftPoint];
                arr[leftPoint] = temp;
            }
        }
        //如果交叉了
        arr[left] = arr[rightPoint];
        arr[rightPoint] = a;
        return rightPoint;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int i = 0;
        char[] arr = s.toCharArray();

        for(int j = 2; j < arr.length; j++) {

            i = i + get(arr[j]) * (16 ^ (arr.length - 1 - j));
        }
        System.out.println(i);
    }

    public static int get(char s) {
        if(s == 'a' || s == 'A') {
            return 10;
        }else if(s == 'b' || s == 'B') {
            return 11;
        }else if(s == 'c' || s == 'C') {
            return 12;
        }else if(s == 'd' || s == 'D') {
            return 13;
        }else if(s == 'e' || s == 'E') {
            return 14;
        }else if(s == 'f' || s == 'F') {
            return 15;
        }
        return Integer.valueOf(s + "");

    }
}
