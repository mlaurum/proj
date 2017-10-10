
import java.util.ArrayList;


/**
 * Created by Mike on 4/14/2016.
 */
public class QuadTree  {
    private Node root;

    public QuadTree(String file) {
        root = new Node(MapServer.ROOT_ULLON, MapServer.ROOT_ULLAT,
                MapServer.ROOT_LRLON, MapServer.ROOT_LRLAT, file);

        root.NW = new Node(root.ullon, root.ullat,
                (root.ullon + root.lrlon) / 2, (root.ullat + root.lrlat) / 2, "1");
        root.NW.depth = root.depth + 1;

        root.NE = new Node((root.lrlon + root.ullon) / 2, root.ullat, root.lrlon,
                (root.ullat + root.lrlat) / 2, "2");
        root.NE.depth = root.depth + 1;

        root.SE = new Node(root.ullon, (root.lrlat + root.ullat) / 2, (root.lrlon + root.ullon) / 2,
                root.lrlat, "3");
        root.SE.depth = root.depth + 1;

        root.SW = new Node((root.ullon + root.lrlon) / 2, (root.ullat + root.lrlat) / 2, root.lrlon,
                root.lrlat, "4");
        root.SW.depth = root.depth + 1;

        makeChild(root.NW, "1");
        makeChild(root.NE, "2");
        makeChild(root.SE, "3");
        makeChild(root.SW, "4");
        /*File read = new File("img");
        System.out.println(read.list());
        for(String j:read.list()){
            System.out.println(j);
            insert(j);
        }*/


    }

    public void makeChild(Node rooty, String name) {

        //check for biggest file name
        if (rooty.depth == 7) {
            return;
        }
        if (rooty.imageTitle.equals("")) {
            name = "";
        }
        for (int i = 1; i <= 4; i++) {
            if (i == 1) {
               // name += Integer.toString(i);
                rooty.NW = new Node(rooty.ullon, rooty.ullat, (rooty.ullon + rooty.lrlon) / 2,
                        (rooty.ullat + rooty.lrlat) / 2, name + Integer.toString(i));
                rooty.NW.depth = rooty.depth + 1;
                makeChild(rooty.NW, name + Integer.toString(i));
            } else if (i == 2) {
               // name += Integer.toString(i);
                rooty.NE = new Node((rooty.lrlon + rooty.ullon) / 2, rooty.ullat, rooty.lrlon,
                        (rooty.ullat + rooty.lrlat) / 2, name + Integer.toString(i));
                rooty.NE.depth = 1 + rooty.depth;
                makeChild(rooty.NE, name + Integer.toString(i));
            } else if (i == 3) {
               // name += Integer.toString(i);
                rooty.SE = new Node(rooty.ullon, (rooty.lrlat + rooty.ullat) / 2,
                        (rooty.lrlon + rooty.ullon) / 2, rooty.lrlat, name + Integer.toString(i));
                rooty.SE.depth = 1 + rooty.depth;
                makeChild(rooty.SE, name + Integer.toString(i));
            } else {
               // name += Integer.toString(i);
                rooty.SW = new Node((rooty.ullon + rooty.lrlon) / 2,
                        (rooty.ullat + rooty.lrlat) / 2, rooty.lrlon, rooty.lrlat,
                        name + Integer.toString(i));
                rooty.SW.depth = 1 + rooty.depth;
                makeChild(rooty.SW, name + Integer.toString(i));
            }
        }


    }
    

    public Node query(double ullon, double ullat, double lrlon, double lrlat, double w, double h) {
        return query(this.root, ullon, ullat, lrlon, lrlat, w, h);
    }

