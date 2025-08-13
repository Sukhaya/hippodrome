import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    //Проверить, что метод выполняется не дольше 22 секунд
    
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Отключено для ускорения тестов, включить при ручном прогоне")
    public void main() throws Exception {
        Main.main(new String[]{});
        System.out.println("Main метод отработал успешно");
    }
}
