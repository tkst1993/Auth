package com.run.auth.common.tree;

import java.util.LinkedList;
import java.util.List;


	/**
 * Created by dello on 2016/7/27.
 * 节点属性和EasyUI的树形结构相配
 */
public class Node implements Comparable<Node> {

    private Long id;
    private Long parentId;
    private String text;
    private String state;   //节点的状态
    private Integer order;
    private NodeAttribute nodeAttribute;
    private List<Node> children=new LinkedList<Node>();

   

    public Node(Long id, Long parentId, String text, String state, Integer order, NodeAttribute nodeAttribute) {
        this.id = id;
        this.parentId = parentId;
        this.text = text;
        this.state = state;
        this.order = order;
        this.nodeAttribute = nodeAttribute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public NodeAttribute getNodeAttribute() {
        return nodeAttribute;
    }

    public void setNodeAttribute(NodeAttribute nodeAttribute) {
        this.nodeAttribute = nodeAttribute;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int compareTo(Node o) {
        if(order>o.order)
            return 1;
        if(order < o.order)
            return -1;
        return 0;
    }
}


