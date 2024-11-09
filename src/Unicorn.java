import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Unicorn extends MovingEntity implements Entity, AnimationEntity, ActionEntity{
    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;
    private final double animationPeriod;
    private final double actionPeriod;
    public Unicorn(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod) {
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
    public double getAnimationPeriod() {return animationPeriod;}
    public double getActionPeriod() {return actionPeriod;}
    @Override
    protected Class _getHouseOrStump() {
        return Stump.class;
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        //if no lucky charms in world, unicorn moves saplings but doesn't do anything (so it doesn't just pause when spawned)
        if (VirtualWorld.luckyCharmCount == 0) {
            Optional<Entity> saplingTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Sapling.class)));

            if (saplingTarget.isPresent()) {
                Point nextPos = this.nextPosition(world, saplingTarget.get().getPosition());
                if (!this.getPosition().equals(nextPos)) {
                    world.moveEntity(scheduler, this, nextPos);
                }
            }
        }

        //when lucky charms are in the world, unicorn eats it to become evilFairy
        if (VirtualWorld.luckyCharmCount > 0) {

            Optional<Entity> unicornTarget2 = world.findNearest(this.position,
                    new ArrayList<>(List.of(RainbowCharm.class, HatCharm.class, StarCharm.class,
                            MoonCharm.class))); // ONLY GOES TO/EATS THESE CHARMS TO TRANSFORM
            if (unicornTarget2.isPresent()) {
                Point tgtPos2 = unicornTarget2.get().getPosition();
                if(moveTo(world, unicornTarget2.get(), scheduler)){
                    if(unicornTarget2.get().getClass() == RainbowCharm.class){
                        AnimationEntity betterFairy =
                                Factory.createEvilFairy(Parse.EVILFAIRY_KEY + "_" + unicornTarget2.get().getId(),
                                        tgtPos2, 0.20, 0.20,
                                        imageStore.getImageList(Parse.EVILFAIRY_KEY));
                        world.addEntity(betterFairy);
                        betterFairy.scheduleActions(scheduler, world, imageStore);
                    }
                    if(unicornTarget2.get().getClass() == HatCharm.class){
                        ActionEntity leprechaun = Factory.createLeprechaun(Parse.LEPRECHAUN_KEY + "",
                                tgtPos2, 0.8, this.getAnimationPeriod(), 3,
                                imageStore.getImageList(Parse.LEPRECHAUN_KEY));
                        world.addEntity(leprechaun);
                        leprechaun.scheduleActions(scheduler, world, imageStore);
                    }
                    if(unicornTarget2.get().getClass()  == StarCharm.class){
                        AnimationEntity fairy =
                                Factory.createFairy(Parse.FAIRY_KEY + "_" + unicornTarget2.get().getId(),
                                        tgtPos2, 0.15, 0.15,
                                        imageStore.getImageList(Parse.FAIRY_KEY));
                        world.addEntity(fairy);
                        fairy.scheduleActions(scheduler, world, imageStore);
                    }
                    if(unicornTarget2.get().getClass() == MoonCharm.class){
                        ActionEntity dude = Factory.createDudeNotFull(Parse.DUDE_KEY + "",
                                tgtPos2, this.getActionPeriod(), this.getAnimationPeriod(), 3,
                                imageStore.getImageList(Parse.DUDE_KEY));
                        world.addEntity(dude);
                        dude.scheduleActions(scheduler, world, imageStore);
                    }
                    world.removeEntity(scheduler, this);
                    VirtualWorld.luckyCharmCount -= 1; // to account for eaten charms
                }
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
