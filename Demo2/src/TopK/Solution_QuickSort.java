package Demo2.src.TopK;

import java.util.Arrays;

/**
 *  Top - K 问题 - 快速选择解法
 */
public class Solution_QuickSort {


    public static void main(String[] args) {
        int arr[] = new int[]{6,8,7,9,5,4};

        int[] leastNumbers = getLeastNumbers(arr, 3);


    }


    /**
     *  获取数组中最小的K个数
     * @param arr   数组
     * @param k K个数
     * @return
     */
    public static   int[] getLeastNumbers(int[] arr,int k){

        if (k == 0 || arr.length == 0)
            return new int[0];

        return quickSearch(arr,0,arr.length-1,k-1);

    }

    private static int[] quickSearch(int[] nums,int lo,int hi,int k){

        int j = partition(nums,lo,hi);
        if (j==k){
            return Arrays.copyOf(nums,j+1);
        }
        return j>k?quickSearch(nums,lo,j-1,k):quickSearch(nums,j+1,hi,k);
    }

    private static int partition(int[] nums,int lo,int hi){

        int v = nums[lo];
        int i = lo,j = hi + 1;
        while (true){
            while (++i<=hi && nums[i]<v) ;
            while (--j>=lo && nums[j]>v) ;
            if (i>=j)
                break;
            print(nums);
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }

    public static void print(int[] arr){
        for (int num:arr){
            System.out.println(num+" ");
        }
        System.out.println();
    }


}
