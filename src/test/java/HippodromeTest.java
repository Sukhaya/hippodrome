import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    //Конструктор
    //Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение "Horses cannot be null.";
    //Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
    //Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение "Horses cannot be empty.";

    //метод getHorses
    //Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор.
    // При создании объекта Hippodrome передай в конструктор список из 30 разных лошадей;

    //метод move
    //Проверить, что метод вызывает метод move у всех лошадей.
    // При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify.

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
    }

    @Test
    public void moveTest() {
    }

    @Test
    public void getWinnerTest() {
    }
}
