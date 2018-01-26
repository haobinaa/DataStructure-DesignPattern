package io.haobin.linked_list;

/**
 * 删除链表重复元素
 * @Author: HaoBin
 * @Date 2018/1/26 18:06
 */
public class DelDuplicateVal {

    public static void deleteDuplicateValue(Node<Integer> head) {
        if (head == null) {
            return;
        }

        Node<Integer> current = head;
        while (current != null) {
            Node<Integer> temp = current;
            while (temp.next != null) {
                // 如果找到重复
                if (temp.next.data.equals(current.data)) {
                    temp.next = temp.next.next;
                }else {
                    temp = temp.next;
                }
            }
            // 查找下一个
            current = current.next;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(44);
        list.add(4);
        list.add(6);
        list.show();
        deleteDuplicateValue(list.head);
        System.out.println();
        list.show();
    }
}
