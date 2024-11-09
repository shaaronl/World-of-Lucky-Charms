import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class AStarPathingStrategy
        implements PathingStrategy {

    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors) {
        List<Point> path = new LinkedList<Point>(); // recursively add to this


        Comparator<Node> nodeComparator = Comparator.comparingInt(Node::getFVal);
        PriorityQueue<Node> openList = new PriorityQueue<>(nodeComparator);
        // need to use comparator, sort by smallest F value
        HashMap<Point, Integer> openGvals = new HashMap<Point, Integer>(); // get G vals o(1)
        HashSet<Point> closedList = new HashSet<Point>();


        // add start node to the open list and mark as the current node
        Node curNode = new Node(start, 0, calculateManhattanDistance(start, end),
                calculateManhattanDistance(start, end), null);
        openList.add(curNode); // add start (step 2)
        openGvals.put(curNode.point, curNode.gVal); // add start (step 2)
        while (!openList.isEmpty() && !withinReach.test(curNode.point, end)) {
            Predicate<Point> inClosedList = closedList::contains;
            List<Point> Neighbors = potentialNeighbors.apply(curNode.point)
                    .filter(canPassThrough) // not an obstacle
                    .filter(inClosedList.negate()) // not in the closedList (negate flips condition)
                    .collect(Collectors.toList());
            // analyze all valid adjacent nodes that are not on the closed list
            for (Point adjPoint : Neighbors)
            {
                Node adjNode = new Node(adjPoint, curNode.gVal + 1, 0, 0, curNode);
                if (openGvals.containsKey(adjPoint)) // step 3b
                {
                    if (adjNode.gVal >= openGvals.get(adjPoint)) // not a better g val
                        continue; // moves to next adjNode
                    else { // found better g val
                        openGvals.replace(adjPoint, adjNode.gVal); // replace old gval w/ new
                        for (Node node : openList) { // o(n) ;( is there a better way?
                            if (node.point.equals(adjPoint)) {
                                openList.remove(node);
                                break; // removes reoccurring point from oList
                            }
                        }
                    }
                }
                adjNode.hVal = calculateManhattanDistance(adjPoint,end); // set h val
                adjNode.fVal = adjNode.gVal + adjNode.hVal; // set f val
                openList.add(adjNode); // adding adj node to openList
                openGvals.put(adjPoint, adjNode.gVal); // adding gval for o(1) lookup
            }
            closedList.add(curNode.point); // putting the point in closed list
            openList.remove(curNode); // removing it from openList
            openGvals.remove(curNode.point); // removing the g val bc its closed now
            if (!openList.isEmpty())
                curNode = openList.peek(); // sorted by smallest f vals so just dequeue(FIFO)
            else
                return path; // no paths exist
        }
        // backtrack from the curNode to the start node
        getPath(curNode, path);
        path.remove(start);
        return path;
    }


    private List<Point> getPath(Node cur, List<Point> path) {
        if (cur.prev != null) {
            getPath(cur.prev, path);
        }
        path.add(cur.point);
        return path;
    }


    private int calculateManhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }


    // Inner class.  Private data can be accessed by outer class
    private class Node {
        private Point point;
        private int gVal, fVal, hVal;
        private Node prev;
        public Node(Point p, int gVal, int fVal, int hVal, Node prev) {
            this.point = p;
            this.fVal = fVal;
            this.gVal = gVal;
            this.hVal = hVal;
            this.prev = prev;
        }
        public int getFVal() {
            return fVal;
        } // used in comparator


    }

}

