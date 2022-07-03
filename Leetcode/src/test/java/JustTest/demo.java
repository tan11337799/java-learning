package JustTest;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Project: Leetcode
 * @Package: test
 * @Date: 2022/1/12 10:35
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */
public class demo {

    // public static String judge(ArrayList<String> input){
    //     for (int i = 0; i < input.size(); i++) {
    //         String oneString = input.get(i);
    //         for(int j=0;j<oneString.length();j++){
    //             if(oneString.for)
    //         }
    //     }
    //
    //
    //
    // }

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int[] nums = new int[a];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = scanner.nextInt();
            }
        }
    }

}
