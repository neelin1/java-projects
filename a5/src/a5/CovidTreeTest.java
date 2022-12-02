package a5;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/** @author david gries */
public class CovidTreeTest {
    /* DISCUSSION OF TESTING
     * Testing with trees is HARDER than testing in A1, A2, or even A3, with circular linked lists.
     *
     * We have provided some methods to help you test your methods that manipulate trees.
     *
     *1. TESTING METHOD SIZE. To do test size adequately, you have to create a
     * tree with lots of nodes and see whether size() returns the right value.
     *
     * METHOD makeTree1 CREATES A "LARGE" TREE!  USE IT TO TEST METHOD size()!
     *
     *
     * 2. TESTING METHOD CONTAINS.
     * Look directly below at the static fields. There is an array of type Human
     * and individual variables HumanA, ..., HumanL. Look below those declarations
     * at method setup. It has the annotation  @BeforeClass, which means that
     * it will be called before methods that have @Test before them are called.
     * Method setup initializes the fields just mentioned. Note this:
     *
     *     HumanA has name "A"
     *     HumanB has name "B"
     *     ...
     *     HumanL has name "L"
     *
     * Further, array humans contains the values (HumanA, HumanB, ..., HumanL).
     * You can use these in testing. For example, look at method makeTree1.
     * Its specification shows you the tree it constructs. For example, after
     * executing
     *
     *      CovidTree ct= makeTree1();
     *
     * you can test whether HumanA and HumanL are in it using
     *
     *      assertEquals(true, ct.contains(HumanA));
     *      assertEquals(false, ct.contains(HumanC));
     * */

    private static Network n;
    private static Human[] humans;
    private static Human humanA;
    private static Human humanB;
    private static Human humanC;
    private static Human humanD;
    private static Human humanE;
    private static Human humanF;
    private static Human humanG;
    private static Human humanH;
    private static Human humanI;
    private static Human humanJ;
    private static Human humanK;
    private static Human humanL;

    /** */
    @BeforeClass
    public static void setup() {
        n= new Network();
        humans= new Human[] { new Human("A", 0, n), new Human("B", 0, n),
                new Human("C", 0, n), new Human("D", 0, n),
                new Human("E", 0, n), new Human("F", 0, n),
                new Human("G", 0, n), new Human("H", 0, n),
                new Human("I", 0, n), new Human("J", 0, n),
                new Human("K", 0, n), new Human("L", 0, n)
        };
        humanA= humans[0];
        humanB= humans[1];
        humanC= humans[2];
        humanD= humans[3];
        humanE= humans[4];
        humanF= humans[5];
        humanG= humans[6];
        humanH= humans[7];
        humanI= humans[8];
        humanJ= humans[9];
        humanK= humans[10];
        humanL= humans[11];
    }

    /* TESTING METHOD DEPTH.
     * Take a look at the tree produced by method make1. We have:
     *    humanA is at depth 0
     *    humanB is at depth 1
     *    humanC is at depth 1
     *    humanD is at depth 2  and so on.
     *
     * Array people already contains humanA, humanB, humanC, ..., humanL
     * What if you constructed an array
     *
     *    var depths= new int[]{0, 1, 1, 2, ...}
     *
     * that contained the depth of each human in array people (including those
     * that are not in it)? You could then write a loop in method testContains
     * to test ALL of those people:
     *
     *    for (int k= 0; k < people.length; k= k+1) {
     *        assertEquals(depths[k], a suitable call on depth, for you to do);
     *    }
     *
     * This is the work that has to go on to do adequate testing
     */

    /** * */
    @Test
    public void testBuiltInGetters() {
        var st= new CovidTree(humanB);
        assertEquals("B", toStringBrief(st));
    }

    /** Create a CovidTree with structure A[B[D E F[G[H[I] J]]] C] <br>
     * This is the tree
     *
     * <pre>
     *            A
     *          /   \
     *         B     C
     *       / | \
     *      D  E  F
     *            |
     *            G
     *            | \
     *            H  J
     *            |
     *            I
     * </pre>
     */
    private CovidTree makeTree1() {
        var dt= new CovidTree(humanA); // A
        dt.insert(humanA, humanB); // A, B
        dt.insert(humanA, humanC); // A, C
        dt.insert(humanB, humanD); // B, D
        dt.insert(humanB, humanE); // B, E
        dt.insert(humanB, humanF); // B, F
        dt.insert(humanF, humanG); // F, G
        dt.insert(humanG, humanH); // G, H
        dt.insert(humanH, humanI); // H, I
        dt.insert(humanG, humanJ); // G, J
        return new CovidTree(dt);
    }

