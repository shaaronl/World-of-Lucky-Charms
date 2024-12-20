/**
 * An event is made up of an Entity that is taking an
 * Action a specified time.
 */
public final class Event {
    private final Action action;
    private final double time;
    private final Entity entity;

    public Event(Action action, double time, Entity entity) {
        this.action = action;
        this.time = time;
        this.entity = entity;
    }

    public double getTime() {
        return time;
    }
    public Entity getEntity() {
        return entity;
    }

    public Action getAction() {
        return action;
    }
}
