package linkedList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class ReverseLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> li = new LinkedList<Integer>(Arrays.asList(1,2,3,4,5));
        Stack<Integer> stack = new Stack<>();
        for (Integer i : li) {
            stack.push(i);
        }

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

    }

}
