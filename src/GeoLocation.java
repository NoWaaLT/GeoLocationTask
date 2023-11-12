package Task1;

import java.util.Random;

public class GeoLocation {

    static int numLocations = 0;

    private double lat, lon;

    Random rand = new Random();

    GeoLocation() {
        this.lat = Math.round((-90 + (90 + 90) * rand.nextDouble()) * 1000000) / 1000000.0;
        this.lon = Math.round((-90 + (90 + 90) * rand.nextDouble()) * 1000000) / 1000000.0;
        numLocations++;
    }

    GeoLocation(double pLat, double pLon) {
        this.lat = pLat;
        this.lon = pLon;
        numLocations++;
    }

    GeoLocation(GeoLocation pGeo) {
        this.lat = Math.round((pGeo.lat + (-0.1 + (0.1 - (-0.1)) * rand.nextDouble())) * 1000000) / 1000000.0;
        this.lon = Math.round((pGeo.lon + (-0.1 + (0.1 - (-0.1)) * rand.nextDouble())) * 1000000) / 1000000.0;
        numLocations++;
    }


    public void print() {
        System.out.println("[" + this.lat + ", " + this.lon + "]");
    }


    static public double distance(GeoLocation gLoc1, GeoLocation gLoc2) {
        double dLat = Math.toRadians(gLoc2.lat - gLoc1.lat);
        double dLon = Math.toRadians(gLoc2.lon - gLoc1.lon);

        double lat1 = Math.toRadians(gLoc1.lat);
        double lat2 = Math.toRadians(gLoc2.lat);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.pow(Math.sin(dLon / 2), 2) *
                   Math.cos(lat1) *
                   Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return Math.round((rad * c) * 100) / 100.0;
    }

    public static void main(String[] args) {
        GeoLocation someLocation = new GeoLocation();
        GeoLocation vilnius = new GeoLocation(54.683333, 25.283333);
        GeoLocation kaunas = new GeoLocation(54.897222, 23.886111);
        GeoLocation nearVilnius = new GeoLocation(vilnius);

        someLocation.print();
        vilnius.print();
        nearVilnius.print();

        System.out.println("Number of locations: " + GeoLocation.numLocations);

        System.out.println("From Vilnius to Kaunas: " + GeoLocation.distance(vilnius, kaunas));
        System.out.println("From Vilnius to random location: " + GeoLocation.distance(vilnius, someLocation));
        System.out.println("From Vilnius to random neighbour: " + GeoLocation.distance(vilnius, nearVilnius));

    }
}
