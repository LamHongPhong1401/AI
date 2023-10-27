package AI.lab4.greedyBestFirstSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo{
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(root);
        List<Node> explord = new ArrayList<>();
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)) return current;
            List<Node> childrent = current.getChildrenNodes();
            for(Node child: childrent){
                if(!frontier.contains(child) || !explord.contains(child)){
                    child.setParent(current);
                    frontier.add(child);
                    explord.add(child);
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(root);
        List<Node> explord = new ArrayList<>();
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)  && findParentStart(current, start)) return current;
            List<Node> childrent = current.getChildrenNodes();
            for(Node child: childrent){
                if(!frontier.contains(child) || !explord.contains(child)){
                    child.setParent(current);
                    frontier.add(child);
                    explord.add(child);
                }
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
