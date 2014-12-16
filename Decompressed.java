/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huffman_coding;

import java.util.ArrayList;

/**
 *
 * @author Leila Sa3eed
 */
public class Decompressed {
    public void decomp (){
        ArrayList <String> table = new ArrayList <> ();
        String d = new IO().getInput("Dictionary.txt");
        String[] dictionary = d.split("\r\n");//[..][97 01]
        String code = new IO().getInput("code.txt");
        String res = "";
        String temp = "";
        for(int i = 0 ; i < dictionary.length; i++){
            String[] tmp = dictionary[i].split(" ");
            if(tmp.length == 2){
                table.add(tmp[1]);
            }else{
                table.add("");
            }
        }
        for (int i = 0 ; i < code.length () ; i++){
            
            temp = String.valueOf(code.charAt(i));
            for (int j = i ; j < code.length () ;  ){
                if (searchAtArrayList (temp,table) == -1){
                    temp += String.valueOf(code.charAt(++j));
                }
                else{
                    i = j;
                    break;
                }
            }
            int idx = searchAtArrayList(temp,table);
            res += String.valueOf((char) idx);
        }
        new IO().writeOutput("output.txt", res);
       
    }
    private int searchAtArrayList (String s,ArrayList<String> table){
        for (int i = 0 ; i < table.size() ; i++){
            if (s.compareTo(table.get(i)) == 0)
                return i;
        }
        return -1;
    }
}

