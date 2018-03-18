package hu.bme.annaATbarbies.sokoban.model.pushable;

import hu.bme.annaATbarbies.sokoban.SkeletonHelper;
import hu.bme.annaATbarbies.sokoban.model.Direction;
import hu.bme.annaATbarbies.sokoban.model.field.Field;

public class Worker extends Pushable implements Controller {
	
	//a jatekos lep
    @Override
    public void step(Direction dir) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Worker step function.");
        
        Field neighbor = new Field().getNeigbor(Direction.UP);
        Pushable obstacle = neighbor.getObstacle();	//lekeri a szomszedos mezo tolhato objektumat
        if (obstacle == null) {
            neighbor.accept(new Worker());			//ha nincs, akkor a szomszedos mezore lep
        } else {
            obstacle.push(Direction.UP, new Worker());	//ha van, akkor eltolja

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                neighbor.accept(new Worker());			//ha sikerult eltolnia, akkor a szomszedos mezore lephet
            }											//ha nem, akkor nem lep sehova
        }

        SkeletonHelper.popIndent();
    }

    @Override
    public void gainPoint() {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Worker gainPoint function.");
        SkeletonHelper.popIndent();
    }

    /**
     * Definiálja, hogy mi történik, ha egy láda tolta meg. Ekkor az állapota láda által tolttá változik.
     * @param dir
     * @param box
     */
    @Override
    public void push(Direction dir, Box box) {
    	SkeletonHelper.appendIndent();
        SkeletonHelper.write("Worker push function. Called when box pushed.");
        
        Field neighbor = new Field().getNeigbor(Direction.UP);
                
        Pushable obstacle = neighbor.getObstacle();	//lekeri a szomszedos mezo tolhato objektumat
        if (obstacle == null) {
            neighbor.accept(new Worker());			//ha nincs, akkor a szomszedos mezore lep
        } else {
            obstacle.push(Direction.UP, new Worker());	//ha van, akkor eltolja

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                neighbor.accept(new Worker());			//ha sikerult eltolnia, akkor a szomszedos mezore lephet
            }											//ha nem, akkor nem lep sehova
        }
        
        SkeletonHelper.popIndent();
    }

    /**
     * Definiálja, hogy mi történik, ha egy munkás tolta meg.
     * @param dir
     * @param worker
     */
    @Override
    public void push(Direction dir, Worker worker) {
    	SkeletonHelper.appendIndent();
        SkeletonHelper.write("Worker push function. Called when worker pushed.");
        
        Field neighbor = new Field().getNeigbor(Direction.UP);
                
        Pushable obstacle = neighbor.getObstacle();	//lekeri a szomszedos mezo tolhato objektumat
        if (obstacle == null) {
            neighbor.accept(new Worker());			//ha nincs, akkor a szomszedos mezore lep
        } else {
            obstacle.push(Direction.UP, new Worker());	//ha van, akkor eltolja

            obstacle = neighbor.getObstacle();
            if (obstacle == null) {
                neighbor.accept(new Worker());			//ha sikerult eltolnia, akkor a szomszedos mezore lephet
            }											//ha nem, akkor nem lep sehova
        }
        
        SkeletonHelper.popIndent();
    }

    /**
     * Definiálja, hogy mi történik, ha össze akarják nyomni.
     * Ha az állapota az, hogy láda tolta, akkor megöli magát.
     * @param dir
     * @return
     */
    public boolean crush (Direction dir) {
        SkeletonHelper.appendIndent();
        SkeletonHelper.write("Worker crush function.");

        SkeletonHelper.write("What is pushing this worker? 1: It's stepping; 2: It's pushed by a Worker; 3: It's pushed by a Box");
        int responseNum = SkeletonHelper.readInt();

        boolean ret;
        switch(responseNum) {
            default:
            case 1: ret = false;
                break;
            case 2: ret = new Worker().crush(Direction.DOWN);
                break;
            case 3: this.die();
                ret = true;
                break;
        }

        SkeletonHelper.popIndent();
        return ret;
    }
}
