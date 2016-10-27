package com.nb.daipengfei.list;

/*********************************
 *                               *
 Created by daipengfei on 16/10/10.
 *                               *
 ********************************/

public class MyList {
    private Long id;
    private String name;
    private Node node;

    private static class Node{
        private Node pre;
        private Node next;
    }

    public static void main(String[] args) {
        MyList myList = new MyList();
        Node head = myList.node;

    }


}
