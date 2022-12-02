package a1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PhDTest {
    @Test
    public void testConstructor1() {
        PhD testPhD= new PhD("Zane", 2022, 12);
        assertEquals("Zane", testPhD.name());
        assertEquals("12/2022", testPhD.date());
        assertEquals(null, testPhD.advisor1());
        assertEquals(null, testPhD.advisor2());
        assertEquals(0, testPhD.advisees());
    }

    @Test
    public void testMutators() {
        PhD testPhD= new PhD("Aldin", 2022, 2);
        PhD testAdvisor1= new PhD("advisorOne", 2000, 5);
        PhD testAdvisor2= new PhD("advisorTwo", 1990, 7);
        testPhD.addAdvisor1(testAdvisor1);
        assertEquals(testAdvisor1, testPhD.advisor1());
        assertEquals(null, testPhD.advisor2());
        assertEquals(1, testAdvisor1.advisees());
        assertEquals(0, testAdvisor2.advisees());
        testPhD.addAdvisor2(testAdvisor2);
        assertEquals(testAdvisor1, testPhD.advisor1());
        assertEquals(testAdvisor2, testPhD.advisor2());
        assertEquals(1, testAdvisor1.advisees());
        assertEquals(1, testAdvisor2.advisees());
        testAdvisor1.addAdvisor1(testAdvisor2);
        assertEquals(2, testAdvisor2.advisees());
        assertEquals(testAdvisor2, testAdvisor1.advisor1());
        assertEquals(null, testAdvisor1.advisor2());
    }

    @Test
    public void testConstructor2() {
        PhD testAdvisor1= new PhD("advisorOne", 2000, 5);
        PhD testAdvisor2= new PhD("advisorTwo", 1990, 7);
        PhD testPhD= new PhD("Aldin", 2022, 2, testAdvisor1, testAdvisor2);
        assertEquals("Aldin", testPhD.name());
        assertEquals("2/2022", testPhD.date());
        assertEquals(testAdvisor1, testPhD.advisor1());
        assertEquals(testAdvisor2, testPhD.advisor2());
        assertEquals(0, testPhD.advisees());
        assertEquals(1, testAdvisor1.advisees());
    }

    @Test
    public void testFunctions() {
        PhD advisor1= new PhD("testAdvisor1", 2000, 5);
        PhD advisor2= new PhD("testAdvisor2", 1990, 7);
        PhD advisor3= new PhD("testAdvisor3", 1965, 3);
        PhD advisor4= new PhD("testAdvisor4", 2001, 4);
        PhD jan2022= new PhD("PhD1", 2022, 1, advisor1, advisor2);
        PhD jan2022_1= new PhD("PhD4", 2022, 1, advisor1, advisor3);
        PhD aug2022= new PhD("PhD2", 2022, 8, advisor1, advisor3);
        PhD jan2023= new PhD("PhD3", 2023, 1, advisor3, advisor4);
        PhD dec2024= new PhD("PhD5", 2024, 12);
        PhD jan2025= new PhD("PhD6", 2025, 1);
        PhD jan30000= new PhD("PhD7", 30000, 1);
        PhD jan2022_2= jan2022;
        assertEquals(false, jan2022.gotBefore(null));
        assertEquals(true, jan2022.gotBefore(aug2022));
        assertEquals(false, aug2022.gotBefore(jan2022));
        assertEquals(true, jan2022.gotBefore(jan2023));
        assertEquals(false, jan2023.gotBefore(jan2022));
        assertEquals(true, aug2022.gotBefore(jan2023));
        assertEquals(false, jan2023.gotBefore(aug2022));
        assertEquals(false, jan2022_1.gotBefore(jan2022));
        assertEquals(true, jan2022.gotBefore(jan30000));
        assertEquals(false, jan2022.isSiblingOf(jan2022_2));
        assertEquals(true, jan2022.isSiblingOf(aug2022));
        assertEquals(false, jan2022.isSiblingOf(jan2023));
        assertEquals(true, jan2022_1.isSiblingOf(aug2022));
        assertEquals(false, dec2024.isSiblingOf(jan2025));
    }

    @Test
    void assertTests() {
        // constructor1
        assertThrows(AssertionError.class, () -> { new PhD("a", 2020, 1); });
        assertThrows(AssertionError.class, () -> { new PhD("", 2020, 1); });
        assertThrows(AssertionError.class, () -> { new PhD("ab", 2020, 0); });
        assertThrows(AssertionError.class, () -> { new PhD("a", 2020, 13); });
        // adding advisors
        PhD advisor1= new PhD("testAdvisor1", 2000, 5);
        PhD advisor2= new PhD("testAdvisor2", 1990, 7);
        PhD test1= new PhD("Aldin", 2020, 2, advisor1, advisor2);
        PhD test2= new PhD("Aldin", 2020, 2);
        assertThrows(AssertionError.class, () -> { test2.addAdvisor1(null); });
        assertThrows(AssertionError.class, () -> { test2.addAdvisor2(null); });
        assertThrows(AssertionError.class, () -> { test1.addAdvisor1(advisor1); });
        assertThrows(AssertionError.class, () -> { test2.addAdvisor2(advisor1); });
        test2.addAdvisor1(advisor1);
        assertThrows(AssertionError.class, () -> { test2.addAdvisor2(advisor1); });
        assertThrows(AssertionError.class, () -> { test1.addAdvisor2(advisor1); });
        // constructor2
        assertThrows(AssertionError.class, () -> { new PhD("a", 2020, 1, advisor1, advisor2); });
        assertThrows(AssertionError.class, () -> { new PhD("", 2020, 1, advisor1, advisor2); });
        assertThrows(AssertionError.class, () -> { new PhD("ab", 2020, 0, advisor1, advisor2); });
        assertThrows(AssertionError.class, () -> { new PhD("ab", 2020, 13, advisor1, advisor2); });
        assertThrows(AssertionError.class, () -> { new PhD("ab", 2020, 1, null, advisor2); });
        assertThrows(AssertionError.class, () -> { new PhD("ab", 2020, 1, advisor1, null); });
        assertThrows(AssertionError.class, () -> { new PhD("ab", 2020, 1, advisor1, advisor1); });
        // boolean function test
        assertThrows(AssertionError.class, () -> { test1.isSiblingOf(null); });
    }
}
