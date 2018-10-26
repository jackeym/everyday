package com.one.algorithm.basic;

/**
 * 红黑树
 * 1、每个节点不是红色就是黑色的；
 * 2、根节点总是黑色的；
 * 3、所有的叶节点都是是黑色的（红黑树的叶子节点都是空节点（NIL或者NULL））；
 * 4、如果节点是红色的，则它的子节点必须是黑色的（反之不一定）；
 * 5、从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）。
 * @author: one
 */
public class RBTree<T extends Comparable<T>> {
    private final RBTreeNode<T> root;
    //node number
    private java.util.concurrent.atomic.AtomicLong size =
            new java.util.concurrent.atomic.AtomicLong(0);
    //in overwrite mode,all node's value can not  has same    value
    //in non-overwrite mode,node can have same value, suggest don't use non-overwrite mode.
    private volatile boolean overrideMode=true;
    
    public RBTree(){
        this.root = new RBTreeNode<T>();
    }

    public RBTree(boolean overrideMode){
        this();
        this.overrideMode=overrideMode;
    }


    public boolean isOverrideMode() {
        return overrideMode;
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }

    /**
     * number of tree number
     * @return
     */
    public long getSize() {
        return size.get();
    }
    /**
     * get the root node
     * @return
     */
    private RBTreeNode<T> getRoot(){
        return root.getLeft();
    }

    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y(right)的左子节点赋给x(node)的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将y(right)的左子节点设为x(node)，将x的父节点设为y
     * 3. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     */
    private void rotateLeft(RBTreeNode<T> node){
        RBTreeNode<T> right = node.getRight();
        if(right==null){
            throw new java.lang.IllegalStateException("right node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setRight(right.getLeft());
        setParent(right.getLeft(),node);

        right.setLeft(node);
        setParent(node,right);

        if(parent==null){//node pointer to root
            //right  raise to root node
            root.setLeft(right);
            setParent(right,null);
        }else{
            if(parent.getLeft()==node){
                parent.setLeft(right);
            }else{
                parent.setRight(right);
            }
            //right.setParent(parent);
            setParent(right,parent);
        }
    }

    /*
     * 右旋示意图：对节点x进行右旋
     *        p                   p
     *       /                   /
     *      x                   y
     *     / \                 / \
     *    y  rx   ----->      ly  x
     *   / \                     / \
     * ly  ry                   ry rx
     * 右旋做了三件事：
     * 1. 将y(left)的右子节点赋给x(node)的左子节点,并将x赋给y右子节点的父节点(y右子节点非空时)
     * 2. 将y(left)的右子节点设为x(node)，将x的父节点设为y
     * 3. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     */
    private void rotateRight(RBTreeNode<T> node){
        RBTreeNode<T> left = node.getLeft();
        if(left==null){
            throw new java.lang.IllegalStateException("left node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setLeft(left.getRight());
        setParent(left.getRight(),node);

        left.setRight(node);
        setParent(node,left);

        if(parent==null){//node pointer to root
            //right  raise to root node
            root.setLeft(left);
            setParent(left,null);
        }else{
            if(parent.getLeft()==node){
                parent.setLeft(left);
            }else{
                parent.setRight(left);
            }
            //right.setParent(parent);
            setParent(left,parent);
        }
    }
    

    private void setParent(RBTreeNode<T> node,RBTreeNode<T> parent){
        if(node!=null){
            node.setParent(parent);
            if(parent==root){
                node.setParent(null);
            }
        }
    }
    
    /**
     * find the value by give value(include key,key used for search,
     * other field is not used,@see compare method).if this value not exist return null
     * @param value
     * @return
     */
    public T find(T value){
        RBTreeNode<T> dataRoot = getRoot();
        while(dataRoot!=null){
            int cmp = dataRoot.getValue().compareTo(value);
            if(cmp<0){
                dataRoot = dataRoot.getRight();
            }else if(cmp>0){
                dataRoot = dataRoot.getLeft();
            }else{
                return dataRoot.getValue();
            }
        }
        return null;
    }
    
}
