package AI.lab_2.graphSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        List<Node> explored = new ArrayList<Node>();
        while(!frontier.isEmpty()){
            Node current = frontier.pop();
            if(current.getLabel().equals(goal)) return current;
            explored.add(current);
            List<Node> childrent = current.getChildrenNodes();
            for (int i = childrent.size() - 1; i >= 0 ; i--){
                 if(!frontier.contains(childrent.get(i)) && !explored.contains(childrent.get(i))){
                    frontier.add(childrent.get(i));
                    childrent.get(i).setParent(current);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        List<Node> explored = new ArrayList<Node>();
        while(!frontier.isEmpty()){
            Node current = frontier.pop();
            if(current.getLabel().equals(goal)) return current;
            explored.add(current);
            List<Node> childrent = current.getChildrenNodes();
            for (int i = childrent.size() - 1; i >= 0 ; i--){
                if(!frontier.contains(childrent.get(i)) && !explored.contains(childrent.get(i))){
                    frontier.add(childrent.get(i));
                    if(childrent.get(i).getLabel().equals(start)){
                        childrent.get(i).setParent(null);
                    }else {
                        childrent.get(i).setParent(current);
                    }
                }
            }
        }
        return null;
    }
}
