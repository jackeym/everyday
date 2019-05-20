package com.one.algorithm.tree;

/**
 * 红黑树
 * 1、每个节点不是红色就是黑色的；
 * 2、根节点总是黑色的；
 * 3、所有的叶节点都是是黑色的（红黑树的叶子节点都是空节点（NIL或者NULL））；
 * 4、父子节点之间不能出现两个连续的红节点；
 * 5、任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等。
 * @author: one
 */
public class RBTree<T extends Comparable<T>> {
    private RBTreeNode<T> root;
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
        return root;
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
            root = right;
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
            root = left;
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
        RBTreeNode<T> node = getRoot();
        while(node!=null){
            int cmp = node.getValue().compareTo(value);
            if(cmp<0){
                node = node.getRight();
            }else if(cmp>0){
                node = node.getLeft();
            }else{
                return node.getValue();
            }
        }
        return null;
    }


    public T addNode(T value){
        RBTreeNode<T> t = new RBTreeNode<T>(value);
        return addNode(t);
    }

    private T addNode(RBTreeNode<T> node){
        node.setLeft(null);
        node.setRight(null);
        node.setRed(true);
        setParent(node,null);
        if(root==null){
            root = node;
            //root node is black
            node.setRed(false);
            size.incrementAndGet();
        }else{
            RBTreeNode<T> x = findParentNode(node);
            int cmp = x.getValue().compareTo(node.getValue());

            if(this.overrideMode && cmp==0){
                T v = x.getValue();
                x.setValue(node.getValue());
                return v;
            }else if(cmp==0){
                //value exists,ignore this node
                return x.getValue();
            }

            setParent(node,x);

            if(cmp>0){
                x.setLeft(node);
            }else{
                x.setRight(node);
            }

            fixInsert(node);
            size.incrementAndGet();
        }
        return null;
    }

    private RBTreeNode<T> findParentNode(RBTreeNode<T> x){
        RBTreeNode<T> node = getRoot();
        RBTreeNode<T> current  = null;  // x的父节点

        while(node!=null){
            current = node;
            int cmp = node.getValue().compareTo(x.getValue());
            if(cmp==0){
                return node;
            }
            if(cmp>0){
                node = node.getLeft();
            }else if(cmp<0){
                node = node.getRight();
            }
        }
        return current;
    }

    /**
     * 新插入的节点是红色的，插入修复操作如果遇到父节点的颜色为黑则修复操作结束。也就是说，只有在父节点为红色节点的时候是需要插入修复操作的。
     * 插入修复操作分为以下的三种情况，而且新插入的节点的父节点都是红色的：
     *    1.叔叔节点也为红色。
     *    2.叔叔节点为空，且祖父节点、父节点和新节点处于一条斜线上。
     *    3.叔叔节点为空，且祖父节点、父节点和新节点不处于一条斜线上。
     */
    private void fixInsert(RBTreeNode<T> x){
        RBTreeNode<T> parent = x.getParent();

        while(parent!=null && parent.isRed()){
            RBTreeNode<T> uncle = getUncle(x);
            if(uncle==null){//need to rotate
                RBTreeNode<T> ancestor = parent.getParent();
                //ancestor is not null due to before before add,tree color is balance
                if(parent == ancestor.getLeft()){
                    boolean isRight = x == parent.getRight();
                    if(isRight){
                        rotateLeft(parent);
                    }
                    rotateRight(ancestor);

                    if(isRight){
                        x.setRed(false);
                        parent=null;//end loop
                    }else{
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                }else{
                    boolean isLeft = x == parent.getLeft();
                    if(isLeft){
                        rotateRight(parent);
                    }
                    rotateLeft(ancestor);

                    if(isLeft){
                        x.setRed(false);
                        parent=null;//end loop
                    }else{
                        parent.setRed(false);
                    }
                    ancestor.setRed(true);
                }
            }else{//uncle is red
                parent.setRed(false);
                uncle.setRed(false);
                parent.getParent().setRed(true);
                x=parent.getParent();
                parent = x.getParent();
            }
        }
        getRoot().makeBlack();
        getRoot().setParent(null);
    }

    /**
     * get uncle node
     * @param node
     * @return
     */
    private RBTreeNode<T> getUncle(RBTreeNode<T> node){
        RBTreeNode<T> parent = node.getParent();
        RBTreeNode<T> ancestor = parent.getParent();
        if(ancestor==null){
            return null;
        }
        if(parent == ancestor.getLeft()){
            return ancestor.getRight();
        }else{
            return ancestor.getLeft();
        }
    }
    
    /**
     * 说明：
     * 1、被删除的节点没有儿子，即删除的是叶子节点。那么，直接删除该节点。
     * 2、被删除的节点只有一个儿子。那么直接删除该节点，并用该节点的唯一子节点顶替它的初始位置。
     * 3、被删除的节点有两个儿子。那么先找出它的后继节点（右孩子中的最小的，该孩子没有子节点或者只有一右孩子）。
     *    然后把"它的后继节点的内容"复制给"该节点的内容"；之后，删除"它的后继节点"。
     *    在这里后继节点相当与替身，在将后继节点的内容复制给"被删除节点"之后，再将后继节点删除。
     *    ------这样问题就转化为怎么删除后继即节点的问题？
     *    在"被删除节点"有两个非空子节点的情况下，它的后继节点不可能是双子都非空。
     *    注：后继节点为补充被删除的节点；
     *    即：意味着"要么没有儿子，要么只有一个儿子"。
     *    若没有儿子，则回归到（1）。
     *    若只有一个儿子，则回归到（2）。
     */
    
    /**
     * remove the node by give value,if this value not exists in tree return null
     * @param value include search key
     * @return the value contain in the removed node
     */
    public T remove(T value){
        RBTreeNode<T> dataRoot = getRoot();
        RBTreeNode<T> parent = dataRoot;

        while(dataRoot!=null){
            int cmp = dataRoot.getValue().compareTo(value);
            if(cmp<0){
                parent = dataRoot;
                dataRoot = dataRoot.getRight();
            }else if(cmp>0){
                parent = dataRoot;
                dataRoot = dataRoot.getLeft();
            }else{
                if(dataRoot.getRight()!=null){
                    //找到后继节点
                    RBTreeNode<T> min = removeMin(dataRoot.getRight());
                    //x used for fix color balance
                    RBTreeNode<T> x = min.getRight()==null ? min.getParent() : min.getRight();
                    boolean isParent = min.getRight()==null;
                    
                    //待删除节点的左节点
                    min.setLeft(dataRoot.getLeft());
                    setParent(dataRoot.getLeft(),min);
                    
                    // 后继节点
                    if(parent.getLeft()==dataRoot){
                        parent.setLeft(min);
                    }else{
                        parent.setRight(min);
                    }
                    setParent(min,parent);
                    
                    //待删除节点的右节点
                    if(min!=dataRoot.getRight()){
                        min.setRight(dataRoot.getRight());
                        setParent(dataRoot.getRight(),min);
                    }
                    
                    boolean curMinIsBlack = min.isBlack();
                    //inherit dataRoot's color
                    min.setRed(dataRoot.isRed());

                    //remove a black node,need fix color
                    if(curMinIsBlack){
                        if(min!=dataRoot.getRight()){
                            fixRemove(x,isParent);
                        }else if(min.getRight()!=null){
                            fixRemove(min.getRight(),false);
                        }else{
                            fixRemove(min,true);
                        }
                    }
                }else{
                    setParent(dataRoot.getLeft(),parent);
                    if(parent.getLeft()==dataRoot){
                        parent.setLeft(dataRoot.getLeft());
                    }else{
                        parent.setRight(dataRoot.getLeft());
                    }
                    //current node is black and tree is not empty
                    if(dataRoot.isBlack() && !(root==null)){
                        RBTreeNode<T> x = dataRoot.getLeft()==null
                                ? parent :dataRoot.getLeft();
                        boolean isParent = dataRoot.getLeft()==null;
                        fixRemove(x,isParent);
                    }
                }
                setParent(dataRoot,null);
                dataRoot.setLeft(null);
                dataRoot.setRight(null);
                if(getRoot()!=null){
                    getRoot().setRed(false);
                    getRoot().setParent(null);
                }
                size.decrementAndGet();
                return dataRoot.getValue();
            }
        }
        return null;
    }

    /**
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     * 如果当前待删除节点是红色的，它被删除之后对当前树的特性不会造成任何破坏影响。
     * 而如果被删除的节点是黑色的，这就需要进行进一步的调整来保证后续的树结构满足要求。
     * 这里我们只修正删除的节点是黑色的情况：
     *
     * 删除操作的总体思想是从兄弟节点借调黑色节点使树保持局部的平衡，如果局部的平衡达到了，
     * 就看整体的树是否是平衡的，如果不平衡就接着向上追溯调整。
     * 为了保证删除节点的父节点左右两边黑色节点数一致，需要重点关注父节点没删除的那一边节点是不是黑色。
     *
     * 1）、当前节点是黑色的，且兄弟节点是红色的（那么父节点和兄弟节点的子节点肯定是黑色的）；
     *      操作：将父节点涂红，将兄弟节点涂黑，然后将当前节点的父节点进行支点左旋。这样就会转化为情况2中的某种状态。
     * 2）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的两个子节点均为黑色的；
     *      操作：将兄弟节点涂红，将当前节点指向其父节点，将其父节点指向当前节点的祖父节点，继续往树根递归判断以及调整
     * 3）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的左子节点是红色，右子节点时黑色的(兄弟节点在右边)；
     *      操作：把当前节点的兄弟节点涂红，把兄弟节点的左子节点涂黑，然后以兄弟节点作为支点做右旋操作。
     * 4）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的右子节点是红色，左子节点任意颜色(兄弟节点在右边)。
     *      操作：把兄弟节点涂成父节点的颜色，再把父节点涂黑，把兄弟节点的右子节点涂黑，然后以当前节点的父节点为支点做左旋操作。
     *
     * 以上四种情况中，2，3，4都是（当前节点是黑色的，且兄弟节点是黑色的）的子集。
     *
     */
    
    /**
     * fix remove action
     * @param node
     * @param isParent
     */
    private void fixRemove(RBTreeNode<T> node,boolean isParent){
        RBTreeNode<T> cur = isParent ? null : node;
        boolean isRed = isParent ? false : node.isRed();
        RBTreeNode<T> parent = isParent ? node : node.getParent();

        while(!isRed && !isRoot(cur)){
            RBTreeNode<T> sibling = getSibling(cur,parent);
            //sibling is not null,due to before remove tree color is balance

            //if cur is a left node
            boolean isLeft = parent.getRight()==sibling;
            if(sibling.isRed() && !isLeft){//case 1
                //cur in right
                parent.makeRed();
                sibling.makeBlack();
                rotateRight(parent);
            }else if(sibling.isRed() && isLeft){
                //cur in left
                parent.makeRed();
                sibling.makeBlack();
                rotateLeft(parent);
            }else if(isBlack(sibling.getLeft()) && isBlack(sibling.getRight())){//case 2
                sibling.makeRed();
                cur = parent;
                isRed = cur.isRed();
                parent=parent.getParent();
            }else if(isLeft && !isBlack(sibling.getLeft())
                    && isBlack(sibling.getRight())){//case 3
                sibling.makeRed();
                sibling.getLeft().makeBlack();
                rotateRight(sibling);
            }else if(!isLeft && !isBlack(sibling.getRight())
                    && isBlack(sibling.getLeft()) ){
                sibling.makeRed();
                sibling.getRight().makeBlack();
                rotateLeft(sibling);
            }else if(isLeft && !isBlack(sibling.getRight())){//case 4
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getRight().makeBlack();
                rotateLeft(parent);
                cur=getRoot();
            }else if(!isLeft && !isBlack(sibling.getLeft())){
                sibling.setRed(parent.isRed());
                parent.makeBlack();
                sibling.getLeft().makeBlack();
                rotateRight(parent);
                cur=getRoot();
            }
        }
        if(isRed){
            cur.makeBlack();
        }
        if(getRoot()!=null){
            getRoot().setRed(false);
            getRoot().setParent(null);
        }

    }

    //get sibling node
    private RBTreeNode<T> getSibling(RBTreeNode<T> node,RBTreeNode<T> parent){
        parent = node==null ? parent : node.getParent();
        if(node==null){
            return parent.getLeft()==null ? parent.getRight() : parent.getLeft();
        }
        if(node==parent.getLeft()){
            return parent.getRight();
        }else{
            return parent.getLeft();
        }
    }

    private boolean isBlack(RBTreeNode<T> node){
        return node==null || node.isBlack();
    }

    private boolean isRoot(RBTreeNode<T> node){
        return root == node && node.getParent()==null;
    }

    /**
     * find the successor node
     * @param node current node's right node
     * @return
     */
    private RBTreeNode<T> removeMin(RBTreeNode<T> node){
        //find the min node
        RBTreeNode<T> parent = node;
        while(node!=null && node.getLeft()!=null){
            parent = node;
            node = node.getLeft();
        }
        //remove min node
        if(parent==node){
            return node;
        }

        parent.setLeft(node.getRight());
        setParent(node.getRight(),parent);

        //don't remove right pointer,it is used for fixed color balance
        //node.setRight(null);
        return node;
    }


}
