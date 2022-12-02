import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    void testEquals() {
        Time t1= new Time(5, 20);
        assertEquals(true, t1.equals(t1)); // compares some input and the expected output
    }

}