    /** test a call on makeTree1(). */
    @Test
    public void testMakeTree1() {
        var dt= makeTree1();
        assertEquals("A[B[D E F[G[H[I] J]]] C]", toStringBrief(dt));
    }

    /** */
    @Test
    public void test1Insert() {
        var st= new CovidTree(humanB);

        // Test insert in the root
        var dtC= st.insert(humanB, humanC); // testing adding a child
        assertEquals("B[C]", toStringBrief(st)); // test tree
        assertEquals(humanC, dtC.human());  // test return value

        // YOU SHOULD WRITE MORE TEST CASES HERE. We supply only one test case.

        var dtA= st.insert(humanC, humanA); // testing adding a child to a child (row 3)
        assertEquals("B[C[A]]", toStringBrief(st)); // test tree
        assertEquals(humanA, dtA.human());  // test return value

        var dtD= st.insert(humanC, humanD); // testing adding a second child
        assertEquals("B[C[A D]]", toStringBrief(st)); // test tree
        assertEquals(humanD, dtD.human());  // test return value

        // var dtN= st.insert(humanC, null); //testing pre-condition assert statement, should throw
        // an error
        // var dtN= st.insert(null, humanC);

        var dtE= st.insert(humanB, humanE); // testing adding a second child when the first child
                                            // already has children
        var dtF= st.insert(humanE, humanF);
        assertEquals("B[C[A D] E[F]]", toStringBrief(st)); // test tree
        assertEquals(humanE, dtE.human());  // test return value
        assertEquals(humanF, dtF.human());  // test return value

    }

    /** */
    @Test
    public void test2size() {
        /* We provide ONE test case. YOU WRITE MORE.
         * At this point, look at line 13 (about) for a discussion of making
         * a tree with which to test size. */

        var st= new CovidTree(humanC);
        assertEquals(1, st.size());

        st.insert(humanC, humanA); // testing a really wide tree
        st.insert(humanC, humanB);
        st.insert(humanC, humanD);
        st.insert(humanC, humanE);
        st.insert(humanC, humanF);
        st.insert(humanC, humanG);
        st.insert(humanC, humanH);
        st.insert(humanC, humanI);
        st.insert(humanC, humanJ);
        assertEquals(10, st.size());

        var st2= makeTree1(); // testing large size tree
        assertEquals(10, st2.size());

        st2.insert(humanA, humanK); // testing adding more nodes to depth 0 and 1
        st2.insert(humanB, humanL);
        assertEquals(12, st2.size());

    }

    /** */
    @Test
    public void test3contains() {
        /* We give you ONE test case. You have to put more in. Look at
         * about line 24 for a discussion of how to do this. You will learn
         * a lot about how to prepare for testing complicated data structures.
         */
        var st= new CovidTree(humanC);
        assertEquals(true, st.contains(humanC));

        st.insert(humanC, humanA); // testing 1 child contains
        assertEquals(true, st.contains(humanA));

        assertEquals(false, st.contains(null)); // testing contains null

        assertEquals(false, st.contains(humanB)); // testing contains for a human not in the array

        var st2= makeTree1(); // testing many child contains
        assertEquals(true, st2.contains(humanA));
        assertEquals(true, st2.contains(humanB));
        assertEquals(true, st2.contains(humanC));
        assertEquals(true, st2.contains(humanD));
        assertEquals(true, st2.contains(humanE));
        assertEquals(true, st2.contains(humanF));
        assertEquals(true, st2.contains(humanG));
        assertEquals(true, st2.contains(humanH));
        assertEquals(true, st2.contains(humanI));
        assertEquals(true, st2.contains(humanJ));
        assertEquals(false, st2.contains(humanK)); // testing contains for a human not in the array
                                                   // for a large array

    }

