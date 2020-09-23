/**
 * Class to work with
 */
class Multiplicator {

    public static <T extends Copy<T>> Folder<T>[] multiply(Folder<T> folder, int arraySize) {
//        Folder<T>[] objects = new Folder[arraySize];
//
//
//        if (folder.get() == null) {
//            for (int i = 0; i < arraySize; i++) {
//                objects[i] = folder;
//            }
//            return objects;
//        } else {
//            for (int i = 0; i < arraySize; i++) {
//                    objects[i].put(folder.get().copy());
//                    return multiply(objects[i], arraySize);
//            }
//
//        }
//
//
//
//       return objects;
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