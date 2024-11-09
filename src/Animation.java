/**
 * An action that can be taken by an entity
 */
public final class Animation implements Action{
    //public final class AnimationEntity implements Action{
    private final AnimationEntity entity;
    private final int repeatCount;

    public Animation(AnimationEntity entity, int repeatCount) {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity, Factory.createAnimationAction(this.entity,Math.max(this.repeatCount - 1, 0)),
                    (this.entity).getAnimationPeriod());
        }
    }
}

