import java.util.LinkedList;

public class MyHashTable<K, V> {
    private class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.size = 0;
    }

    public void put(K key, V value) {
        int bucketIndex = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int bucketIndex = hash(key);
        LinkedList<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void printBucketSizes() {
        for (int i = 0; i < capacity; i++) {
            System.out.println("Bucket " + i + " size: " + buckets[i].size());
        }
    }
}