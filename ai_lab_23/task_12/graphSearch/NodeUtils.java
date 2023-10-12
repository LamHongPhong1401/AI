package AI.lab_2.graphSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeUtils {
	public static List<String> printPath(Node node) {
		if (node != null) {
			List<String> result = new ArrayList<String>();
			result.add(node.getLabel());
			Node tmp;
			while ((tmp = node.getParent()) != null) {

				result.add(tmp.getLabel());
				node = tmp;
			}
			Collections.reverse(result);
			return result;
		} else
			return new ArrayList<String>();
	}
	public static void main(String[] args) {

		Node nodeS = new Node("S");
		Node nodeA = new Node("A"); Node nodeB = new Node("B");
		Node nodeC = new Node("C"); Node nodeD = new Node("D");
		Node nodeE = new Node("E"); Node nodeF = new Node("F");
		Node nodeG = new Node("G"); Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5); nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4); nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4); nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2); nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6); nodeF.addEdge(nodeG, 1);

//		ISearchAlgo algo1 = new BreadthFirstSearchAlgo();
//		Node result = algo1.execute(nodeS, "G");
//		System.out.println(printPath(result));

//		ISearchAlgo algo2 = new BreadthFirstSearchAlgo();
//		Node resultFromStart = algo2.execute(nodeS, "A", "H");
//		System.out.println(printPath(resultFromStart));

//		ISearchAlgo algo3 = new DepthFirstSearchAlgo();
//		Node result_depth = algo3.execute(nodeS, "G");
//		System.out.println(printPath(result_depth));

		ISearchAlgo algo4 = new DepthFirstSearchAlgo();
		Node result_depth_from_start = algo4.execute(nodeS, "A", "G");
		System.out.println(printPath(result_depth_from_start));
	}
}
