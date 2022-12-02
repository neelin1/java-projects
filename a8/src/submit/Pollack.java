package submit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.FindState;
import graph.Finder;
import graph.FleeState;
import graph.Node;
import graph.NodeStatus;

/** A solution with find-the-Orb optimized and flee getting out as fast as possible. */
public class Pollack extends Finder {
    /** Get to the orb in as few steps as possible. <br>
     * Once you get there, you must return from the function in order to pick it up. <br>
     * If you continue to move after finding the orb rather than returning, it will not count.<br>
     * If you return from this function while not standing on top of the orb, it will count as <br>
     * a failure.
     *
     * There is no limit to how many steps you can take, but you will receive<br>
     * a score bonus multiplier for finding the orb in fewer steps.
     *
     * At every step, you know only your current tile's ID and the ID of all<br>
     * open neighbor tiles, as well as the distance to the orb at each of <br>
     * these tiles (ignoring walls and obstacles).
     *
     * In order to get information about the current state, use functions<br>
     * currentLoc(), neighbors(), and distanceToOrb() in FindState.<br>
     * You know you are standing on the orb when distanceToOrb() is 0.
     *
     * Use function moveTo(long id) in FindState to move to a neighboring<br>
     * tile by its ID. Doing this will change state to reflect your new position.
     *
     * A suggested first implementation that will always find the orb, but <br>
     * likely won't receive a large bonus multiplier, is a depth-first search. <br>
     * Some modification is necessary to make the search better, in general. */

    @Override
    public void find(FindState state) {
        // TODO 1: Walk to the orb
        ArrayList<Long> visited= new ArrayList<>();
        dfsWalk1(state, visited);
    }

    /** navigate the grid following a classic dfs walk search, always turning towards the neighbor
     * that is closer to the orb */
    public boolean dfsWalk1(FindState s, ArrayList<Long> visited) {
        if (s.distanceToOrb() == 0) return true;
        long u= s.currentLoc();
        visited.add(u);
        ArrayList<NodeStatus> opt= optimizedNeighbors(s.neighbors());
        for (NodeStatus w : opt) {
            if (!visited.contains(w.getId())) {
                s.moveTo(w.getId());
                if (dfsWalk1(s, visited)) { return true; }

                s.moveTo(u);
            }
        }
        return false;
    }

    /** order the neighbors by distance to orb */
    public ArrayList<NodeStatus> optimizedNeighbors(Collection<NodeStatus> n) {
        ArrayList<NodeStatus> neighborList= new ArrayList<>();
        for (NodeStatus w : n) {
            neighborList.add(w);
        }

        for (int i= 0; i < neighborList.size(); i++ ) {
            int k= i;
            while (k > 0 && neighborList.get(k).compareTo(neighborList.get(k - 1)) < 0) {
                Collections.swap(neighborList, k - 1, k);
                k-- ;
            }
        }

        return neighborList;
    }

    /** Get out the cavern before the ceiling collapses, trying to collect as <br>
     * much gold as possible along the way. Your solution must ALWAYS get out <br>
     * before steps runs out, and this should be prioritized above collecting gold.
     *
     * You now have access to the entire underlying graph, which can be accessed <br>
     * through FleeState state. <br>
     * currentNode() and exit() will return Node objects of interest, and <br>
     * allsNodes() will return a collection of all nodes on the graph.
     *
     * Note that the cavern will collapse in the number of steps given by <br>
     * stepsLeft(), and for each step this number is decremented by the <br>
     * weight of the edge taken. <br>
     * Use stepsLeft() to get the steps still remaining, and <br>
     * moveTo() to move to a destination node adjacent to your current node.
     *
     * You must return from this function while standing at the exit. <br>
     * Failing to do so before steps runs out or returning from the wrong <br>
     * location will be considered a failed run.
     *
     * You will always have enough steps to flee using the shortest path from the <br>
     * starting position to the exit, although this will not collect much gold. <br>
     * For this reason, using Dijkstra's to plot the shortest path to the exit <br>
     * is a good starting solution
     *
     * Here's another hint. Whatever you do you will need to traverse a given path. It makes sense
     * to write a method to do this, perhaps with this specification:
     *
     * // Traverse the nodes in moveOut sequentially, starting at the node<br>
     * // pertaining to state <br>
     * // public void moveAlong(FleeState state, List<Node> moveOut) */
    @Override
    public void flee(FleeState state) {
        // TODO 2. Get out of the cavern in time, picking up as much gold as possible.
        ArrayList<Node> nodes= orderNodes(state);
        while (state.currentNode() != state.exit()) {
            getGold(state, nodes);
        }
    }

