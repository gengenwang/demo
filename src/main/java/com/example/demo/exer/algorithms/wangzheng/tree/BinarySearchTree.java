package com.example.demo.exer.algorithms.wangzheng.tree;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * <p>二叉查找树</p>
 *
 * @author gengen.wang
 * @version $$ Id: BinarySearchTree.java, V 0.1 2019/6/19 下午3:54 wanggengen Exp $$
 **/
@Data
@NoArgsConstructor
public class BinarySearchTree {

    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.getData()) {
                p = p.getLeft();
            } else if (data > p.getData()) {
                p = p.getRight();
            } else {
                return p;
            }
        }

        return null;
    }

    public void insert(int data) {

        if (tree == null) {
            tree = new Node(data, null, null);
        }

        Node p = tree;
        while (p != null){
            if (data < p.getData()) {
                if (p.getLeft() == null) {
                    p.setLeft(new Node(data, null, null));
                    System.out.println(data + "插入成功");
                    return;
                }
                p = p.getLeft();
            } else if (data > p.getData()) {
                if (p.getRight() == null) {
                    p.setRight(new Node(data, null, null));
                    System.out.println(data + "插入成功");
                    return;
                }
                p = p.getRight();
            } else {
                System.out.println(data + "已存在");
                return;
            }
        }

    }

    public Node initTree() {
        Node rootNode = new Node();
        Node leftNode1 = new Node();
        Node rightNode1 = new Node();
        rootNode.setData(13);
        rootNode.setLeft(leftNode1);
        rootNode.setRight(rightNode1);

        Node leftNode2 = new Node(9, null, null);
        Node rightNode2 = new Node(11, null, null);
        leftNode1.setData(10);
        leftNode1.setLeft(leftNode2);
        leftNode1.setRight(rightNode2);

        Node leftNode3 = new Node(14, null, null);
        Node rightNode3 = new Node(18, null, null);
        rightNode1.setData(16);
        rightNode1.setLeft(leftNode3);
        rightNode1.setRight(rightNode3);

        return rootNode;
    }


    @Test
    public void testFind() {
        Node rootNode = initTree();

        BinarySearchTree searchTree = new BinarySearchTree();
        searchTree.setTree(rootNode);
        Node node = searchTree.find(11);
        System.out.println(node);

    }

    @Test
    public void testInsert() {
        Node rootNode = initTree();

        BinarySearchTree searchTree = new BinarySearchTree();
        searchTree.setTree(rootNode);
        //searchTree.insert(11);
        searchTree.insert(19);
        searchTree.insert(7);
        System.out.println("===");

    }

}