    public Node query(Node rootOf, double ullon, double ullat, double lrlon, double lrlat,
                      double w, double h) {
        /*if (root == null){
            return;
        }*/
        Node nw = rootOf.NW;
        Node ne = rootOf.NE;
        Node sw = rootOf.SW;
        Node se = rootOf.SE;

        if (rootOf.overlap(ullon, ullat, lrlon, lrlat)
                && rootOf.isLeafOrDepth(Math.abs(ullon - lrlon) / w)) { //root.ulon
            return rootOf;
        }

        //37.851677914681716
         //       -122.2701916031687
        if (nw.overlap(ullon, ullat, lrlon, lrlat) &&  nw.isLeafOrDepth(
                Math.abs(ullon - lrlon) / w)) {
            return nw;
        } else if (ne.overlap(ullon, ullat, lrlon, lrlat) &&  ne.isLeafOrDepth(Math.abs(
                ullon - lrlon) / w)) {
            return ne;
        } else if (se.overlap(ullon, ullat, lrlon, lrlat) &&  se.isLeafOrDepth(Math.abs(
                ullon - lrlon) / w)) {
            return se;
        } else if (sw.overlap(ullon, ullat, lrlon, lrlat) &&  sw.isLeafOrDepth(Math.abs(
                ullon - lrlon) / w)) {
            return sw;
        }

        if (nw.overlap(ullon, ullat, lrlon, lrlat) &&  !nw.isLeafOrDepth(
                Math.abs(ullon - lrlon) / w)) {
            return query(nw, ullon, ullat, lrlon, lrlat, w, h);
        } else if (ne.overlap(ullon, ullat, lrlon, lrlat) && !ne.isLeafOrDepth(
                Math.abs(ullon - lrlon) / w)) {
            return query(ne, ullon, ullat, lrlon, lrlat, w, h);
        } else if (se.overlap(ullon, ullat, lrlon, lrlat) &&  !se.isLeafOrDepth(
                Math.abs(ullon - lrlon) / w)) {
            return query(se, ullon, ullat, lrlon, lrlat, w, h);
        } else if (sw.overlap(ullon, ullat, lrlon, lrlat) &&  !sw.isLeafOrDepth(
                Math.abs(ullon - lrlon) / w)) {
            return query(sw, ullon, ullat, lrlon, lrlat, w, h);
        }

        return rootOf;
    }

    public int calculateWidth(Node targ, double lrlon) {
        double distanceToCover = Math.abs(targ.lrlon - lrlon);
        int i = 1;
        while (distanceToCover > 0) {
            distanceToCover -= targ.w;
            i++;
        }
        return i;
    }

    public int calculateHeigth(Node targ, double lrlat) {
        double distanceToCover = Math.abs(targ.lrlat - lrlat);
        //System.out.println("disttocov: " + distanceToCover);
        int i = 1;
        if (distanceToCover - targ.h < 0) {
            return i;
        }
        while (distanceToCover > 0) {
            //System.out.println("disttocov: " + distanceToCover);
            distanceToCover -= targ.h;

            i++;
        }

        return i;
    }
    public ArrayList<Node> rasterIm(double ullon, double ullat, double lrlon,
                                    double lrlat, double w, double h) {
        ArrayList<Node> store = new ArrayList<>();
        Node target = query(ullon, ullat, lrlon, lrlat, w, h);
        //store.add(target);
        double ullon1 = ullon;
        double ullat1 = ullat;
        double lrlat1 = lrlat;
        double lrlon1 = lrlon;
        double wTI = Math.abs(target.ullon - target.lrlon);
        double hTI = Math.abs(target.ullat - target.lrlat);
        int numw = calculateWidth(target, lrlon);
        int numh = calculateHeigth(target, lrlat);
        int width = 1;
        int height = 1;

        //System.out.println("numw: " + numw);
        //System.out.println("numh: " + numh);

        for (int i = 0; i < numh; i++) { //(int) (Math.round(w/wt)* Math.round(w/wt))
            for (int j = 0; j < numw; j++) {
                store.add(query(ullon1, ullat1, lrlon1, lrlat1, w, h));
                ullon1 += wTI;
                lrlon1 += wTI;
                width++;
            }
            ullon1 = ullon;
            lrlon1 = lrlon;
            ullat1 -= hTI;
            lrlat1 -= hTI;
            height++;

        }

        store.add(new Node(numw, 0, 0, 0, ""));
        store.add(new Node(numh, 0, 0, 0, ""));
        return store;
    }

    /*public static void main(String args[]) {
        QuadTree j = new QuadTree("root.png");
    }*/

}
