package i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static ru.netology.entity.Country.*;

public class LocalizationServiceTest {

    LocalizationServiceImpl localizationService;

    @BeforeEach
    void beforeEach() {
        localizationService = new LocalizationServiceImpl();
    }

    @AfterEach
    void afterEach() {
        localizationService = null;
    }

    @ParameterizedTest
    @MethodSource("localeWithDataSource")
    public void locale(Country country, String expected) {

        //when
        String actual = localizationService.locale(country);

        //then
        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> localeWithDataSource() {
        return Stream.of(Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(USA, "Welcome"),
                Arguments.of(GERMANY, "Welcome"),
                Arguments.of(BRAZIL, "Welcome"));

    }

}
