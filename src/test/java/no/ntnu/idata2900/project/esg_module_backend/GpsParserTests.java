package no.ntnu.idata2900.project.esg_module_backend;

import static org.junit.jupiter.api.Assertions.*;

import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.GLLSentence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GpsParserTests {

    @Test
	void testParseString() {
        String nmea = "$GPGLL,6136.1889,N,00502.6740,E,114725,A,A*4B";
        SentenceFactory sf = SentenceFactory.getInstance();
        GLLSentence gll = (GLLSentence) sf.createParser(nmea);

        assertTrue(gll.isValid());
        assertEquals(61.6031483, gll.getPosition().getLatitude(), 0.0001);
        assertEquals(5.0445667, gll.getPosition().getLongitude(), 0.0001);
	}
}
