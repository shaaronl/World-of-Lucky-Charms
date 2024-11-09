import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeNotFull extends Dude{
    private int resourceCount;
    public DudeNotFull(String id, Point position, List<PImage> images, double animationPeriod,
                       double actionPeriod, int resourceLimit, int resourceCount)  {
        super(id, position, images, animationPeriod,actionPeriod, resourceLimit);
        this.resourceCount = resourceCount;

    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));

        if (target.isEmpty() || !this.moveTo(world, target.get(), scheduler) || !this.transformNotFull(world, scheduler, imageStore)) {
           super.executeActivity(world, imageStore, scheduler); // TODO: also not sure
        }
    }

    boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (this.resourceCount >= this.getResourceLimit()) {
            super.transform(world,scheduler,imageStore);
            scheduler.unscheduleAllEvents(this);
            return true;
        }
        return false;
    }
    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (super.moveTo(world,target,scheduler)) {
            this.resourceCount += 1;
            if(target instanceof Tree){
                ((Tree)target).setHealth(((Tree)target).getHealth() - 1);
            }
            return true;
        } else
            return false;
    }
    @Override
    protected ActionEntity _getDude() {
        return Factory.createDudeFull(this.getId(), this.getPosition(), this.getActionPeriod(),
                this.getAnimationPeriod(), this.getResourceLimit(), this.getImages());
    }
}
