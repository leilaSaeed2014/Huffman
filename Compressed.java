/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huffman_coding;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author Leila Sa3eed
 */
public class Compressed {
    
    public void comp (String s){
         
        ArrayList <Integer> rep = new ArrayList<>();
        ArrayList <Node> work = new ArrayList<>();
        ArrayList <String> code = new ArrayList <> ();
        
        for (int i = 0 ; i < 128 ; i++){
            rep.add (0);
            code.add("");
        }
        for (int i = 0 ; i < s.length() ; i++){
            int x = s.charAt(i);
            rep.set (x ,rep.get(x)+1);
        }      
        for (int i = 0 ; i < 128 ; i++){
            if (rep.get(i) != 0){
                work.add (new Node (String.valueOf((char)i) ,rep.get(i)));
            }
        }
        
        while(work.size() > 1){
            Pair min = twoMini(work);
            Node n = new Node(
                work.get(min.firstIndex()).name+work.get(min.secondIndex()).name,
                work.get(min.firstIndex()).num+work.get(min.secondIndex()).num);
            String mins = work.get(min.firstIndex()).name;
            for(int i = 0 ; i < mins.length();i++){
                int y = mins.charAt(i);
                code.set(y, code.get(y) + "0");
            }
            mins = work.get(min.secondIndex()).name;
            for(int i = 0 ; i < mins.length();i++){
                int y = mins.charAt(i);
                code.set(y, code.get(y) + "1");
            }
            Node temp1 = work.get(min.firstIndex());
            Node temp2 = work.get(min.secondIndex());
            work.remove(temp1);
            work.remove(temp2);
            work.add(n);
        }
        IO writer = new IO();
        String dictionary = "";
        
        for(int i = 0 ; i < code.size(); i++){
            dictionary += i + " " + reverse(code.get(i)) + "\r\n";
        }
        writer.writeOutput("Dictionary.txt", dictionary);
        
        String c = "";
        for (int i = 0 ; i < s.length() ; i++){
            int n = s.charAt(i);
            c += reverse(code.get(n));
        }
        writer.writeOutput("code.txt", c);
    } 
    private String reverse(String t){
        String tmp = "";
        for(int j = t.length()-1 ; j >= 0; j--){
            tmp += t.charAt(j);
        }
        return tmp;
    }
    private Pair twoMini (ArrayList<Node> work){
        int indexMini1 , indexMini2 ;
        if(work.get(0).num < work.get(1).num){
            indexMini1 = 0;
            indexMini2 = 1;
        }
        else{
            indexMini1 = 1;
            indexMini2 = 0;
        }
        for (int i = 2 ; i < work.size(); i++){
            int mini1 = work.get(indexMini1).num;
            int mini2 = work.get(indexMini2).num;
            if (work.get(i).num < mini1){
                indexMini2 = indexMini1;
                indexMini1 = i;
            }
            else if (work.get(i).num < mini2){
                indexMini2 = i;
            }
        }
        return new Pair (indexMini1 , indexMini2);
    }
}

class Pair {
    private int index1 ; 
    private int index2 ;
    Pair (int n1 , int n2){
        index1 = n1;
        index2 = n2;
    }
    public int firstIndex (){
        return index1;
    }
    public int secondIndex (){
        return index2;
    }    
}
class Node {
    public String name;
    public int num;
    public Node (String s , int n){
        name = s;
        num = n;
    }
}