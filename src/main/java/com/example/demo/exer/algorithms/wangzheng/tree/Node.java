package com.example.demo.exer.algorithms.wangzheng.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: Node.java, V 0.1 2019/6/19 下午3:51 wanggengen Exp $$
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node {

    /**
     * 数据
     */
    private int data;

    /**
     * 左节点
     */
    private Node left;

    /**
     * 右节点
     */
    private Node right;

}

