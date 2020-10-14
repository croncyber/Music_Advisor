import java.lang.reflect.Array;
import java.util.stream.IntStream;

/**
 * Class to work with
 */
class Multiplicator {
    @SuppressWarnings("unchecked")
    public static <T extends Copy<T>> Folder<T>[] multiply(Folder<T> folder, int arraySize) {
        Folder<T>[] copies = (Folder<T>[]) Array.newInstance(folder.getClass(), arraySize);
        if (arraySize <= 0) {
            return copies;
        } else {
            IntStream.range(0, copies.length).forEach(i -> {
                copies[i] = new Folder<>();
                if (folder.get() != null) {
                    copies[i].put(folder.get().copy());
                }
            });
        }
        return copies;
    }

}

// Don't change the code below
interface Copy<T> {
    T copy();
}

class Folder<T> {

    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }
}