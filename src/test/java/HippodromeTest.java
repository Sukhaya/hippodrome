import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    //Конструктор
    //Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение "Horses cannot be null.";
    //Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение "Horses cannot be empty.";

    //метод getHorses
    //Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор.

    //метод move
    //Проверить, что метод вызывает метод move у всех лошадей.

    //метод getWinner
    //Проверить, что метод возвращает лошадь с самым большим значением distance.

    private static final String NULL_CONSTRUCTOR_EXCEPTION_MESSAGE = "Horses cannot be null.";
    private static final String EMPTY_CONSTRUCTOR_EXCEPTION_MESSAGE = "Horses cannot be empty.";

    @Test
    public void null_Constructor_ThrowsIllegalArgumentExceptionTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );

        assertEquals(NULL_CONSTRUCTOR_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void empty_ListConstructor_ThrowsIllegalArgumentExceptionTest() {
        List<Horse> emptyList = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(emptyList)
        );

        assertEquals(EMPTY_CONSTRUCTOR_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void getHorsesTest() {
        int horsesNumber = 30;
        List<Horse> horses = createHorsesList(horsesNumber);

        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> hippodromeHorses = hippodrome.getHorses();

        assertEquals(horses, hippodromeHorses, "Списки лошадей разные");
    }

    @Test
    public void moveTest() {
        int horsesNumber = 50;
        List<Horse> horses = createMockHorsesList(horsesNumber);

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = Arrays.asList(
                new Horse("Horse1", 3.0, 1.0),
                new Horse("Horse2", 5.0, 1.0),
                new Horse("Horse3", 2.0, 1.0)
        );

        Horse winnerHorse = horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .orElse(null);
        System.out.println(winnerHorse.getName());

        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();
        System.out.println(winner.getName());
        assertEquals(winnerHorse, winner, "Победитель определяется не по условию");
    }

    private List<Horse> createHorsesList(int count) {
        List<Horse> horses = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String horseName = "Horse #" + i;
            double speed = random.nextDouble();
            horses.add(new Horse(horseName, speed));
        }
        return horses;
    }

    private List<Horse> createMockHorsesList(int count) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        return horses;
    }
}
