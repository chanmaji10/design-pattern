
public class SingletonTest {
    public static void main(String[] args) {
        var renban1 = Renban.getInstance();
        var renban2 = Renban.getInstance();
        System.out.println(renban1.getNumber());
        System.out.println(renban2.getNumber());
        System.out.println(renban1.getNumber());

    }
}

class Renban {
    private static Renban inst = new Renban();
    private static String num;

    private Renban() {
        this.num = "0000";
    }

    public static Renban getInstance() {
        return inst;
    }

    public static String getNumber() {
        return num = String.format("%04d", Integer.parseInt(num) + 1);
    }
}