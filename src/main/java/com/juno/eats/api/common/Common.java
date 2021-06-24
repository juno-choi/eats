package com.juno.eats.api.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.Stack;

public class Common {
    public static Stack<String> keyCheck(String str, String[] keys){
        JsonObject json = new Gson().fromJson(str, JsonObject.class);
        Stack<String> stack = new Stack<>();
        stack.addAll(Arrays.asList(keys));

        int stackSize = stack.size();
        for(int i=0; i<stackSize; i++){
            if(json.keySet().contains(stack.peek())){
                stack.pop();
            }
        }
        return stack;
    }
}
