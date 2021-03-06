package com.runtime.algorithm.structure.tree.avltree;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/3 17:34
 * @Description
 */
public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    //todo 返回左子树的高度
    public int leftHeight() {
        if (this.left != null) {
            return this.left.height();
        }
        return 0;
    }

    //todo 返回右子树的高度
    public int rightHeight() {
        if (this.right != null) {
            return this.right.height();
        }
        return 0;
    }

    //todo 左旋转方法
    private void leftRotate() {
        // 创建新的节点,值等于当前节点的值
        Node newNode = new Node(this.value);
        // 把当前节点的左子树 设置为 新节点的左子树
        newNode.left = this.left;
        // 把当前节点右字树的左子树 设置为新节点的右子树
        newNode.right = this.right.left;
        // 把当前节点的值 替换成右子节点的值
        this.value = this.right.value;
        // 把当前节点的右子树设置成当前节点右子树的右子树
        this.right = this.right.right;
        // 把当前节点的左子树 设置为新的根节点
        this.left = newNode;
    }

    //todo 右旋转方法
    private void rightRotate() {
        // 创建新的节点,值等于当前节点的值
        Node newNode = new Node(this.value);
        // 把当前节点的右子树 设置为 新节点的右子树
        newNode.right = this.right;
        // 把当前节点左字树的右子树 设置为新节点左子树
        newNode.left = this.left.right;
        // 把当前节点的值 替换成左子节点的值
        this.value = this.left.value;
        // 把当前节点的左子树设置成当前节点左子树的左子树
        this.left = this.left.left;
        // 把当前节点的右子树 设置为新的根节点
        this.right = newNode;
    }


    //todo 返回以该节点为根节点的树的高度
    public int height() {
        //System.out.println(this + " --- > 进来一次 ");
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /***
     * 调用方法的输出
     * Node{value=3} --- > 进来一次
     * 1
     * Node{value=6} --- > 进来一次
     * Node{value=5} --- > 进来一次
     * Node{value=7} --- > 进来一次
     * Node{value=8} --- > 进来一次
     * 4
     */


    //todo 添加节点的方法
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加
                this.left.add(node);
            }
        } else { // 添加的结点的值大于 当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加
                this.right.add(node);
            }

        }


        //todo 如果 当前节点的右子树的高度 减去 当前节点的左子树的高度 > 1 则需要左旋转
        if (rightHeight() - leftHeight() > 1) {
            //todo 如果它的右子树的 左子树 的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate(); //左旋转..
            } else {
                //直接进行左旋转即可
                leftRotate();
            }
            return; //必须要!!!
        }

        //todo 如果当前节点的的左子树的高度 减去 当前节点的右子树的高度 > 1 则需要右旋转
        if (leftHeight() - rightHeight() > 1) {
            //todo 如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightRotate();
            }
        }
    }


    //todo 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value + "}";
    }

/*    public static void main(String[] args) {
        Node node = new Node(1);
        node.add(new Node(4));
        node.add(new Node(3));
        node.add(new Node(6));
        node.add(new Node(5));
        node.add(new Node(7));
        node.add(new Node(8));
        node.infixOrder();
        System.out.println(node.leftHeight());
        System.out.println(node.rightHeight());
    }*/
}

