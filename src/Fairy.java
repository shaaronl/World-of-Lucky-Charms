import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Fairy extends MovingEntity implements Entity, AnimationEntity, ActionEntity{
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;
    private final double animationPeriod;
    private final double actionPeriod;
    public Fairy(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;

        this.animationPeriod = animationPeriod;
        this.actionPeriod = actionPeriod;
    }
    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    } // what to do with this method

    public int getImageIndex() {
        return imageIndex;
    }
    public void setImageIndex(int index){
        imageIndex = index;
    }
    public List<PImage> getImages() {
        return images;
    }
    public double getAnimationPeriod() {return animationPeriod;}
    public double getActionPeriod() {return actionPeriod;}
    @Override
    protected Class _getHouseOrStump() {
        return House.class;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fairyTarget = world.findNearest(this.position, new ArrayList<>(List.of(Stump.class)));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().getPosition();

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {

                ActionEntity sapling = Factory.createSapling(Parse.SAPLING_KEY + "_" + fairyTarget.get().getId(), tgtPos, imageStore.getImageList(Parse.SAPLING_KEY), 0);

                world.addEntity(sapling);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }
        ActionEntity.super.executeActivity(world, imageStore, scheduler);
    }
    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (super.moveTo(world,target,scheduler)) {
            world.removeEntity(scheduler, target);
            return true;
        }
        else {
            return false;
        }
    }


}
