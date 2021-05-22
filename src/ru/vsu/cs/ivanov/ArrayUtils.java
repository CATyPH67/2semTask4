package ru.vsu.cs.ivanov;

import java.util.Comparator;
import java.util.Stack;

public class ArrayUtils {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, Comparable::compareTo);
    }

    private static <T> void sort(T[] arr, Comparator<T> c) {
        int left, right, l, r, n;
        T mid;
        n = arr.length;

        Stack<Integer> st = new Stack<>();
        st.push(n - 1);
        st.push(0);

        do {
            left = st.pop();
            right = st.pop();
            mid = arr[(left + right) / 2];
            l = left;
            r = right;

            while (l < r) {
                while (c.compare(arr[l], mid) < 0) l++;
                while (c.compare(mid, arr[r]) < 0) r--;

                if (l <= r) {
                    swap(arr, l, r);
                    l++;
                    r--;
                }
            }

            if (left < r) {
                st.push(r);
                st.push(left);
            }

            if (l < right) {
                st.push(right);
                st.push(l);
            }
        } while (st.size() != 0);
    }

    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static Integer[] strArrToInt(String str) {
        String[] strArr = str.split("\\s+");
        Integer[] intArr = new Integer[strArr.length];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }
}
