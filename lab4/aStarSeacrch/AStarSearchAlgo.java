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
            System.out.println(current.getLabel()+" = "+current.getG());
            if(current.getLabel().equals(goal)){
                return current;
            }else {
                explord.add(current);
            }
            List<Edge> children = current.getChildren();
            for(Edge child: children){
                Node nodeChild = child.getEnd();
                double extraH = (current.getParent() != null)? current.getG() - current.getH() : 0;
                nodeChild.setG(extraH + child.getWeight() + nodeChild.getH());
                if((!frontier.contains(nodeChild) || !explord.contains(nodeChild)) || checkFnLessMore(frontier, nodeChild)){
                    nodeChild.setParent(current);
                    System.out.println(current.getLabel()+"--->"+nodeChild.getLabel());
                    frontier.add(nodeChild);
                }
            }
        }
        return null;
    }

    private boolean checkFnLessMore(PriorityQueue<Node> frontier, Node nodeChild) {
        for(Node child: frontier){
            if(child.getLabel().equals(nodeChild.getLabel()) && child.getG() > nodeChild.getG()){
               frontier.remove(child);
                System.out.println("run");
                return true;
            }
        }
        return false;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(root);
        List<Node> explord = new ArrayList<>();
        while(!frontier.isEmpty()){
            Node current = frontier.poll();
            System.out.println(current.getLabel()+" = "+current.getG());
            if(current.getLabel().equals(goal) && findParentStart(current, start)){
                return current;
            }else {
                explord.add(current);
            }
            List<Edge> children = current.getChildren();
            for(Edge child: children){
                Node nodeChild = child.getEnd();
                double extraH = (current.getParent() != null)? current.getG() - current.getH() : 0;
                nodeChild.setG(extraH + child.getWeight() + nodeChild.getH());
                if((!frontier.contains(nodeChild) || !explord.contains(nodeChild)) || checkFnLessMore(frontier, nodeChild)){
                    nodeChild.setParent(current);
                    System.out.println(current.getLabel()+"--->"+nodeChild.getLabel());
                    frontier.add(nodeChild);
                }
            }
        }
        return null;
    }

    @Override
    public boolean isAdmissibleH(Node root, String goal) {
            PriorityQueue<Node> frontier = new PriorityQueue<>();
            frontier.add(root);
            List<Node> explord = new ArrayList<>();
            while(!frontier.isEmpty()){
                Node current = frontier.poll();
                if((current.getLabel().equals(goal) && current.getG() >= current.getH()) || current.getH() == 0){
                    return true;
                }
                explord.add(current);
                List<Edge> children = current.getChildren();
                for(Edge child: children){
                    Node nodeChild = child.getEnd();
                    double extraH = (current.getParent() != null)? current.getG() - current.getH() : 0;
                    nodeChild.setG(extraH + child.getWeight() + nodeChild.getH());
                    if((!frontier.contains(nodeChild) || !explord.contains(nodeChild)) || checkFnLessMore(frontier, nodeChild)){
                        nodeChild.setParent(current);
                        frontier.add(nodeChild);
                    }
                }
            }
        return false;
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
