import javax.swing.text.Position;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class MovingEntity implements Entity {
    protected abstract Class _getHouseOrStump();

    public Point nextPosition(WorldModel world, Point destPos) {
      //PathingStrategy strat = new SingleStepPathingStrategy();
      AStarPathingStrategy strat = new AStarPathingStrategy();
        Predicate<Point> canPassThrough =
                (p) -> world.withinBounds(p) &&(!(world.isOccupied(p) &&
                        world.getOccupancyCell(p).getClass() != _getHouseOrStump()));
        BiPredicate<Point, Point> withinReach = Point::adjacent;

        List<Point> path = strat.computePath(this.getPosition(),
                destPos,
                canPassThrough,withinReach,SingleStepPathingStrategy.CARDINAL_NEIGHBORS);

        if(path.size() == 0){
            return this.getPosition();// no path
        }
        return path.get(0);

    }

    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            return true;
        } else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

}