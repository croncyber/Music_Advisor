import java.util.List;

class QualityControl {

    public static boolean check(List<Box<?>> boxes) {
        boolean check = false;

        if (boxes.isEmpty()) {
            return true;
        }

        try {
            for (Box<?> box : boxes) {
                if (box.get() != null) {
                    Class<?> clazz = box.get().getClass();
                    //  System.out.println(clazz.getGenericSuperclass().getTypeName());
                    if (clazz.getGenericSuperclass().getTypeName().equals("Bakery")) {
                        check = true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }

        return check;
    }

/*
    public static void main(String[] args) {
        Box<Paper> paperBox = new Box<>();
        paperBox.put(new Paper());
        List<Box<?>> boxList = new ArrayList<>();
        boxList.add(paperBox);
        check(boxList);

        Box<Bakery> bakeryBox = new Box<>();
        bakeryBox.put(new Cake());
        List<Box<?>> boxList1 = new ArrayList<>();
        boxList1.add(bakeryBox);
        System.out.println(check(boxList1));
    }
*/

}

// Don't change the code below
class Bakery {
}

class Cake extends Bakery {
}

class Pie extends Bakery {
}

class Tart extends Bakery {
}

class Paper {
}

class Box<T> {

    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }
}

