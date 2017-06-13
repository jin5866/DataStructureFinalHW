/**
 * Created by user on 2017-06-06.
 */
import java.io.*;

public class RBTree {

    RBTree()
    {
        root = nil;
        nil.red = false;
    }

    String ol = System.getProperty("line.separator");


    static public Node nil = new Node(0);
    public Node root = nil;

    void LeftRotate(Node x)
    {
        Node y = x.right;
        x.right = y.left;
        if(y.left != nil)
        {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == nil)
        {
            root = y;
        }
        else if(x == x.parent.left)
        {
            x.parent.left = y;
        }
        else
        {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    void RightRotate(Node x)
    {
        Node y = x.left;
        x.left = y.right;
        if(y.right != nil)
        {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == nil)
        {
            root = y;
        }
        else if(x == x.parent.right)
        {
            x.parent.right = y;
        }
        else
        {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;

    }

    public void InsertFix(Node z)
    {
        Node y;
        while (z.parent.red == true)
        {
            if(z.parent == z.parent.parent.left)
            {
                y = z.parent.parent.right;
                if(y.red == true)
                {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                }
                else
                {
                    if(z == z.parent.right)
                    {
                        z = z.parent;
                        LeftRotate(z);
                    }
                    z.parent.red = false;
                    z.parent.parent.red = true;
                    RightRotate(z.parent.parent);
                }
            }
            else
            {
                y = z.parent.parent.left;
                if(y.red == true)
                {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                }
                else
                {
                    if(z == z.parent.left)
                    {
                        z = z.parent;
                        RightRotate(z);
                    }
                    z.parent.red = false;
                    z.parent.parent.red = true;
                    LeftRotate(z.parent.parent);
                }
            }

        }

        root.red = false;
    }

    public void Insert(Node z)
    {
        Node y =nil;
        Node x = root;
        while (x != nil)
        {
            y = x;
            if(z.val < x.val)
            {
                x = x.left;
            }
            else
            {
                x= x.right;
            }
        }
        z.parent = y;

        if(y == nil)
        {
            root = z;
        }
        else if(z.val < y.val)
        {
            y.left = z;
        }
        else
        {
            y.right = z;
        }
        z.left =nil;
        z.right = nil;
        z.red = true;
        InsertFix(z);
    }

    public  boolean Delete(int val)
    {
        Node x = root;
        while (x != nil && x.val != val)
        {
            if(val<x.val)
            {
                x = x.left;
            }
            else
            {
                x = x.right;
            }
        }

        if(x != nil)
        {
            if(x.val == val)
            {
                RB_delete(x);
                return true;
            }
            else
            {
                return  false;
            }
        }
        else
        {
            return  false;
        }
    }


    private void RB_delete(Node z)
    {
        Node x;
        Node y = z;
        boolean y_orig = y.red;
        if(z.left == nil)
        {
            x = z.right;
            RB_Transplant(this,z,z.right);
        }
        else if(z.right == nil)
        {
            x = z.left;
            RB_Transplant(this,z,z.left);
        }
        else
        {
            y = tree_minimum(z.right);
            y_orig = y.red;
            x = y.right;
            if( y.parent == z)
            {
                x.parent = y;
            }
            else
            {
                RB_Transplant(this,y,y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            RB_Transplant(this,z,y);
            y.left = z.left;
            y.left.parent = y;
            y.red = z.red;
        }

        if(y_orig == false)
        {
            RBDeleteFixUp(x);
        }
    }

    void RBDeleteFixUp(Node x)
    {
        Node w;
        while (x != root && x.red == false)
        {
            if(x == x.parent.left)
            {
                w = x.parent.right;
                if(w.red == true)
                {
                    w.red = false;
                    x.parent.red = true;
                    LeftRotate(x.parent);
                    w = x.parent.right;
                }

                if(w.left.red == false && w.right.red == false)
                {
                    w.red = true;
                    x = x.parent;
                }
                else
                {
                    if(w.right.red  == false)
                    {
                        w.left.red = false;
                        w.red = true;
                        RightRotate(w);
                        w = x.parent.right;
                    }
                    w.red = x.parent.red;
                    x.parent.red = false;
                    w.right.red = false;
                    LeftRotate(x.parent);
                    x = root;
                }
            }
            else
            {
                w = x.parent.left;
                if(w.red == true)
                {
                    w.red = false;
                    x.parent.red = true;
                    RightRotate(x.parent);
                    w = x.parent.left;
                }
                if(w.right.red == false && w.left.red == false)
                {
                    w.red = true;
                    x = x.parent;
                }
                else
                {
                    if (w.left.red == false)
                    {
                        w.right.red = false;
                        w.red = true;
                        LeftRotate(w);
                        w = x.parent.left;
                    }
                    w.red = x.parent.red;
                    x.parent.red = false;
                    w.left.red = false;
                    RightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.red = false;
    }



    public static void RB_Transplant(RBTree tree, Node u,Node v)
    {
        if(u.parent == nil)
        {
            tree.root = v;
        }
        else if(u == u.parent.left)
        {
            u.parent.left = v;
        }
        else
        {
            u.parent.right = v;
        }
        v.parent = u.parent;

    }

    public static Node tree_minimum(Node tree)
    {
        while(tree.left != nil)
            tree = tree.left;
        return tree;
    }

    public int GetTotal()
    {
        return GetTotal(root);
    }


    private int GetTotal(Node a)
    {
        if(a == nil)
        {
            return 0;
        }
        else
        {
            return GetTotal(a.left) + 1 +GetTotal(a.right);
        }
    }

    public int GetBlackNode()
    {
        return GetBlackNode(root);
    }

    private int GetBlackNode(Node a)
    {
        if(a == nil)
        {
            return 0;
        }
        else
        {
            int tmp;
            tmp = a.red ? 0 : 1;
            return GetBlackNode(a.left)+GetBlackNode(a.right) + tmp;
        }
    }

    public int GetBlackHeight()
    {
        Node x = root;
        int counter = 0;
        while(x != nil)
        {
            if(!x.red)
            {
                counter++;
            }
            x = x.left;
        }

        return counter;
    }

    public void InorderTraversal(BufferedWriter bw)
    {
        InorderTraversal(bw,root);
    }

    private  void InorderTraversal(BufferedWriter bw , Node a)
    {

        if(a == nil)
        {
            return;
        }
        else
        {
            InorderTraversal(bw , a.left);
            try
            {
                bw.write( "" + a.val + ( a.red ? "R" : "B" ) + ol);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            InorderTraversal(bw , a.right);
        }
    }


    public void tree_print(Node tree, int level) {
        if (tree.right != nil)
            tree_print(tree.right, level + 1);
        for(int i = 0; i < level; i++)
            System.out.print("    ");
        System.out.println(tree.val);
        if (tree.left != nil)
            tree_print(tree.left, level + 1);
    }


}

