package myCollection;


import java.util.Collection;
import java.util.Iterator;


public class MyCollection implements Collection {

    private Object[] array = new Object[0];

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean add(Object object) {
        Object[] newArray = new Object[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = object;
        array = newArray;

        return true;
    }

    @Override
    public boolean contains(Object object) {
        for (Object o : array) {
            if (o.equals(object))
                return true;
        }
        return false;

    }

    @Override
    public Iterator iterator() {
        return new MyIteratorForCollection(array);
    }

    @Override
    public Object[] toArray() {
        return createCopy(array);
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size())
            return toArray();
        System.arraycopy(array, 0, a, 0, size());
        if (a.length > size())
            a[size()] = null;
        return a;
    }

    private Object[] createCopy(Object[] array) {
        Object[] result = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean addAll(Collection collection) {
        Object[] objects = collection.toArray();
        for (Object object : objects) {
            add(object);
        }
        return true;

    }

    @Override
    public boolean remove(Object object) {
        if (contains(object)) {
            Object[] result = new Object[array.length - 1];
            int k = 0;
            for (int i = 0; i < array.length - 1; i++) {

                if (array[i].equals(object)) {
                    k = 1;
                }

                result[i] = array[i + k];
            }
            array = result;
            return true;

        } else return false;
    }


    @Override
    public boolean removeAll(Collection collection) {
        int count = 0;
        MyCollection result = new MyCollection();
        result.array = createCopy(array);
        for (int i = 0; i < collection.size(); i++) {
            if (result.contains(collection.toArray()[i])) {
                result.remove(collection.toArray()[i]);
                count++;
            }
        }
        array = result.array;
        if (count > 0)
            return true;
        return false;

    }

    @Override
    public boolean containsAll(Collection collection) {
        int count = 0;
        for (int i = 0; i < collection.size(); i++) {
            if (contains(collection.toArray()[i]))
                count++;
        }
        if (count == collection.size())
            return true;
        return false;
    }

    @Override
    public boolean retainAll(Collection collection) {
        if (containsAll(collection))
            return false;
        MyCollection result = new MyCollection();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < collection.size(); j++) {
                if (array[i].equals(collection.toArray()[j]))
                    result.add(array[i]);
            }
        }
        array = result.array;
        return true;
    }

    @Override
    public void clear() {
        array = new Object[0];
    }

    public Object get(int index) {
        return array[index];
    }

    @Override
    public String toString() {
        String result = "";
        for (Object o : array) {
            result += o + ", ";
        }
        if (result.length() != 0)
            result = result.substring(0, result.length() - 2);
        return "[" + result + "]";

    }
}
