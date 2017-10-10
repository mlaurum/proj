import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.LinkedList;

/**
 * Created by Mike on 4/18/2016.
 */
public class ASTAR {
    private GraphNode start;
    private GraphNode end;

    public ASTAR(GraphNode start, GraphNode end) {
        this.start = start;
        this.start.setDistToStart(0);
        this.start.setPriority(this.start.euclidianDist(end));
        this.end = end;
    }

    private PriorityQueue<GraphNode> sequence = new PriorityQueue<>();
    private Set<GraphNode> cache = new HashSet<>();


    public LinkedList<Long> aStar() {
        GraphNode next = start;

        sequence.add(start);

        while (!sequence.peek().equals(end)) {
            next = sequence.remove();
            cache.add(next);

            for (GraphNode j : next.neighbors()) {
                if (cache.contains(j)) {
                    continue;
                }
                double pri = next.euclidianDist(j) + next.distToStart();
                if (!sequence.contains(j)) {
                    sequence.add(j);
                }
                j.setParent(next);
                j.setDistToStart(pri);
                j.setPriority(j.euclidianDist(end) + j.distToStart());
                sequence.remove(j);
                sequence.add(j);
            }

        }
        end.setParent(next);
        return reconstruct(end);

    }

    public LinkedList reconstruct(GraphNode endy) {
        LinkedList<Long> mn = new LinkedList<>();
        while (endy != null) {
            mn.add(0, (long) endy.nodeID());
            endy = endy.parent();
        }

        return mn;
    }


}
