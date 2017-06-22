/**
 * Created by user on 2017-06-22.
 */
public class List {
    listNode first = null;

    public  void add(int val)
    {
        listNode tmp = new listNode(val);

        if(first == null)
        {
            first = tmp;
            tmp.before = tmp;
            tmp.next = tmp;
        }
        else
        {
            tmp.next = first;
            tmp.before = first.before;
            tmp.before.next = tmp;
            first.before = tmp;
        }
    }

    public String[] searchONSorted(int val)
    {
        String[] re = {"","",""};
        listNode node = first;
        boolean check = true;
        while(check)
        {
            if(node.val < val)
            {
                node = node.next;
            }
            else if(node.val == val)
            {
                check = false;
                if(node == first)
                {
                    re[0] += "nil";
                }
                else
                {
                    re[0] += node.before.val;
                }

                re[1] += node.val;
                if(node.next == first)
                {
                    re[2] += "nil";
                }
                else
                {
                    re[2] += node.next.val;
                }

            }
            else
            {
                check = false;
                if(node == first)
                {
                    re[0] += "nil";
                }
                else
                {
                    re[0] += node.before.val;
                }

                re[1] += "nil";

                if(node.next == first)
                {
                    re[2] += "nil";
                }
                else
                {
                    re[2] += node.next.val;
                }
            }

            if(node.next == first)
            {
                check = false;
            }
        }

        return re;
    }


}

class  listNode{
    listNode next;
    listNode before;
    int val;
    listNode(int val)
    {
        this.val =val;
    }
}