    /** */
    @Test
    public void test4depth() {
        /* We give you ONE test case. You have to put more in. Look at
         * about line 90 for a discussion of how to do this. You will learn
         * a lot about how to prepare for testing complicated data structures.
         */
        var st= new CovidTree(humanB);
        st.insert(humanB, humanC);
        st.insert(humanC, humanD);
        assertEquals(0, st.depth(humanB));
        assertEquals(1, st.depth(humanC));
        assertEquals(2, st.depth(humanD));
        assertEquals(-1, st.depth(humanE));

        var st2= makeTree1();
        ArrayList<Human> nodes= new ArrayList<>();
        nodes.add(humanA); // A B C D E F G H J I
        nodes.add(humanB);
        nodes.add(humanC);
        nodes.add(humanD);
        nodes.add(humanE);
        nodes.add(humanF);
        nodes.add(humanG);
        nodes.add(humanH);
        nodes.add(humanJ);
        nodes.add(humanI);
        var depths= new int[] { 0, 1, 1, 2, 2, 2, 3, 4, 4, 5 };
        for (int k= 0; k < depths.length; k= k + 1) {
            var human= nodes.get(k);
            assertEquals(depths[k], st2.depth(human));
        }
        assertEquals(-1, st2.depth(humanK));

    }

    /** */
    @Test
    public void test5WidthAtDepth() {
        // We give you ONE test case. You write more.
        var st= new CovidTree(humanB);
        assertEquals(1, st.widthAtDepth(0));
        // assertEquals(1, st.widthAtDepth(-1)); //testing if it throws illegal argument exception

        st.insert(humanB, humanA); // testing a really wide tree
        st.insert(humanB, humanC);
        st.insert(humanB, humanD);
        st.insert(humanB, humanE);
        st.insert(humanB, humanF);
        st.insert(humanB, humanG);
        st.insert(humanB, humanH);
        st.insert(humanB, humanI);
        st.insert(humanB, humanJ);
        assertEquals(st.childrenSize(), st.widthAtDepth(1));

        var st2= makeTree1(); // testing a large tree
        assertEquals(1, st2.widthAtDepth(0));
        assertEquals(2, st2.widthAtDepth(1));
        assertEquals(3, st2.widthAtDepth(2));
        assertEquals(1, st2.widthAtDepth(3));
        assertEquals(2, st2.widthAtDepth(4));
        assertEquals(1, st2.widthAtDepth(5));

    }

    @SuppressWarnings("javadoc")
    @Test
    public void test6CovidRouteTo() {
        /* The one testcase we give shows you how method getNames() is
         * used to make testing a bit easier.
         * Use it in developing more testcases. Using method makeTree1 can help.*/
        var st= new CovidTree(humanB);
        var route= st.CovidRouteTo(humanB);
        assertEquals("[B]", getNames(route));

        var st2= makeTree1(); // testing a large tree
        var route2= st2.CovidRouteTo(humanI);
        assertEquals("[A, B, F, G, H, I]", getNames(route2));

        var route3= st2.CovidRouteTo(humanA);
        assertEquals("[A]", getNames(route3));

        var route4= st2.CovidRouteTo(humanJ);
        assertEquals("[A, B, F, G, J]", getNames(route4));

        var route5= st2.CovidRouteTo(humanB);
        assertEquals("[A, B]", getNames(route5));

        assertEquals(null, st2.CovidRouteTo(humanK));

    }

    /** Return the names of Humans in sp, separated by ", " and delimited by [ ]. <br>
     * Precondition: No name is the empty string. */
    private String getNames(List<Human> sp) {
        var res= "[";
        for (Human p : sp) {
            if (res.length() > 1) res= res + ", ";
            res= res + p.name();
        }
        return res + "]";
    }

    /** */
    @Test
    public void test7commonAncestor() {
        var st= new CovidTree(humanB);
        st.insert(humanB, humanC);
        var p= st.commonAncestor(humanC, humanC);
        assertEquals(humanC, p);
        assertEquals(humanB, st.commonAncestor(humanC, humanB));
        assertEquals(null, st.commonAncestor(humanB, humanK));

        // Write more test cases. You can use the tree that makeTree(1) returns.

        var st2= makeTree1(); // testing a large tree
        var p2= st2.commonAncestor(humanB, humanC);
        assertEquals(humanA, p2);
        assertEquals(humanA, st2.commonAncestor(humanA, humanI));
        assertEquals(humanG, st2.commonAncestor(humanH, humanJ));
        assertEquals(humanB, st2.commonAncestor(humanD, humanI));

        assertEquals(null, st2.commonAncestor(humanH, null)); // testing nulls
        assertEquals(null, st2.commonAncestor(null, humanA));
        assertEquals(null, st2.commonAncestor(humanH, humanK));
    }

