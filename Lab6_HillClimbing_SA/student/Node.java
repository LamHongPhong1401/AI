package AI.Lab6_HillClimbing_SA.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Node {
    public static final int N = 8;
    private Queen[] state;

    public Node() {
        // generateBoard();
        state = new Queen[N];
    }

    public Node(Queen[] state) {
        this.state = new Queen[N];
        for (int i = 0; i < state.length; i++) {
            this.state[i] = new Queen(state[i].getRow(), state[i].getColumn());
        }
    }

    public Node(Node n) {
        this.state = new Queen[N];
        for (int i = 0; i < N; i++) {
            Queen qi = n.state[i];
            this.state[i] = new Queen(qi.getRow(), qi.getColumn());
        }
    }

    public void generateBoard() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            state[i] = new Queen(random.nextInt(N), i);
        }
    }

    public int getH() {
        int heuristic = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (state[i].isConflict(state[j])) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    public List<Node> generateAllCandidates() {
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < N; i++) {
            Node n = new Node(this.state);
            n.state[i].move();
            result.add(n);
        }

        return result;
    }

    public Node selectNextRandomCandidate() {
        Random random = new Random();
        int index = random.nextInt(N);
        return generateAllCandidates().get(index);
    }

    public void displayBoard() {
        int[][] board = new int[N][N];
        // set queen position on the board
        for (int i = 0; i < N; i++) {
            board[state[i].getRow()][state[i].getColumn()] = 1;
        }
        // print board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print("Q" + " ");
                } else {
                    System.out.print("-" + " ");
                }
            }
            System.out.println();
        }
    }

    /*
    task 3
     */
    public Node execute(Node initialState) {
        Node current = initialState;
        Node neighbor = null;
        while(true){
            neighbor = getBestCandidate(current);
            if(current.getH() <= neighbor.getH()){
                current = neighbor;
                return current;
            }
        }

    }
/*
lay gia tri cao nhat trong current
 */
    private Node getBestCandidate(Node current) {
        List<Node> candidates = current.generateAllCandidates();
        Collections.sort(candidates, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getH() - o2.getH();
            }
        });
        return candidates.get(0);
    }

    public Node executeHillClimbingWithRandomRestart(Node initialState) {
        int stepClimbed = 0;
        int stepClimbedAfterRandomRestart = 0;
        int randomRestarts = 0;
        Node current = execute(initialState);
        while(current.getH() != 0){
            randomRestarts++;
             generateBoard();
             stepClimbedAfterRandomRestart++;
             current = execute(current);
             stepClimbed++;
        }
        return current;
    }
    
}
