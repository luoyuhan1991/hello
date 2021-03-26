package org.luoyuhan.learn;

public class ReverseNode {
    int val;
    ReverseNode next;

    public ReverseNode(int i) {
        this.val = i;
    }

    public static void main(String[] args) {
        ReverseNode node1 = new ReverseNode(1);
        ReverseNode node2 = new ReverseNode(2);
        ReverseNode node3 = new ReverseNode(3);
        node1.next = node2;
        node2.next = node3;
        ReverseNode node = node1;
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
        node = reverse(node1);
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
    }
    
    // pre -> cur -> next
    // pre <- cur    next
    static ReverseNode reverse(ReverseNode node) {
        ReverseNode pre = null;
        ReverseNode cur = node;
        ReverseNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
