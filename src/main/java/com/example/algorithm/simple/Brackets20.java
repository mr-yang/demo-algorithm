package com.example.algorithm.simple;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author tianxiaoyang
 * @date 2020-12-16 09:56
 * @describe 有效的括号，https://leetcode-cn.com/problems/valid-parentheses
 */
public class Brackets20 {

    //弄一个map记录反向成对的括号，一定是Character类型，遍历s，判断map是否包含，如果不包含
    //把char放到Stack中，如果不包含，从Stack弹出一个字符判断是否相等，如果不相等返回false
    //遍历完成之后返回Stack是否为空即可
    public boolean isValid(String s) {
        if(s == null || s.length()%2!=0){
            return false;
        }

        HashMap<Character,Character> map = new HashMap<Character,Character>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            //如果包含，从Stack中弹出
            if(map.containsKey(charAt)){
                if(stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                //注意，这里比较记得是从map获取，而不是直接用chaAt比较
                if(pop== null || !pop.equals(map.get(charAt))){
                    return false;
                }
            }else{
                //不包含就放到Stack中
                stack.push(charAt);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Brackets20 brackets = new Brackets20();
//        String s = "()";
//        String s = "([])";
//        String s = "([{}])";
//
//        String s = ")]";
        String s = "())";
//        String s = "())";
        boolean valid = brackets.isValid(s);
        System.out.println("valid = " + valid);

    }
}
