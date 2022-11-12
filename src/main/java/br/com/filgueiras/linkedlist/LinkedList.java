package br.com.filgueiras.linkedlist;

public class LinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    public void add(T element) {
        if(isEmpty()) {
            addAtBegin(element);
        } else {
            addAtEnd(element);
        }
        this.size ++;
    }

    public void add(T element, int position) {
        if(position == 0) {
            addAtBegin(element);
        } else if(position == this.size - 1) {
            addAtEnd(element);
        } else {
            addAtPosition(element, position);
        }
        this.size ++;
    }

    public void remove() {
        Node<T> last = this.last;
        this.last = last.getPrevious();
        this.last.setNext(null);
        last.setPrevious(null);
    }

    public void remove(int position) {
        if(position == 0) {
            removeAtBegin();
        } else if(position == this.size - 1) {
            remove();
        } else {
            removeAt(position);
        }
    }

    private void removeAt(int position) {
        Node<T> current = getNode(position);
        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());
    }

    private void removeAtBegin() {
        Node<T> first = this.first;
        this.first = this.first.getNext();
        this.first.setPrevious(null);
        first.setNext(null);
    }

    public int size() {
        return this.size;
    }

    public boolean contains(T element) {
        int index = indexOf(element);
        if(index >= 0) {
            return true;
        }
        return false;
    }

    public int indexOf(T element) {
        for(int i = 0; i < this.size; i++) {
            Node<T> node = getNode(i);
            if(node.getElement() != null && node.getElement().equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public T getElement(int position) {
        Node<T> node = getNode(position);
        return node.getElement();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("LinkedList = [ ");

        if(!isEmpty()) {
            Node<T> node = this.first;
            builder.append(node.getElement().toString());
            while(node.getNext() != null) {
                builder.append(node.getNext().getElement().toString());
                node = node.getNext();
            }
        }

        builder.append(" ]");
        return builder.toString();
    }

    private void addAtPosition(T element, int position) {
        Node<T> node = new Node<>(element);
        Node<T> current = getNode(position);

        node.setNext(current);
        node.setPrevious(current.getPrevious());
        current.setPrevious(node);
        current.getPrevious().setNext(node);
    }

    private void addAtEnd(T element) {
        Node<T> node = new Node<>(element);
        this.last.setNext(node);
        node.setPrevious(this.last);
        this.last = node;
    }

    private void addAtBegin(T element) {
        Node<T> node = new Node<>(element);
        if(this.isEmpty()) {
            this.first = node;
            this.last = node;
        } else {
            this.first.setPrevious(node);
            node.setNext(this.first);
            this.first = node;
        }
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private Node<T> getNode(int position) {
        Node<T> node = this.first;
        for(int i = 1; i <= position; i++) {
            node = node.getNext();
        }
        return node;
    }
    
}
