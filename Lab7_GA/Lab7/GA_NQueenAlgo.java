package AI.Lab7_GA.Lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
    public static final int POP_SIZE = 100;//Population size
    public static final double MUTATION_RATE = 0.03;
    public static final int MAX_ITERATIONS = 1000;
    List<Node> population = new ArrayList<>();
    Random rd = new Random();

    // initialize the individuals of the population
    public void initPopulation() {
        for (int i = 0; i < POP_SIZE; i++) {
            Node ni = new Node();
            ni.generateBoard();
            population.add(ni);
        }
    }

    public Node execute() {
        // Enter your code here
        initPopulation();
        int k = 0;

        while (k++ < MAX_ITERATIONS) {

            List<Node> newPopulation = new ArrayList<Node>();
            for (int i = 0; i < POP_SIZE; i++) {
                Node x = getParentByRandomSelection();
                Node y = getParentByRandomSelection();
                Node child = reproduce(x, y);
                if ((new Random()).nextDouble() < MUTATION_RATE) {
                    mutate(child);
                }
                if (child.getH() == 0) {
                    return child;
                }
            }
            this.population = newPopulation;
        }
        Collections.sort(population);
        return population.get(0);
    }

    // Mutate an individual by selecting a random Queen and
    //move it to a random row.
    public void mutate(Node node) {
        // Enter your code here
        int rdom = rd.nextInt(Node.N);
        Queen queen = new Queen(rdom,rdom);
        queen.setRow(rdom);

    }

    //Crossover x and y to reproduce a child
    public Node reproduce(Node x, Node y) {
        // Enter your code here
        Node child = new Node();
        child.generateBoard();
        int c = (new Random()).nextInt(Node.N);
        for (int i = 0; i < c; i++) {
            child.setRow(i, x.getRow(i));
        }
        for (int i = 0; i < Node.N; i++) {
            child.setRow(i, y.getRow(i));
        }
        return child;
    }

    // Select K individuals from the population at random and
    //select the best out of these to become a parent.
    public Node getParentByTournamentSelection() {
        // Enter your code here
        int k = 4;
        List<Node> listKPopulation = new ArrayList<Node>();
        listKPopulation = getListKPopulation(k);
        Collections.sort(listKPopulation);
        return listKPopulation.get(0);
    }

    private List<Node> getListKPopulation(int k) {
        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < k; i++) {
            result.add(this.population.get((new Random()).nextInt(POP_SIZE)));
        }
        return result;
    }

    //Select a random parent from the population
    public Node getParentByRandomSelection() {
// Enter your code here
        return this.population.get((new Random()).nextInt(POP_SIZE));
    }
}
