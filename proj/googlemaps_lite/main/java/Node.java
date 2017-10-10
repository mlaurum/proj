/**
 * Created by Mike on 4/14/2016.
 */
public class Node {
    double ullon;
    double ullat;
    double lrlon;
    double lrlat;
    double w;
    double h;
    Node SW;
    Node SE;
    Node NW;
    Node NE;
    int depth = 0;
    String imageTitle;

    public Node(double ullon, double ullat, double lrlon, double lrlat, String imageTitle) {
        this.ullon = ullon;
        this.ullat = ullat;
        this.lrlon = lrlon;
        this.lrlat = lrlat;
        this.imageTitle = imageTitle + ".png";
        w = Math.abs(ullon - lrlon);
        h = Math.abs(ullat - lrlat);
    }

    public boolean overlap(double ullo, double ulla, double lrlo, double lrla) {
        /*if( (ullon < ullo && ullo < lrlon) && (ullat > ulla && ulla > lrlat)){
            return true;
        } else if((ullon < lrlo && lrlo < lrlon) && (ullat > lrla && lrla > lrlat) ){
            return true;
        }*/

        return !(ullon >= lrlo || lrlon <= ullo || ullat <= lrla || lrlat >= ulla);
    }

    public boolean isLeafOrDepth(double dpp) {
        if (depth == 7) {
            return true;
        } else if (dpp >= (Math.abs(ullon - lrlon) / 256.0)) {
            return true;
        }
        return false;

    }
}
