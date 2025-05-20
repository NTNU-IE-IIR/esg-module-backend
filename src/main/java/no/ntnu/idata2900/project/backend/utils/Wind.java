package no.ntnu.idata2900.project.backend.utils;

/**
 * The Wind class represents the utility class for various wind data.
 *
 * @author Group 14
 * @version v0.1.0 (2025.05.02)
 */
public class Wind {
  /**
   * Constructor for the Wind class. This constructor should never be called and does for this
   * reason contain the <code>private</code> keyword.
   */
  private Wind() {
    // Intentionally left blank
  }

  /**
   * Calculates wind direction based on the specifed vector defined by <code>u</code> and
   * <code>v</code>.
   *
   * @param windU The specified <code>u</code>
   * @param windV The specified <code>v</code>
   * @return Wind direction baed on wind vector
   */
  public static float calculateWindDirection(float windU, float windV) {
    // Reference vector
    float refU = 0.0f;
    float refV = 1.0f;

    double cos = (refU * windU + refV * windV) / (
        Math.sqrt(Math.pow(refU, 2) + Math.pow(refV, 2))
        * Math.sqrt(Math.pow(windU, 2) + Math.pow(windV, 2))
    );
    double ang = Math.acos(cos);

    if (windU < 0) {
      ang = 360.0 - ang;
    }

    return (float) ang;
  }
}
