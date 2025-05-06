package no.ntnu.idata2900.project.esg_module_backend.utils;

import java.util.List;
import no.ntnu.idata2900.project.esg_module_backend.models.data_points.Position;

/**
 * The Distance class provides methods to calculate the geographical distance between two points
 * and the total distance traveled based on a list of positions.
 */
public class Distance {
    /**
     * Returns the calculated geographical distance between two individual data points.
     *
     * @param p1 The specified first position
     * @param p2 The specified second position
     * @return Calculated geographical distance between two points
     */
    public static float calculateDistance(Position p1, Position p2) {
        float lat1 = p1.getLat();
        float lon1 = p1.getLng();
        float lat2 = p2.getLat();
        float lon2 = p2.getLng();

        float theta = lon1 - lon2;

        double dist =
                Math.sin(Math.toRadians(lat1)) *
                        Math.sin(Math.toRadians(lat2)) +
                        Math.cos(Math.toRadians(lat1)) *
                                Math.cos(Math.toRadians(lat2)) *
                                Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 0.8684;

        return (float) dist;
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
