package com.myRedis.commands;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;

class Node{
    int val;
    Node left;
    Node right;
    public Node(int val){
        this.val = val;
    }
}
public class Test {
    public static void main(String[] args) {

    }
    public ArrayList<Integer> preOrder(Node root){
        if (root == null){
            return new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(root.val);
        ArrayList<Integer> left = preOrder(root.left);
        ArrayList<Integer> right = preOrder(root.right);
        list.addAll(left);
        list.addAll(right);
        return list;
    }
    public int tree(Node root){
        if (root == null){
            return 0;
        }
        int left = tree(root.left);
        int right = tree(root.right);
        return left+right+1;
    }
    public int kLevel(Node root,int k){
        if (root == null){
            return 0;
        }
        if (k == 1){
            return 1;
        }
        int left = kLevel(root.left,k-1);
        int right = kLevel(root.right, k-1);
        return left+right;
    }
    public static void levelOrder(Node root){
        if (root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        //wanquan erchashu ?
        queue.offer(root);
        while (!queue.isEmpty()){
            Node tmp = queue.poll();
            if (tmp.left!=null){
                queue.offer(tmp.left);
            }
            if (tmp.right!=null){
                queue.offer(tmp.right);
            }
        }
    }
    //zhongxu houxu goujain erchahsu
    Node buildTree(List<Integer> in,List<Integer> last){
        if (last == null){
            return null;
        }
        return null;
    }
    //减治算法

}
