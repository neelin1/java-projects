package a1;
/** NetId: zmn5, afa48. Time spent: 3 hours, 0 minutes. <br>
 * What I thought about this assignment: good way to teach constructors <br>
 * Learned a lot. */

/** An instance maintains info about the PhD of a person. */

public class PhD {
    /** name of person with PhD, with at least 2 characters */
    private String name;
    /** year PhD was awarded */
    private int yearAwarded;
    /** month PhD was awarded. In 1,...,12, with 1 being January, etc. */
    private int monthAwarded;
    /** name of first PhD advisor. null if unknown. */
    private PhD firstAdvisor;
    /** name of second PhD advisor. null if unknown, or if less than two advisors. */
    private PhD secondAdvisor;
    /** number of PhD advisees */
    private int numAdvisees;

    /** Constructor: an instance with name n, PhD year y, and PhD month m. The advisors are unknown,
     * and there are 0 advisees.<br>
     * Precondition: n has at least 2 chars, and m is in 1..12. */
    public PhD(String n, int y, int m) {
        assert n.length() >= 2;
        assert m >= 1 && m <= 12;
        name= n;
        yearAwarded= y;
        monthAwarded= m;
    }

    /** Constructor: a PhD with name n, PhD year y, PhD month m, first advisor p1, and second
     * advisor p2.<br>
     * Precondition: n has at least 2 chars, m is in 1..12, p1 and p2 are not null, and p1 and p2
     * are different. */
    public PhD(String n, int y, int m, PhD p1, PhD p2) {
        assert n.length() >= 2;
        assert m >= 1 && m <= 12;
        assert p1 != null;
        assert p2 != null;
        assert p1 != p2;
        name= n;
        yearAwarded= y;
        monthAwarded= m;
        numAdvisees= 0;
        firstAdvisor= p1;
        secondAdvisor= p2;
        p1.numAdvisees++ ;
        p2.numAdvisees++ ;
    }

    /** Return the name of this person. <br>
     */
    public String name() {
        return name;
    }

    /** Return the date this person got their PhD in the form "month/year" <br>
     */
    public String date() {
        return monthAwarded + "/" + yearAwarded;
    }

    /** Return the first advisor of this PhD. If unknown, return null <br>
     */
    public PhD advisor1() {
        return firstAdvisor;
    }

    /** Return the second advisor of this PhD. If unknown or non-existent, return null. <br>
     */
    public PhD advisor2() {
        return secondAdvisor;
    }

    /** Return the number of PhD advisees of this person. <br>
     */
    public int advisees() {
        return numAdvisees;
    }

    /** Add p as the first advisor of this person. <br>
     * Precondition: the first advisor is unknown and p is not null. */
    public void addAdvisor1(PhD p) {
        assert advisor1() == null;
        assert p != null;
        firstAdvisor= p;
        p.numAdvisees++ ;
    }

    /** Add p as the second advisor of this PhD. <br>
     * Precondition: The first advisor is known, the second advisor is unknown, p is not null, and p
     * is different from the first advisor. */
    public void addAdvisor2(PhD p) {
        assert advisor1() != null;
        assert advisor2() == null;
        assert p != null;
        assert p != firstAdvisor;
        secondAdvisor= p;
        p.numAdvisees++ ;
    }

    /** Return value of: "p is not null and this PhD got the PhD before p" <br>
     */
    public boolean gotBefore(PhD p) {
        return p != null && (yearAwarded == p.yearAwarded && monthAwarded < p.monthAwarded ||
            yearAwarded < p.yearAwarded);
    }

    /** Return value of: "this PhD is an intellectual sibling of p".<br>
     * Precondition: p is not null. */
    public boolean isSiblingOf(PhD p) {
        assert p != null;
        return p != this && (firstAdvisor == p.firstAdvisor && firstAdvisor != null ||
            secondAdvisor == p.secondAdvisor && secondAdvisor != null ||
            firstAdvisor == p.secondAdvisor && firstAdvisor != null ||
            secondAdvisor == p.firstAdvisor && secondAdvisor != null);
    }
}
