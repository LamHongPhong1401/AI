package AI.lab_2.treeSearch;

import java.util.*;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
   /*
   using the tree search approach.
    */
    @Override
    public Node execute(Node root, String goal) {
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(root);
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal))return current;
            List<Node> children = current.getChildrenNodes();
            for(Node child: children){
                if(child.getParent() == null){
                    child.setParent(current);
                }
                frontier.add(child);
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(root);
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal) && findParentStart(current, start)) return current;
            List<Node> children = current.getChildrenNodes();
            for(Node child: children){
                child.setParent(current);
                frontier.add(child);
            }
        }
        return null;
    }
    private boolean findParentStart(Node goal, String start){
        Node find;
        while ((find = goal.getParent()) != null){
            if(find.getLabel().equals(start)) {
                find.setParent(null);
                return true;
            }else {
                goal = find;
            }
        }
        return  false;
    }
}
