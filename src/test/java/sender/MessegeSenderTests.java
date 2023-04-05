package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;

import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessegeSenderTests {
    @Test
    void sendTest(){
        String excepted = "Добро пожаловать";
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER,MOSCOW_IP);

        String actual = messageSender.send(headers);

        Assertions.assertEquals(excepted,actual);
    }
}
