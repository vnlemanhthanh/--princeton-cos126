//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 2;
    private Item[] a = (Item[]) new Object[2];
    private int N = 0;

    public RandomQueue() {
    }

    public boolean isEmpty() {
        return this.N == 0;
    }

    public int size() {
        return this.N;
    }

    private void resize(int var1) {
        Object[] var2 = (Object[])(new Object[var1]);

        for(int var3 = 0; var3 < this.N; ++var3) {
            var2[var3] = this.a[var3];
        }

        this.a = (Item[]) var2;
    }

    public void enqueue(Item var1) {
        if (this.N == this.a.length) {
            this.resize(2 * this.a.length);
        }

        this.a[this.N++] = var1;
    }

    public Item sample() {
        if (this.isEmpty()) {
            throw new RuntimeException("Randomized queue underflow");
        } else {
            int var1 = StdRandom.uniform(this.N);
            return this.a[var1];
        }
    }

    public Item dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Randomized queue underflow");
        } else {
            int var1 = StdRandom.uniform(this.N);
            Object var2 = this.a[var1];
            this.a[var1] = this.a[this.N - 1];
            this.a[this.N - 1] = null;
            --this.N;
            if (this.N > 0 && this.N == this.a.length / 4) {
                this.resize(this.a.length / 2);
            }

            return (Item) var2;
        }
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private RandomQueue<Item> copy = new RandomQueue();

        public RandomIterator() {
            for(int var2 = 0; var2 < RandomQueue.this.N; ++var2) {
                this.copy.enqueue(RandomQueue.this.a[var2]);
            }

        }

        public boolean hasNext() {
            return !this.copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                return this.copy.dequeue();
            }
        }
    }
}

