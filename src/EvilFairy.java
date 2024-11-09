import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EvilFairy extends Fairy{
    public EvilFairy(String id, Point position, List<PImage> images, double animationPeriod, double actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> evilFairyTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Fairy.class)));

        if (evilFairyTarget.isPresent()) {
            Point tgtPos = evilFairyTarget.get().getPosition();

            if (moveTo(world, evilFairyTarget.get(), scheduler)) {
                AnimationEntity unicorn =
                        Factory.createUnicorn(Parse.UNICORN_KEY + "_" + evilFairyTarget.get().getId(),
                                tgtPos, 0.5, 0.5,
                                imageStore.getImageList(Parse.UNICORN_KEY));
                world.addEntity(unicorn);
                unicorn.scheduleActions(scheduler, world, imageStore);
                world.removeEntity(scheduler,this);
            }
        }

        super.executeActivity(world, imageStore, scheduler);
    }
}
