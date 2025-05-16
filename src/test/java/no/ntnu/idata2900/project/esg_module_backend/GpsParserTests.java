package no.ntnu.idata2900.project.esg_module_backend;

import static org.junit.jupiter.api.Assertions.*;

import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.sentence.GLLSentence;
import net.sf.marineapi.nmea.sentence.RMCSentence;
import net.sf.marineapi.nmea.sentence.VTGSentence;
import net.sf.marineapi.nmea.sentence.ZDASentence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class GpsParserTests {

    @Test
	void testParseGLLString() {
        String nmea = "$GPGLL,6136.1889,N,00502.6740,E,114725,A,A*4B";
        SentenceFactory sf = SentenceFactory.getInstance();
        GLLSentence gll = (GLLSentence) sf.createParser(nmea);

        assertTrue(gll.isValid());
        assertEquals(61.6031483, gll.getPosition().getLatitude(), 0.0001);
        assertEquals(5.0445667, gll.getPosition().getLongitude(), 0.0001);
	}


  @Test
  void testParseRMCString() {
    String nmea = "$GPRMC,114725,A,6136.1889,N,00502.6740,E,0.0,309.5,080424,1.3,E,A*10";
    SentenceFactory sf = SentenceFactory.getInstance();
    RMCSentence rmc = (RMCSentence) sf.createParser(nmea);

    assertTrue(rmc.isValid());
    assertEquals(61.6031483, rmc.getPosition().getLatitude(), 0.0001);
    assertEquals(5.0445667, rmc.getPosition().getLongitude(), 0.0001);
  }


  @Test
  void testParseVTGString() {
    String nmea = "$GPVTG,313.0,T,311.7,M,0.0,N,0.0,K,A*26";
    SentenceFactory sf = SentenceFactory.getInstance();
    VTGSentence vtg = (VTGSentence) sf.createParser(nmea);

    assertTrue(vtg.isValid());
    assertEquals(313.0, vtg.getTrueCourse(), 0.0001);
    assertEquals(311.7, vtg.getMagneticCourse(), 0.0001);
  }


  @Test
  void testParseZDAString() {
    String nmea = "$GPZDA,114726,08,04,2024,-01,00*6B";
    SentenceFactory sf = SentenceFactory.getInstance();
    ZDASentence zda = (ZDASentence) sf.createParser(nmea);

    assertTrue(zda.isValid());
    assertEquals(11, zda.getTime().getHour());
    assertEquals(47, zda.getTime().getMinutes());
  }


  @Test
  void testParseGGAString() {
    String nmea = "$GPGGA,114726,6136.1889,N,00502.6740,E,1,11,1.4,74,M,,M,,*7B";
    SentenceFactory sf = SentenceFactory.getInstance();
    GGASentence gga = (GGASentence) sf.createParser(nmea);

    assertTrue(gga.isValid());
    assertEquals(61.6031483, gga.getPosition().getLatitude(), 0.0001);
    assertEquals(5.0445667, gga.getPosition().getLongitude(), 0.0001);
  }
}
