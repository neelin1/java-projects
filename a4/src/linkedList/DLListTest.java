package linkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DLListTest {

    @Test
    public void testAppendAndToStringRev() {
        // empty test
        DLList<Integer> d= new DLList<>();
        assertEquals("[]", d.toString());
        assertEquals("[]", d.toStringRev());
        assertEquals(0, d.size());

        // 1 element, strings
        DLList<String> dl= new DLList<>();
        dl.append("2110");
        assertEquals("[2110]", dl.toString());
        assertEquals("[2110]", dl.toStringRev());
        assertEquals(1, dl.size());

        // 2 elements, empty strings
        DLList<String> dl1= new DLList<>();
        dl1.append("");
        assertEquals("[]", dl1.toString());
        assertEquals("[]", dl1.toStringRev());
        assertEquals(1, dl1.size());
        dl1.append("");
        assertEquals("[, ]", dl1.toString());
        assertEquals("[, ]", dl1.toStringRev());
        assertEquals(2, dl1.size());

        // 4 elements, characters
        DLList<Character> dll= new DLList<>();
        dll.append('1');
        dll.append('2');
        dll.append('3');
        dll.append('4');
        assertEquals("[1, 2, 3, 4]", dll.toString());
        assertEquals("[4, 3, 2, 1]", dll.toStringRev());
        assertEquals(4, dll.size());

        // 4 elements, multiple object types, null
        DLList<Object> dlli= new DLList<>();
        Character ch= '1';
        dlli.append(ch);
        dlli.append('2');
        dlli.append(3);
        dlli.append("four");
        assertEquals("[1, 2, 3, four]", dlli.toString());
        assertEquals("[four, 3, 2, 1]", dlli.toStringRev());
        assertEquals(4, dlli.size());
        dlli.append(null);
        assertEquals("[1, 2, 3, four, null]", dlli.toString());
        assertEquals("[null, four, 3, 2, 1]", dlli.toStringRev());
        assertEquals(5, dlli.size());

    }

    @Test
    void testPrepend() {
        // 1 element, strings
        DLList<Integer> dl= new DLList<>();
        dl.prepend(2110);
        assertEquals("[2110]", dl.toString());
        assertEquals("[2110]", dl.toStringRev());
        assertEquals(1, dl.size());

        // 2 elements, empty strings
        DLList<String> dl1= new DLList<>();
        dl1.prepend("");
        assertEquals("[]", dl1.toString());
        assertEquals("[]", dl1.toStringRev());
        assertEquals(1, dl1.size());
        dl1.prepend("");
        assertEquals("[, ]", dl1.toString());
        assertEquals("[, ]", dl1.toStringRev());
        assertEquals(2, dl1.size());

        // 4 elements, characters
        DLList<Character> dll= new DLList<>();
        dll.prepend('1');
        dll.prepend('2');
        dll.prepend('3');
        dll.prepend('4');
        assertEquals("[4, 3, 2, 1]", dll.toString());
        assertEquals("[1, 2, 3, 4]", dll.toStringRev());
        assertEquals(4, dll.size());

        // 4 elements, multiple object types
        DLList<Object> dlli= new DLList<>();
        Character ch= '1';
        dlli.prepend(ch);
        dlli.prepend('2');
        dlli.prepend(3);
        dlli.prepend("four");
        assertEquals("[four, 3, 2, 1]", dlli.toString());
        assertEquals("[1, 2, 3, four]", dlli.toStringRev());
        assertEquals(4, dlli.size());
    }

    @Test
    void testNode() {

        // basic test with integer DLL
        DLList<Integer> dl= new DLList<>();
        dl.append(1);
        dl.append(2);
        dl.append(3);
        dl.append(4);
        dl.append(5);
        dl.append(6);
        assertEquals("[1, 2, 3, 4, 5, 6]", dl.toString());
        assertEquals("[6, 5, 4, 3, 2, 1]", dl.toStringRev());
        assertEquals(6, dl.size());
        assertEquals(1, dl.node(0).value());
        assertEquals(2, dl.node(1).value());
        assertEquals(3, dl.node(2).value());
        assertEquals(4, dl.node(3).value());
        assertEquals(5, dl.node(4).value());
        assertEquals(6, dl.node(5).value());
        assertEquals(dl.node(4), dl.node(5).prev());
        assertEquals(null, dl.node(5).next());
        assertEquals(null, dl.node(0).prev());
        // int i1= dl.node(6).value();
        // int i2= dl.node(-1).value();

        // string DLL
        DLList<String> dl1= new DLList<>();
        dl1.append("2110");
        assertEquals("[2110]", dl1.toString());
        assertEquals("[2110]", dl1.toStringRev());
        assertEquals(1, dl1.size());
        assertEquals("2110", dl1.node(0).value());

    }

    @Test
    void testInsertBefore() {

        DLList<Integer> dl= new DLList<>();
        dl.append(1);
        dl.append(2);
        dl.append(3);
        dl.append(4);
        dl.append(5);
        dl.append(6);
        assertEquals("[1, 2, 3, 4, 5, 6]", dl.toString());
        assertEquals("[6, 5, 4, 3, 2, 1]", dl.toStringRev());
        assertEquals(6, dl.size());

        // insert before head
        dl.insertBefore(dl.head(), 10);
        assertEquals("[10, 1, 2, 3, 4, 5, 6]", dl.toString());
        assertEquals("[6, 5, 4, 3, 2, 1, 10]", dl.toStringRev());
        assertEquals(7, dl.size());

        // insert before tail
        dl.insertBefore(dl.tail(), 11);
        assertEquals("[10, 1, 2, 3, 4, 5, 11, 6]", dl.toString());
        assertEquals("[6, 11, 5, 4, 3, 2, 1, 10]", dl.toStringRev());
        assertEquals(8, dl.size());
        // checking if the values are correct
        assertEquals(11, dl.tail().prev().value());
        assertEquals(5, dl.tail().prev().prev().value());

        // insert in middle
        dl.insertBefore(dl.node(3), 12);
        assertEquals("[10, 1, 2, 12, 3, 4, 5, 11, 6]", dl.toString());
        assertEquals("[6, 11, 5, 4, 3, 12, 2, 1, 10]", dl.toStringRev());
        assertEquals(9, dl.size());

    }

    @Test
    void testDelete() {
        DLList<Integer> dl= new DLList<>();
        dl.append(1);
        dl.append(2);
        dl.append(3);
        dl.append(4);
        dl.append(5);
        dl.append(6);
        assertEquals("[1, 2, 3, 4, 5, 6]", dl.toString());
        assertEquals("[6, 5, 4, 3, 2, 1]", dl.toStringRev());
        assertEquals(6, dl.size());

        // deleting head
        dl.delete(dl.node(0));
        assertEquals("[2, 3, 4, 5, 6]", dl.toString());
        assertEquals("[6, 5, 4, 3, 2]", dl.toStringRev());
        assertEquals(5, dl.size());

        // deleting tail
        dl.delete(dl.node(4));
        assertEquals("[2, 3, 4, 5]", dl.toString());
        assertEquals("[5, 4, 3, 2]", dl.toStringRev());
        assertEquals(4, dl.size());

        // deleting from middle
        dl.delete(dl.node(1));
        assertEquals("[2, 4, 5]", dl.toString());
        assertEquals("[5, 4, 2]", dl.toStringRev());
        assertEquals(3, dl.size());

        // deleting last node
        dl.delete(dl.node(0));
        dl.delete(dl.node(0));
        assertEquals("[5]", dl.toString());
        assertEquals("[5]", dl.toStringRev());
        assertEquals(1, dl.size());
        dl.delete(dl.node(0));
        assertEquals("[]", dl.toString());
        assertEquals("[]", dl.toStringRev());
        assertEquals(0, dl.size());
        // dl.delete(dl.node(0));

    }

}
