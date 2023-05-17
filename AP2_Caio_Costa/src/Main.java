class Node {
    int value;
    Node prev;
    Node next;

    public Node(int value) {
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtBeginning(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void insertAfter(int value, int afterValue) {
        if (isEmpty()) {
            throw new RuntimeException("A lista está vazia.");
        }

        Node newNode = new Node(value);
        Node current = head;
        while (current != null) {
            if (current.value == afterValue) {
                newNode.prev = current;
                newNode.next = current.next;
                if (current.next != null) {
                    current.next.prev = newNode;
                } else {
                    tail = newNode;
                }
                current.next = newNode;
                return;
            }
            current = current.next;
        }

        throw new RuntimeException("O valor " + afterValue + " não foi encontrado na lista.");
    }

    public void insertAtEnd(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void sortAscending() {
        if (isEmpty() || head == tail) {
            return;
        }
        head = mergeSortAscending(head);
        updateTail();
    }

    private Node mergeSortAscending(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddleNode(head);
        Node nextOfMiddle = middle.next;

        middle.next = null;
        nextOfMiddle.prev = null;

        Node left = mergeSortAscending(head);
        Node right = mergeSortAscending(nextOfMiddle);

        return mergeAscending(left, right);
    }

    private Node mergeAscending(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node result;
        if (left.value <= right.value) {
            result = left;
            result.next = mergeAscending(left.next, right);
            if (result.next != null) {
                result.next.prev = result;
            }
        } else {
            result = right;
            result.next = mergeAscending(left, right.next);
            if (result.next != null) {
                result.next.prev = result;
            }
        }
        return result;
    }

    public void sortDescending() {
        if (isEmpty() || head == tail) {
            return;
        }
        head = mergeSortDescending(head);
        updateTail();
    }

    private Node mergeSortDescending(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddleNode(head);
        Node nextOfMiddle = middle.next;

        middle.next = null;
        nextOfMiddle.prev = null;

        Node left = mergeSortDescending(head);
        Node right = mergeSortDescending(nextOfMiddle);

        return mergeDescending(left, right);
    }

    private Node mergeDescending(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node result;
        if (left.value >= right.value) {
            result = left;
            result.next = mergeDescending(left.next, right);
            if (result.next != null) {
                result.next.prev = result;
            }
        } else {
            result = right;
            result.next = mergeDescending(left, right.next);
            if (result.next != null) {
                result.next.prev = result;
            }
        }
        return result;
    }

    private Node getMiddleNode(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private void updateTail() {
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        tail = current;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        System.out.println("Criando uma lista vazia.");
        list.printList();

        System.out.println("Inserindo elementos na lista.");
        list.insertAtBeginning(3);
        list.insertAtBeginning(1);
        list.insertAtEnd(5);
        list.insertAfter(4, 3);

        System.out.println("Elementos da lista:");
        list.printList();

        System.out.println("Ordenação A:");
        list.sortAscending();
        list.printList();

        System.out.println("Ordenação B:");
        list.sortDescending();
        list.printList();
    }
}
