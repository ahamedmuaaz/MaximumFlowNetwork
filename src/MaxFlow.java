import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MaxFlow {
    public static Graph g = new Graph();
    static Scanner sc = new Scanner(System.in);
    static int V=0;    //Number of vertices in graph

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */

    static boolean dfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not visited
        boolean visited[] = new boolean[V];

        Stack<Integer> stack = new Stack<Integer>();
        stack.add(s);
        visited[s] = true;
        parent[s] = -1;

        //to mark weather there are nodes to visit
        boolean b = false;
        //varibale to
        int m = 0;

        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++) arr.add(-1);

        while (!stack.empty()) {
            //remove from top
            int u = stack.pop();
            b = false;
            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    arr.add(u);
                    stack.push(v);
                    parent[v] = u;
                    visited[v] = true;
                    //stop exploring the current node
                    b = true;
                    break;
                }
            }
            if (b == false && visited[t] != true && m != -1) {

                for (int i = arr.size() - 1; i > 0; i--) {
                    if (arr.get(i) != -1) {
                        m = arr.get(i);
                        stack.push(m);
                        arr.set(i, -1);

                        break;
                    } else {
                        m = -1;
                    }

                }

            }


        }

        // If we reached sink in DFS starting from source, then
        //return true, else false
        return (visited[t] == true);
    }


    // Returns tne maximum flow from s to t in the given graph
    static int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];


        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (dfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by DFS. Or we can say
            // find the maximum flow through the path found.

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);

            }
            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }


            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

    static int fordFulkersonwithsteps(int graph[][], int s, int t) {
        int u, v;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];


        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (dfs(rGraph, s, t, parent)) {

//           System.out.println("path*************");
//           for (int i = V - 1; i >= 0;) {
//              System.out.print(i+" :"+parent[i] + " ");
//                i=parent[i];
//           }
           System.out.println();
           System.out.println("*************");

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);

            }
             System.out.println("Possible Flow = " + path_flow);

            // update residual capacities of the edges and
            // reverse edges along the path
            System.out.print("path : "+(V-1)+" ");
            for (v = t; v != s; v = parent[v]) {
                 System.out.print(parent[v] + " ");
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            System.out.println();
            System.out.println("***********");
            for (int i = 0; i < rGraph.length; i++) {
                for (int j = 0; j < rGraph.length; j++) {
                    System.out.print(rGraph[i][j] + " ");
                }

                System.out.println();
            }


            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // System.out.println("-----------------********----------------*********---------------");
        // Return the overall flow
        return max_flow;
    }

    // Driver program to DepthFirstSearchExample above functions
    public static void main(String[] args) throws java.lang.Exception {

        int input = 99;
        while (input != 0) {
            System.out.println("*********___Menu___***********:");
            System.out.println("Press 1 Generate Graph");
            System.out.println("Press 2 View Nodes and Edges");
            System.out.println("Press 3 MaxFlow");
            System.out.println("Press 4 Real Graph");
            System.out.println("Press 5 Change Capacities");
            System.out.println("Press 6 MaxFlow With Steps");
            System.out.println("Press 0 EXIT");


            System.out.println("Enter No:");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            input= sc.nextInt();


            MaxFlow m=new MaxFlow();



            switch (input) {

                case 1: {
                    g.generateGraph();
                }
                break;
                case 2: {

                    g.visualize(g.graph);
                }
                break;
                case 3: {
                    V=g.V;
                    System.out.println("The Maximum Possible Flow = " + m.fordFulkerson(g.graph, g.s, g.t));
                    System.out.println("-----------------********----------------*********---------------");
                }
                break;
                case 4: {


                    g.graphView(g.graph);
                }
                break;
                case 5: {


                    g.changeCapacity();
                }
                break;
                case 6: {
                    V=g.V;
                    System.out.println("The Maximum Possible Flow = " + m.fordFulkersonwithsteps(g.graph, g.s, g.t));
                    System.out.println("-----------------********----------------*********---------------");
                }
                break;
                case 0:{
                    System.out.println("******************");
                    System.exit(0);
                }
                break;
                default:{
                    System.out.println("Invalid Menu Number");}


            }
        }

    }
}