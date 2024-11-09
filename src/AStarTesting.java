import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class AStarTesting {

    @Test
    public void testAStarStep(){
        boolean [][] grid = {
                {true, true, true},
                {true, true, true},
                {true, true, true},
        };
        PathingStrategy strat = new AStarPathingStrategy();
        WorldModel world = new WorldModel();
        Predicate<Point> canPassThrough = p -> true;
        BiPredicate<Point, Point> withinReach = Point::adjacent;
        List<Point> path = strat.computePath(
                new Point(0, 0),
                new Point(2, 2),
                canPassThrough,
                withinReach,
                PathingStrategy.CARDINAL_NEIGHBORS
        );
        assertEquals(3, path.size());
    }

}
