import java.util.HashSet;

/**
 * Created by Mike on 4/17/2016.
 */
public class GraphNode implements Comparable<GraphNode> {
    private String name;
    private String tag;
    private double nodeID;
    private double lat;
    private double lon;
    private double version;
    private HashSet<GraphNode> neighbors;
    private double distToStart;
    private double priority;
    private GraphNode parent;

    public String name() {
        return name;
    }

    public String tag() {
        return tag;
    }

    public double nodeID() {
        return nodeID;
    }

    public double lat() {
        return lat;
    }

    public double lon() {
        return lon;
    }

    public double version() {
        return version;
    }

    public HashSet<GraphNode> neighbors() {
        return neighbors;
    }

    public double distToStart() {
        return distToStart;
    }

    public double priority() {
        return priority;
    }

    public GraphNode parent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setNodeID(double nodeID) {
        this.nodeID = nodeID;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public void setNeighbors(HashSet<GraphNode> neighbors) {
        this.neighbors = neighbors;
    }

    public void setDistToStart(double distToStart) {
        this.distToStart = distToStart;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GraphNode graphNode = (GraphNode) o;

        if (Double.compare(graphNode.nodeID, nodeID) != 0) {
            return false;
        }
        if (Double.compare(graphNode.lat, lat) != 0) {
            return false;
        }
        if (Double.compare(graphNode.lon, lon) != 0) {
            return false;
        }
        return name.equals(graphNode.name);

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(nodeID);
        return (int) (temp ^ (temp >>> 32));
    }

    public GraphNode() {
        name = "";
        tag = "";
        nodeID = 0;
        lat = 0;
        lon = 0;
        version = 0;
        neighbors = new HashSet<>();

        parent = null;

    }

    public double euclidianDist(double lat1, double lon2) {
        double l1 = Math.abs(lat1 - this.lat);
        double l2 = Math.abs(lon2 - this.lon);
        return Math.sqrt(l1 * l1 + l2 * l2);
    }

    public double euclidianDist(GraphNode k) {
        double l1 = Math.abs(k.lat - this.lat);
        double l2 = Math.abs(k.lon - this.lon);
        return Math.sqrt(l1 * l1 + l2 * l2);
    }

    public int compareTo(GraphNode j) {
        if (this.priority < j.priority) {
            return -1;
        }
        if (this.priority > j.priority) {
            return 1;
        }
        return 0;
    }
}
