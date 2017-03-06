package client;

import java.util.LinkedHashMap;

import model.Automobile;

public class test {

    public static void main(String[] args) {
        
        CarModelOptionsIO model=new CarModelOptionsIO();
        LinkedHashMap<String,Automobile> list=model.connectServlet();
        System.out.println(list.keySet().toArray()[0]);
        System.out.println(list.keySet().toArray()[1]);
        System.out.println(list.keySet().toArray()[2]);
    }

}