    /** This is what makeTree1() produces
     *
     * <pre>
     *            A
     *          /   \
     *         B     C
     *       / | \
     *      D  E  F
     *            |
     *            G
     *            | \
     *            H  J
     *            |
     *            I
     * </pre>
     */

    /** */
    @Test
    public void test8equals() {
        // We give you one test case. You write more.
        // Using makeTree1() and makeTree2() can help.
        var treeB1= new CovidTree(humanB);
        var treeB2= new CovidTree(humanB);
        assertEquals(true, treeB1.equals(treeB1));
        assertEquals(true, treeB1.equals(treeB2));

        assertEquals(true, treeB2.equals(treeB1));
        assertEquals(true, treeB2.equals(treeB2));

        var treeB3= makeTree1();
        assertEquals(false, treeB2.equals(treeB3));
        assertEquals(false, treeB1.equals(treeB3));

        var treeB4= makeTree1();
        assertEquals(true, treeB3.equals(treeB4));
        assertEquals(true, treeB4.equals(treeB3));

        treeB4.insert(humanI, humanK);
        assertEquals(false, treeB3.equals(treeB4));
        assertEquals(false, treeB4.equals(treeB3));

        assertEquals(false, treeB3.equals(null));
        assertEquals(false, treeB3.equals(humanA));
    }

    /* Make a tree like makeTree1 except that use humanK instead of humanH*/
    private CovidTree makeTree2() {
        var dt= new CovidTree(humanA); // A
        dt.insert(humanA, humanB); // A, B
        dt.insert(humanA, humanC); // A, C
        dt.insert(humanB, humanD); // B, D
        dt.insert(humanB, humanE); // B, E
        dt.insert(humanB, humanF); // B, F
        dt.insert(humanF, humanG); // F, G
        dt.insert(humanG, humanK); // G, K
        dt.insert(humanK, humanI); // K, I
        dt.insert(humanG, humanJ); // G, J
        return new CovidTree(dt);
    }

    // ===================================
    // ==================================

    /** Return a representation of this tree. This representation is: <br>
     * (1) the name of the Human at the root, followed by <br>
     * (2) the representations of the children <br>
     * . (in alphabetical order of the children's names). <br>
     * . There are two cases concerning the children.
     *
     * . No children? Their representation is the empty string. <br>
     * . Children? Their representation is the representation of each child, <br>
     * . with a blank between adjacent ones and delimited by "[" and "]". <br>
     * <br>
     * Examples: One-node tree: "A" <br>
     * root A with children B, C, D: "A[B C D]" <br>
     * root A with children B, C, D and B has a child F: "A[B[F] C D]" */
    public static String toStringBrief(CovidTree t) {
        var res= t.human().name();

        var childs= t.copyOfChildren().toArray();
        if (childs.length == 0) return res;
        res= res + "[";
        selectionSort1(childs);

        for (var k= 0; k < childs.length; k= k + 1) {
            if (k > 0) res= res + " ";
            res= res + toStringBrief((CovidTree) childs[k]);
        }
        return res + "]";
    }

    /** Sort b --put its elements in ascending order. <br>
     * Sort on the name of the Human at the root of each CovidTree.<br>
     * Throw a cast-class exception if b's elements are not CovidTree */
    public static void selectionSort1(Object[] b) {
        var j= 0;
        // {inv P: b[0..j-1] is sorted and b[0..j-1] <= b[j..]}
        // 0---------------j--------------- b.length
        // inv : b | sorted, <= | >= |
        // --------------------------------
        while (j != b.length) {
            // Put into p the index of smallest element in b[j..]
            var p= j;
            for (var i= j + 1; i != b.length; i++ ) {
                var bi= ((CovidTree) b[i]).human().name();
                var bp= ((CovidTree) b[p]).human().name();
                if (bi.compareTo(bp) < 0) {
                    p= i;

                }
            }
            // Swap b[j] and b[p]
            var t= b[j];
            b[j]= b[p];
            b[p]= t;
            j= j + 1;
        }
    }

}
