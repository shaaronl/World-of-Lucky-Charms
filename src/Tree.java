import processing.core.PImage;

import java.util.List;

public class Tree implements Entity,ActionEntity,AnimationEntity {
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;
    private final double animationPeriod;
    private final double actionPeriod;
    private int health;
    public Tree(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod, int health) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;

        this.animationPeriod = animationPeriod;
        this.actionPeriod = actionPeriod;

        this.health = health;
    }
    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int index){
        imageIndex = index;
    }
    public List<PImage> getImages() {
        return images;
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){ this.health = health;}

    public double getAnimationPeriod() {return animationPeriod;}
    public double getActionPeriod() {return actionPeriod;}

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if (!this.transform(world, scheduler, imageStore)) {

            ActionEntity.super.executeActivity(world, imageStore, scheduler);
        }
    }
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.getHealth() <= 0) {
            Entity stump = Factory.createStump(Parse.STUMP_KEY + "_" + this.getId(), this.getPosition(),
                    imageStore.getImageList(Parse.STUMP_KEY));

            world.removeEntity(scheduler, this);

            world.addEntity(stump);

            return true;
        }

        return false;
    }
}
