package br.com.filgueiras.vector;

import java.util.Arrays;

public class Vector<T> {
    
    private Object[] elements;
    private int position;

    public Vector() {
        this.elements = new Object[5];
        this.position = 0;
    }

    public Vector(int capacity) {
        this.elements = new Object[capacity];
        this.position = 0;
    }

    public void add(T element) {
        if(this.position == this.elements.length){
            lengthIncrease();
        }
        this.elements[this.position] = element;
        this.position ++;
    }

    public void add(T element, int position) {
        if(this.position == this.elements.length){
            lengthIncrease();
        }

        Object[] begin = Arrays.copyOfRange(this.elements, 0, position + 1);
        begin[position] = element;
        Object[] end = Arrays.copyOfRange(this.elements, position, this.elements.length - 1);

        System.arraycopy(begin, 0, this.elements, 0, begin.length);
        System.arraycopy(end, 0, this.elements, position + 1, end.length);

        this.position ++;
    }

    public void remove(T element) {
        int position = indexOf(element);
        if(position != -1) {
            remove(position);
        }
    }

    public void remove(int position) {
        Object[] begin = Arrays.copyOfRange(this.elements, 0, position - 1);
        Object[] end = Arrays.copyOfRange(this.elements, position + 1, this.elements.length);
        System.arraycopy(begin, 0, this.elements, 0, begin.length);
        System.arraycopy(end, 0, this.elements, position, end.length);
        this.position --;
    }

    public int size() {
        return this.elements.length;
    }

    public int indexOf(T element) {
        for (int i = 0; i < this.elements.length; i++) {
            if(element.equals(this.elements[i])){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T element) {
        int index = indexOf(element);
        if(index < 0) return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T getElement(int position){
        T element = (T) this.elements[position];
        return element;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Elements = [ ");
        for (int i = 0; i < this.elements.length; i++) {
            if(this.elements[i] == null){
                break;
            }
            builder.append(this.elements[i].toString());
        }
        builder.append(" ]");
        return builder.toString();
    }

    private void lengthIncrease() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }
}
