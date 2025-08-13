import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    //конструктор
    //Проверить, что при передаче в конструктор первым параметром null, будет выброшено IllegalArgumentException.
    //Проверить, что при передаче в конструктор первым параметром null, выброшенное исключение будет содержать сообщение "Name cannot be null.".
    //Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), будет выброшено IllegalArgumentException.
    //Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), выброшенное исключение будет содержать сообщение "Name cannot be blank.";
    //Проверить, что при передаче в конструктор вторым параметром отрицательного числа, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор вторым параметром отрицательного числа, выброшенное исключение будет содержать сообщение "Speed cannot be negative.";
    //Проверить, что при передаче в конструктор третьим параметром отрицательного числа, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор третьим параметром отрицательного числа, выброшенное исключение будет содержать сообщение "Distance cannot be negative.";

    //метод getName
    //Проверить, что метод возвращает строку, которая была передана первым параметром в конструктор;

    //метод getSpeed
    //Проверить, что метод возвращает число, которое было передано вторым параметром в конструктор;

    //метод getDistance
    //Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
    //Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;

    //метод move
    //Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9
    //Проверить, что метод присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9)

    private static final String NULL_NAME_EXCEPTION_MESSAGE = "Name cannot be null.";
    private static final String EMPTY_NAME_EXCEPTION_MESSAGE = "Name cannot be blank.";
    private static final String NEGATIVE_SPEED_EXCEPTION_MESSAGE = "Speed cannot be negative.";
    private static final String NEGATIVE_DISTANCE_EXCEPTION_MESSAGE = "Distance cannot be negative.";

    @Test
    public void null_Name_Constructor_ThrowsIllegalArgumentExceptionTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 2.8, 5.0)
        );

        assertEquals(NULL_NAME_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\t", "\f", "\r"})
    public void empty_Name_Constructor_ThrowsIllegalArgumentExceptionTest(String argument) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(argument, 2.8, 5.0)
        );

        assertEquals(EMPTY_NAME_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void negative_Speed_Constructor_ThrowsIllegalArgumentExceptionTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Irma", -1.9, 5.0)
        );

        assertEquals(NEGATIVE_SPEED_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void negative_Distance_Constructor_ThrowsIllegalArgumentExceptionTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Irma", 1.0, -5.3)
        );

        assertEquals(NEGATIVE_DISTANCE_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void getNameTest() {
        Horse horse = new Horse("Irma", 2.8);
        assertEquals("Irma", horse.getName(),
                "Имя не совпадает");
    }

    @Test
    public void getSpeedTest() {
        Horse horse = new Horse("Irma", 1.2);
        assertEquals(1.2, horse.getSpeed(),
                "Скорость не совпадает");
    }

    @Test
    public void getDistanceTest() {
        Horse horse = new Horse("Irma", 2.2, 3.4);
        assertEquals(3.4, horse.getDistance(),
                "Дистанция не совпадает");
    }

    @Test
    public void zeroDistanceTest() {
        Horse horse = new Horse("Irma", 2.2);
        assertEquals(0, horse.getDistance(),
                "Дистанция не совпадает");
    }

    @Test
    public void moveTest() {
        try(MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Irma", 2.2, 3.4);
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();

            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
                    "0.0, 1.0, 0.2, 0.2",
                    "3.0, 2.0, 0.5, 4.0",
                    "5.0, 3.0, 0.9, 7.7"
            })
    public void moveDistanceUpdateTest(double distance, double speed, double randomValue, double expectedDistance) {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Irma", speed, distance);
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.0001,
                    "Расстояние задается не по формуле: distance + speed * getRandomDouble(0.2, 0.9)");
        }
    }
}
