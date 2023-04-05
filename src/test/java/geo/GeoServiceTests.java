package geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceTests {
    GeoServiceImpl geoService;

    @BeforeEach
    void beforeEach() {
        geoService = new GeoServiceImpl();
    }

    @AfterEach
    void afterEach() {
        geoService = null;
    }

    @ParameterizedTest
    @MethodSource("ipWithDataSource")
    public void byIp(String ip, Country expected) {


        Country actual = geoService.byIp(ip).getCountry();


        assertEquals(expected, actual);
    }

    public static Stream<Arguments> ipWithDataSource() {
        return Stream.of(Arguments.of(LOCALHOST, null),
                Arguments.of(MOSCOW_IP, Country.RUSSIA),
                Arguments.of(NEW_YORK_IP, Country.USA),
                Arguments.of("172.", Country.RUSSIA),
                Arguments.of("96.", Country.USA));
    }

    @Test
    void byCoordinatesTest() {
        double latitude = 22.1, longitude = 21.4;
        Executable executable = () -> geoService.byCoordinates(latitude, longitude);

        Assertions.assertThrows(RuntimeException.class, executable);

    }
}

