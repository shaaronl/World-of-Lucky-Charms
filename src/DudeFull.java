import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DudeFull extends Dude{
    public DudeFull(String id, Point position, List<PImage> images, double animationPeriod,
                       double actionPeriod, int resourceLimit)  {
        super(id, position, images, animationPeriod,actionPeriod, resourceLimit);

    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(House.class)));
        if (fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler)) {
            this.transform(world, scheduler, imageStore);
        } else {
           super.executeActivity(world, imageStore, scheduler); //TODO: not sure if this is really necessary
        }

    }
    @Override
    protected ActionEntity _getDude() {
        return Factory.createDudeNotFull(this.getId(), this.getPosition(), this.getActionPeriod(),
                this.getAnimationPeriod(), this.getResourceLimit(), this.getImages());
    }
}
