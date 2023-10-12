package AI.lab_2.treeSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        while(!frontier.isEmpty()){
            Node current = frontier.pop();
            if(current.getLabel().equals(goal)) return current;
            List<Node> children = current.getChildrenNodes();
            for (int i = children.size()- 1; i >= 0 ; i--) {
                if(children.get(i).getParent() == null){
                    children.get(i).setParent(current);
                    frontier.add(children.get(i));
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Stack<Node> frontier = new Stack<>();
        frontier.add(root);
        while(!frontier.isEmpty()){
            Node current = frontier.pop();
            if(current.getLabel().equals(goal) &&  findParentStart(current, start)){
                return current;
            }
            List<Node> children = current.getChildrenNodes();
            for (int i = children.size()- 1; i >= 0 ; i--) {
                frontier.add(children.get(i));
                children.get(i).setParent(current);
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
