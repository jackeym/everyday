package com.one.algorithm.array;

import java.util.Arrays;

/**
 * @author: xiaokang.zhu
 */
public class ArrayKShift {
    
    
    public void arrayKShift(int[] array, int k) {

        /**
         * constrictions
         */

        if (array == null || 0 == array.length) {
            return ;
        }

        k = k % array.length;

        if (0 > k) {
            return;
        }


        /**
         * reverse array , e.g: [1, 2, 3 ,4] to [4,3,2,1]
         */

        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }

        /**
         * first k element reverse
         */
        for (int i = 0; i < k / 2; i++) {
            int tmp = array[i];
            array[i] = array[k - 1 - i];
            array[k - 1 - i] = tmp;
        }

        /**
         * last length - k element reverse
         */

        for (int i = k; i < k + (array.length - k ) / 2; i ++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i + k];
            array[array.length - 1 - i + k] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3 ,4, 5, 6, 7};
        ArrayKShift shift = new ArrayKShift();
        shift.arrayKShift(array, 6);

        Arrays.stream(array).forEach(o -> {
            System.out.println(o);
        });

    }
}
