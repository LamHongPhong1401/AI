package AI.lab_2.graphSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
   /*
   using the graph search approach.
    */
    @Override
    public Node execute(Node root, String goal) {
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(root);
        List<Node> explored = new ArrayList<Node>();
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)) return current;
            explored.add(current);
            List<Node> children = current.getChildrenNodes();
            for(Node child: children){
                if(!explored.contains(child) && !frontier.contains(child)){
                    frontier.add(child);
                    child.setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(root);
        List<Node> explored = new ArrayList<Node>();
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)){
                return current;
            }
            explored.add(current);
            List<Node> children = current.getChildrenNodes();
            for(Node child: children){
                if(!explored.contains(child) && !frontier.contains(child)){
                    frontier.add(child);
                    if(child.getLabel().equals(start)) {
                        child.setParent(null);
                    }else{
                        child.setParent(current);
                    }
                }
            }
        }
        return null;
    }

}
