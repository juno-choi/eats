package com.juno.eats.api.common;

import com.google.gson.JsonObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Stack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CommonTest {

    @Test
    void keyCheck성공(){
        //given
        Common common = new Common();
        String[] keys = {"key1","key2","key3"};
        JsonObject json = new JsonObject();
        json.addProperty("key1","value");
        json.addProperty("key2","value");
        json.addProperty("key3","value");
        String str = json.toString();

        //when
        Stack<String> result = common.keyCheck(str, keys);

        //then
        assertThat(result.empty()).isEqualTo(true);
    }

    @Test
    void keyCheck실패(){
        //given
        Common common = new Common();
        String[] keys = {"key0","key2","key3"};
        JsonObject json = new JsonObject();
        json.addProperty("key1","value");
        json.addProperty("key2","value");
        json.addProperty("key3","value");
        String str = json.toString();

        //when
        Stack<String> result = common.keyCheck(str, keys);

        //then
        assertThat(result.empty()).isEqualTo(false);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.pop()).isEqualTo("key0");
    }
}