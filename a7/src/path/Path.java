
package path;

/* NetId(s):

 * Name(s): zmn5, afa48
 * What I thought about this assignment:
 *  Definitely less time intesive than GUIS. Still difficult, but did not take nearly as long
 *
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import graph.Edge;
import graph.Node;

/** This class contains the solution to A7, shortest-path algorithm, <br>
 * and other methods for an undirected graph. */
public class Path {

    /** Replace "-1" by the time you spent on A2 in hours.<br>
     * Example: for 3 hours 15 minutes, use 3.25<br>
     * Example: for 4 hours 30 minutes, use 4.50<br>
     * Example: for 5 hours, use 5 or 5.0 */
    public static double timeSpent= 3;

    /** = the shortest path from node v to node end <br>
     * ---or the empty list if a path does not exist. <br>
     * Note: The empty list is a list with 0 elements ---it is not "null". */
    public static List<Node> shortestPath(Node v, Node end) {
        /* TODO Implement this method.
         * Read the A7 assignment handout for all details.
         * Remember, the graph is undirected. */

        // Contains an entry for each node in the frontier set. The priority of
        // a node is the length of the shortest known path from v to the node
        // using only settled nodes except for the last node, which is in F.
        var F= new Heap<Node>(true);
        F.insert(v, 0);

        // Put in a declaration of the HashMap here, with a suitable name
        // for it and a suitable definition of its meaning --what it contains,
        // etc. See Section 10 point 4 of the A7 handout for help.

        // Contains all nodes in the settled and frontier sets as the key and
        // an object of type Info as the value (which contains the shortest known
        // distance of path to the node from v and the node directly before said node on the
        // backpointer path to v
        HashMap<Node, Info> nodeList= new HashMap<>();
        nodeList.put(v, new Info(0, null));

        // invariant: (1) For a node s in the Settled set (in nodeList, but not in F), a shortest v
        // --> s path exists that contains only settled nodes; d[s] (nodeList.get(s).dist) is its
        // length (distance) and bk[s] (nodeList.get(s).bkptr) is s's backpointer. (2) For each node
        // f in Frontier set F, a v --> f path exists that contains only settled nodes except for f;
        // d[f] (nodeList.get(f).dist) is the length (distance) of the shortest such path and bk[f]
        // (nodeList.get(f).bkptr) is f's backpointer on that path. (3) All edges leaving S go to F.
        while (F.size() > 0) {
            Node f= F.poll();
            if (f.equals(end)) {
                return pathToEnd(nodeList, end);
            } else {
                // invariant: nodeList contains all nodes in the settled and frontier sets as the
                // key and an object of type Info as the value (which contains the shortest known
                // distance of the node and the node directly before it on backpointer path
                // to v
                for (Edge e : f.exits()) {
                    Node w= e.other(f);
                    int wDist= nodeList.get(f).dist + e.length;
                    if (!nodeList.containsKey(w)) {
                        nodeList.put(w, new Info(wDist, f));
                        F.insert(w, wDist);
                    } else if (wDist < nodeList.get(w).dist) {
                        nodeList.get(w).dist= wDist;
                        nodeList.get(w).bkptr= f;
                        F.changePriority(w, wDist);
                    }
                }
            }
        }

        // Put all your code before this comment. Do not change this comment or return statement
        // no path from v to end.
        return new LinkedList<>();
    }

    /** An instance contains info about a node: <br>
     * the known shortest distance of this node from the start node and <br>
     * its backpointer: the previous node on a shortest path <br>
     * from the first node to this node (null for the start node). */
    private static class Info {
        /** shortest known distance from the start node to this one. */
        private int dist;
        /** backpointer on path (with shortest known distance) from <br>
         * start node to this one */
        private Node bkptr;

        /** Constructor: an instance with dist d from the start node and<br>
         * backpointer p. */
        private Info(int d, Node p) {
            dist= d;     // Distance from start node to this one.
            bkptr= p;    // Backpointer on the path (null if start node)
        }

        /** = a representation of this instance. */
        @Override
        public String toString() {
            return "dist " + dist + ", bckptr " + bkptr;
        }
    }

    /** = the path from the start node to node end.<br>
     * Precondition: SandF contains all the necessary information about<br>
     * ............. the path. */
    public static List<Node> pathToEnd(HashMap<Node, Info> SandF, Node end) {
        List<Node> path= new LinkedList<>();
        var p= end;
        // invariant: All the nodes from p's successor to node
        // . . . . . .end are in path, in reverse order.
        while (p != null) {
            path.add(0, p);
            p= SandF.get(p).bkptr;
        }
        return path;
    }

    /** = the sum of the weights of the edges on path p. <br>
     * Precondition: p contains at least 1 node. <br>
     * If 1 node, it's a path of length 0, i.e. with no edges. */
    public static int pathSum(List<Node> p) {
        synchronized (p) {
            Node w= null;
            var sum= 0;
            // invariant: if w is null, n is the start node of the path.<br>
            // .......... if w is not null, w is the predecessor of n on the path.
            // .......... sum = sum of weights on edges from first node to v
            for (Node n : p) {
                if (w != null) sum= sum + w.edge(n).length;
                w= n;
            }
            return sum;
        }
    }

}
