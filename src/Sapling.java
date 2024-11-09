import processing.core.PImage;

import java.util.List;
import java.util.Random;

public class Sapling extends Tree{
    private int healthLimit;
    public Sapling(String id, Point position, List<PImage> images, double animationPeriod,
                   double actionPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod, actionPeriod, health);
        this.healthLimit = healthLimit;
    }
    private static int getIntFromRange(int max, int min) {
        Random rand = new Random();
        return min + rand.nextInt(max-min);
    }

    private static double getNumFromRange(double max, double min) {
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        setHealth(getHealth() + 1);
        super.executeActivity(world, imageStore, scheduler);
    }
    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if(super.transform(world, scheduler, imageStore)){
            return true;
        }
        else if (this.getHealth() >= this.healthLimit) {
            ActionEntity tree = Factory.createTree(Parse.TREE_KEY + "_" + this.getId(), this.getPosition(),
                    getNumFromRange(Parse.TREE_ACTION_MAX, Parse.TREE_ACTION_MIN),
                    getNumFromRange(Parse.TREE_ANIMATION_MAX, Parse.TREE_ANIMATION_MIN),
                    getIntFromRange(Parse.TREE_HEALTH_MAX, Parse.TREE_HEALTH_MIN),
                    imageStore.getImageList(Parse.TREE_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(tree);
            tree.scheduleActions(scheduler, world, imageStore);
            return true;
        }

        return false;
    }

}

