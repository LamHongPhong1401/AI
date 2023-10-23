package AI.lab_2.task4AndTask5;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {
    /*
    graph search
     */
    @Override
    public Node execute(Node root, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue();
        frontier.add(root);
        List<Node> explord = new ArrayList<Node>();
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal)){
                return current;
            }else{
                explord.add(current);
            }
            for (Edge edge : current.getChildren()) {
                Node nodeChild = edge.getEnd();
                nodeChild.setPathCost(current.getPathCost() + edge.getWeight());
                if((!explord.contains(nodeChild) || !frontier.contains(nodeChild)) || checkSmallerPathCostNodeChild(nodeChild, frontier)){
                    frontier.add(nodeChild);
                    nodeChild.setParent(current);
                }
            }
        }
        return null;
    }
    /*
    check pastCost nodeChild có nhỏ hơn cái pastCost nodeChild đã có trong frontier hay không?
     */
    private boolean checkSmallerPathCostNodeChild(Node nodeChild, PriorityQueue<Node> frontier){
        for(Node nodeOfFrontier: frontier){
            if(nodeChild.getLabel().equals(nodeOfFrontier.getLabel()) && nodeChild.getPathCost() < nodeOfFrontier.getPathCost()){
                nodeOfFrontier.setPathCost(nodeChild.getPathCost());
                System.out.println("run");
                return true;
            }
        }
        return false;
    }
    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue();
        frontier.add(root);
        List<Node> explord = new ArrayList<Node>();
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            if (current.getLabel().equals(goal) && findParentStart(current, start)){
                return current;
            }else{
                explord.add(current);
            }
            for (Edge edge : current.getChildren()) {
                Node nodeChild = edge.getEnd();
                nodeChild.setPathCost(current.getPathCost() + edge.getWeight());
                if((!explord.contains(nodeChild) || !frontier.contains(nodeChild)) || checkSmallerPathCostNodeChild(nodeChild, frontier)){
                    frontier.add(nodeChild);
                    nodeChild.setParent(current);
                }
            }
        }
        return null;
    }
    /*
    set parent of start == null
     */
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
