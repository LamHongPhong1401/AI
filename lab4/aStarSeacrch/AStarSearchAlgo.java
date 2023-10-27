package AI.lab4.aStarSeacrch;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(root);
        List<Node> explord = new ArrayList<>();
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            if(current.getLabel().equals(goal)){
                return current;
            }else {
                explord.add(current);
            }
            List<Edge> children = current.getChildren();
            for(Edge child: children){
                Node nodeChild = child.getEnd();
                nodeChild.setG(current.getG() + child.getWeight() + nodeChild.getH());
                if(!frontier.contains(nodeChild) || !explord.contains(nodeChild)){
                    nodeChild.setParent(current);
                    frontier.add(nodeChild);
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
            if(current.getLabel().equals(goal) && findParentStart(current, start)){
                return current;
            }else {
                explord.add(current);
            }
            List<Edge> children = current.getChildren();
            for(Edge child: children){
                Node nodeChild = child.getEnd();
                nodeChild.setG(current.getG() + child.getWeight() + nodeChild.getH());
                if(!frontier.contains(nodeChild) || !explord.contains(nodeChild)){
                    nodeChild.setParent(current);
                    frontier.add(nodeChild);
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
