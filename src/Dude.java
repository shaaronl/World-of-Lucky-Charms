import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class Dude extends MovingEntity implements Entity,AnimationEntity,ActionEntity{
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;

    private final double animationPeriod;
    private final double actionPeriod;
    private int resourceLimit;
    public Dude(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod, int resourceLimit) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.animationPeriod = animationPeriod;
        this.actionPeriod = actionPeriod;
        this.resourceLimit = resourceLimit;
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

    public double getAnimationPeriod(){
        return animationPeriod;
    }
    public double getActionPeriod(){
        return actionPeriod;
    }
    public int getResourceLimit(){
        return resourceLimit;
    }
    protected Class _getHouseOrStump() {
        return Stump.class;
    }
    protected abstract ActionEntity _getDude();

    public void transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        ActionEntity dude = _getDude();
        world.removeEntity(scheduler, this);
        world.addEntity(dude);
        dude.scheduleActions(scheduler, world, imageStore);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        // this is to change the dudes into leprechauns when they approach the rainbow
        ActionEntity.super.executeActivity(world, imageStore, scheduler);
        HashMap<Integer, List<Integer>> points = Parse.createRGBPoints();
        if(points.containsKey(this.getPosition().x)){
            for(Integer i : points.get(this.getPosition().x)){
                if(i == this.getPosition().y){
                    ActionEntity leprechaun = Factory.createLeprechaun(Parse.LEPRECHAUN_KEY + "",
                            this.getPosition(), 0.8, this.getAnimationPeriod(), this.getResourceLimit(),
                            imageStore.getImageList(Parse.LEPRECHAUN_KEY));

                    world.removeEntity(scheduler, this);
                    world.addEntity(leprechaun);
                    leprechaun.scheduleActions(scheduler, world, imageStore);
                }
            }
        }
    }
}
