import java.util.*;

public class IteratorName {
    public static void main(String[] args) {
        var aggr = new ZombieListAggregate();
        var iter = aggr.createIterator();
        Arrays.<Zombie>asList(
                new Zombie("源さくら", "本渡楓", 17),
                new Zombie("二階堂サキ", "田野アサミ", 18),
                new Zombie("水野愛", "種田梨沙", 16),
                new Zombie("紺野純子", "河瀬茉希", 19),
                new Zombie("ゆうぎり", "衣川里佳", 19),
                new Zombie("星川リリィ", "田中美海", 12),
                new Zombie("山田たえ", "三石琴乃", 29)
        ).forEach(aggr::add);

        extractionZombieName(iter)
                .forEach(System.out::println);
    }

    private static List<String> extractionZombieName(Iterator iter) {
        var list = new ArrayList<String>();
        for (iter.first(); !iter.isDone(); iter.next()) {
            list.add(
                    ((Zombie)iter.getItem()).getName()
            );
        }
        return list;
    }
}


class Zombie {
    private String name;
    private String voice;
    private int yearOfLife;

    public Zombie(String name, String voice, int yearOfLife) {
        this.name = name;
        this.voice = voice;
        this.yearOfLife = yearOfLife;
    }

    public String getName() {
        return name;
    }

    public String getVoice() {
        return voice;
    }

    public int getYearOfLife() {
        return yearOfLife;
    }
}


interface Aggregate {
    public Iterator createIterator();
}

class ZombieListAggregate implements Aggregate {
    private List<Zombie> list = new ArrayList<>();

    @Override
    public Iterator createIterator() {
        return new ZombieListIterator(this);
    }

    public void add(Zombie p) {
        list.add(p);
    }

    public Object get(int n) {
        return list.get(n);
    }

    public int size() {
        return list.size();
    }
}

interface Iterator {
    public void first();
    public void next();
    public boolean isDone();
    public Object getItem();
}

class ZombieListIterator implements Iterator {
    private ZombieListAggregate aggr;
    private int cur;

    public ZombieListIterator(ZombieListAggregate aggr) {
        this.aggr = aggr;
    }

    @Override
    public void first() {
        cur = 0;
    }

    @Override
    public void next() {
        cur++;
    }

    @Override
    public boolean isDone() {
        return cur >= aggr.size() ? true : false;
    }

    @Override
    public Object getItem() {
        return aggr.get(cur);
    }
}
