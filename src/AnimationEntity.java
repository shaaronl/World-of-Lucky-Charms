public interface AnimationEntity extends Entity {
    //AnimationEntity: Dude <-DudeNF DudeF, Fairy, Tree<-Sapling, Obstacle
    double getAnimationPeriod();
    default void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore){
        scheduler.scheduleEvent(this, Factory.createAnimationAction(this,0), this.getAnimationPeriod());
    };
    default void nextImage() {
        this.setImageIndex(this.getImageIndex() + 1);
    }
}
