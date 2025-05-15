package no.ntnu.idata2900.project.esg_module_backend.utils;

import java.util.List;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;

/**
 * The Distance class provides methods to calculate the geographical distance between two points
 * and the total distance traveled based on a list of positions Using the Haversine formula
 */
public class Distance {
    private static final double EARCH_RADIUS_NM = 3440.064795
    ; // Radius of the Earth in nautical miles

    /**
     * Returns the calculated geographical distance between two individual data points.
     *
     * @param p1 The specified first position
     * @param p2 The specified second position
     * @return Calculated geographical distance between two points
     */
    public static float calculateDistance(Position p1, Position p2) {
        double lat1 = Math.toRadians(p1.getLat());
        double lon1 = Math.toRadians(p1.getLng());
        double lat2 = Math.toRadians(p2.getLat());
        double lon2 = Math.toRadians(p2.getLng());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARCH_RADIUS_NM * c;

        return (float) distance;
    }

    /**
     * Returns the calculated total distance traveled during the trip. This is done by calculating
     * the distance between each data point and adding them together.
     *
     * @return Calculated total distance traveled
     */
    public float calculateTotalDistance(List<Position> positions) {
        float distance = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            distance += calculateDistance(positions.get(i), positions.get(i + 1));
        }
        return distance;
    }
}
