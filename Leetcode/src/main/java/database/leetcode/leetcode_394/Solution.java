package database.leetcode.leetcode_394;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Project: Leetcode
 * Package: database.leetcode.leetcode_394
 * Date: 2022/7/8 10:04
 * Author: Wenhao Tan
 * Version: 1.0
 * License: (C)2022,MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 */
class Solution {
    //利用两个栈进行存储，利用栈后进先出的原则，前面的]将匹配后面的[
    public String decodeString(String s) {
        Deque<String> stringStack = new LinkedList<>();  //用于存放每次[前的字符串，最内层的字母不存入栈中
        Deque<Integer> numStack = new LinkedList<>(); //用于存放每次[前的数字
        StringBuilder tmp = new StringBuilder();  //用于存放每次剥离括号时，第一层括号内的字符串
        int num=0;//用于存放每次出现的数字对应的真实值
        for(int i=0;i<s.length();i++){
            char cur = s.charAt(i);
            if(cur>='0' && cur<='9'){
                num = num*10+cur-'0'; //由于是字符类型，针对多位数字需要进行修改
            }
            else if((cur>='a' && cur<='z') || (cur>='A' && cur<='Z')){
                tmp.append(cur);
            }
            else if(cur=='['){//每次检测到[，则将字符串和数字存入到相应的栈中，用于处理嵌套关系；再将用于存储str和num的temp全部清空
                if(num>0){
                    numStack.push(num);
                }
                stringStack.push(tmp.toString());
                tmp = new StringBuilder();
                num = 0;
            }
            else if(cur==']'){//每次检测到]，则将栈的顶部元素进行处理，
                StringBuilder st = new StringBuilder();//表示一次括号剥离后，第二层括号内的字符串，刚开始为空
                String str = stringStack.pop();
                int n = numStack.pop();
                st.append(str);//需要将第一层和第二层括号之间的字符串先添加到st中
                for(int j=0;j<n;j++){
                    st.append(tmp);//根据第一层和第二层之间的数字，重复第一层括号内的字符串
                }
                tmp = st;//剥离当前括号后，新的tmp进行更新
            }
        }
        return tmp.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString("3[a2[c]]"));
    }
}