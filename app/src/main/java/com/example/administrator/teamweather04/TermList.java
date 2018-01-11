package com.example.administrator.teamweather04;

/**
 * Created by Administrator on 2016/12/24.
 * 用来保存学期信息的id.以及学期信息。作为adapter的list
 */
///
public class TermList {
    public String id;
    public String term;
    public String No="";
    public String toString(){

        return term+No;//为什么要重写toString()呢？因为适配器在显示数据的时候，如果传入适配器的对象不是字符串的情况下，直接就使用对象.toString()
        //No显示学期
    }
}
