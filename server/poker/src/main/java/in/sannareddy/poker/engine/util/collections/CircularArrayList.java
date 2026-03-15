package in.sannareddy.poker.engine.util.collections;

import jakarta.annotation.Nonnull;

import java.util.*;

public class CircularArrayList<T> extends ArrayList<T> {
    private int currentIndex = 0;

    public CircularArrayList() {
        super();
    }

    public CircularArrayList(Collection<? extends T> c) {
        super(c);
    }

    //Fetch next element circularly
    public T next() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        T element = get(currentIndex);
        currentIndex = (currentIndex + 1) % size();
        return element;
    }

    //Fetch previous element circularly
    public T previous() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        currentIndex = (currentIndex - 1 + size()) % size();
        return get(currentIndex);
    }

    // Reset current pointer in circular tracking
    public void reset() {
        currentIndex = 0;
    }
    public void resetTo(int index) {
        currentIndex = index;
    }

    //Stateless function to return next element based on index provided
    public T next(int currentIndex) {
        if (isEmpty()) {
            return null;
        }
        currentIndex = (currentIndex + 1) % size();
        return get(currentIndex);
    }

    //Stateless function to return previous element based on index provided
    public T previous(int currentIndex) {
        if (isEmpty()) {
            return null;
        }
        currentIndex = (currentIndex - 1 + size()) % size();
        return get(currentIndex);
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return new CircularIterator(0);
    }
    public Iterator<T> iterator(int startingIndex) {
        return new CircularIterator(startingIndex);
    }

    private class CircularIterator implements Iterator<T> {
        private int index;

        //To track number of elements visited to stop after one circular cycle
        private int count = 0;

        CircularIterator(int startingIndex) {
            index = startingIndex;
        }

        @Override
        public boolean hasNext() {
            return !isEmpty() && count < size();
        }

        @Override
        public T next() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }

            T element = get(index);
            index = (index + 1) % size();
            count++;
            return element;
        }
    }
}