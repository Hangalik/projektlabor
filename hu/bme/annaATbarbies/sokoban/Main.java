package hu.bme.annaATbarbies.sokoban;

import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.pushable.Worker;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Hello Bela!");
        System.out.println("Hello Gergo! Oke, ez a masodik probalkozasom az eclipse IDE-bol.");
        System.out.println("Hello Balazs!");
        System.out.println("Hello Jozsi!");
        System.out.println("Meg egyszer probalkozom. Hello Gergo!");
        System.out.println("Hello Anna");

        new Worker().step(Direction.UP);
    }
}
