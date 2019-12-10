package site.elven.test;

import java.lang.String;
import java.util.Arrays;

public class Test1{
    public static void main(String[] args){
        int[] data = new int[]{8,2,4,57,2};

        int len = data.length;
        for(int i=1; i<len; i++){

            int temp = data[i];
            for(int j=i-1; j >=0 && data[j] > temp; j--){
                data[j+1] = data[j];
                data[j+1] = temp;
            }

        }

        System.out.println(Arrays.asList());
    }
}