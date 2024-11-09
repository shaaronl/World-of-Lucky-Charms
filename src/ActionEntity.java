public interface ActionEntity extends Entity, AnimationEntity{
    //ActionEntities: Dude <- DudeNF DudeF, Fairy, Sapling, Tree
    // extends animation entity, so that means all entities should also
    // have AnimationEntity methods, and thus should be AnimationEntities
    double getActionPeriod();
    @Override
    default void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, Factory.createActivityAction(this, world, imageStore), this.getActionPeriod());
        AnimationEntity.super.scheduleActions(scheduler, world, imageStore);
    }
    default void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        scheduler.scheduleEvent(this, Factory.createActivityAction(this, world, imageStore), this.getActionPeriod());
    }

}