    /** order all of the nodes on the map by amount of gold on the tile */
    public ArrayList<Node> orderNodes(FleeState s) {
        ArrayList<Node> orderedNode= new ArrayList<>();
        for (Node n : s.allNodes()) {
            orderedNode.add(n);
        }
        for (int i= 0; i < orderedNode.size(); i++ ) {
            int k= i;
            while (k > 0 &&
                orderedNode.get(k).getTile().gold() < orderedNode.get(k - 1).getTile().gold()) {
                Collections.swap(orderedNode, k - 1, k);
                k-- ;
            }
        }

        return orderedNode;
    }

    /** move from a specified start node to a specified end node */
    public void moveIt(FleeState s, Node start, Node finish) {
        List<Node> path= Path.shortestPath(start, finish);
        for (int i= 1; i < path.size(); i++ ) {
            s.moveTo(path.get(i));
        }
    }

    /** sort the elements of the hashmap by the value */
    public static HashMap<Node, Integer> hashSort(HashMap<Node, Integer> map1) {
        List<Map.Entry<Node, Integer>> list= new LinkedList<Map.Entry<Node, Integer>>(
            map1.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Node, Integer>>() {
            @Override
            public int compare(Map.Entry<Node, Integer> x,
                Map.Entry<Node, Integer> y) {
                return x.getValue().compareTo(y.getValue());
            }
        });
        HashMap<Node, Integer> map2= new LinkedHashMap<Node, Integer>();
        for (Map.Entry<Node, Integer> m : list) {
            map2.put(m.getKey(), m.getValue());
        }
        return map2;
    }

    /** go to the optimized tile in terms of distance and gold */
    public void getGold(FleeState s, ArrayList<Node> nodes) {
        Node first= nodes.get(nodes.size() - 1);
        Node second= nodes.get(nodes.size() - 2);
        Node third= nodes.get(nodes.size() - 3);
        Node fourth= nodes.get(nodes.size() - 4);
        List<Node> path1= Path.shortestPath(s.currentNode(), first);
        List<Node> path1_1= Path.shortestPath(first, s.exit());
        List<Node> path2= Path.shortestPath(s.currentNode(), second);
        List<Node> path2_1= Path.shortestPath(second, s.exit());
        List<Node> path3= Path.shortestPath(s.currentNode(), third);
        List<Node> path3_1= Path.shortestPath(third, s.exit());
        List<Node> path4= Path.shortestPath(s.currentNode(), fourth);
        List<Node> path4_1= Path.shortestPath(fourth, s.exit());
        int distance= Path.pathSum(path1) + Path.pathSum(path1_1);
        int distance1= Path.pathSum(path2) + Path.pathSum(path2_1);
        int distance2= Path.pathSum(path3) + Path.pathSum(path3_1);
        int distance3= Path.pathSum(path4) + Path.pathSum(path4_1);
        HashMap<Node, Integer> minArray= new HashMap<>();
        minArray.put(first, distance);
        minArray.put(second, distance1);
        minArray.put(third, distance2);
        minArray.put(fourth, distance3);
        hashSort(minArray);
        Set<Node> nodes11= minArray.keySet();
        Node[] nodes1= nodes11.toArray(new Node[4]);
        if (minArray.get(nodes1[0]) <= s.stepsLeft() &&
            Path.pathSum(Path.shortestPath(nodes1[0], s.exit())) <= s.stepsLeft()) {
            moveIt(s, s.currentNode(), nodes1[0]);
            nodes.remove(nodes.size() - 1);
        } else if (minArray.get(nodes1[1]) <= s.stepsLeft() &&
            Path.pathSum(Path.shortestPath(nodes1[1], s.exit())) <= s.stepsLeft()) {
                moveIt(s, s.currentNode(), nodes1[1]);
                nodes.remove(nodes.size() - 2);
            } else if (minArray.get(nodes1[2]) <= s.stepsLeft() &&
                Path.pathSum(Path.shortestPath(nodes1[2], s.exit())) <= s.stepsLeft()) {
                    moveIt(s, s.currentNode(), nodes1[2]);
                    nodes.remove(nodes.size() - 3);
                } else if (minArray.get(nodes1[3]) <= s.stepsLeft() &&
                    Path.pathSum(Path.shortestPath(nodes1[3], s.exit())) <= s.stepsLeft()) {
                        moveIt(s, s.currentNode(), nodes1[3]);
                        nodes.remove(nodes.size() - 4);
                    } else moveIt(s, s.currentNode(), s.exit());

    }

}
