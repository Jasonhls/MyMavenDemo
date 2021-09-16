package com.cn.dataStructure.ch02;

public class QuickSort {
    /**
     * left 左边第一个，从0开始
     * right 右边第一个，从arr.length() - 1开始
     * 查找标志数point在该数组正确排序后，所处位置的下标值
     * @param arr
     * @param left
     * @param right
     * @param point
     */
    public static int quickSearch(int[] arr,int left,int right,int point){
        int leftPtr = left - 1;
        int rightPtr = right;//因为把数组最后一个数当作标志数，因此，右边查找的时候，从倒数第二个开始
        while(true){
            while(leftPtr < rightPtr && arr[++leftPtr] < point);//从左边开始找，找到第一个比point大的元素就停下找
            while(leftPtr < rightPtr && arr[--rightPtr] > point);//从右边开始找，找到第一个比point小的元素就停下找
            if(leftPtr >= rightPtr){
                break;
            }else {
                int temp = arr[rightPtr];
                arr[rightPtr] = arr[leftPtr];
                arr[leftPtr] = temp;
            }
        }
        /*leftPtr即是在该数组正确排序后，所处位置的下标值*/
        /*默认最右边为标志数，因此将leftPtr所在元素与最右边位置元素交换*/
        int temp = arr[right];
        arr[right] = arr[leftPtr];
        arr[leftPtr] = temp;
        return leftPtr;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,232,232,43,22,42,15};
        print(arr);
        quickSort(arr,0,arr.length - 1,arr[arr.length - 1]);
        print(arr);
    }

    public static void quickSort(int arr[],int left,int right,int point){
        if(left >= right){
            return;
        }
        int location = quickSearch(arr, left, right, point);
        quickSort(arr,left,location - 1,arr[location - 1]);
        quickSort(arr,location + 1,right,arr[right]);
    }


    public static void print(int arr[]){
        System.out.print("开始打印数组：");
        for (int i = 0 ;i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
}
