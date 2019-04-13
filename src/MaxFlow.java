import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class MaxFlow {
    public static Graph g = new Graph();
    static Scanner sc = new Scanner(System.in);
    static final int V = g.V;    //Number of vertices in graph

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];


        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        //LinkedList<Integer> queue = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(s);
        visited[s] = true;
        parent[s] = -1;

        boolean b = false;
        // Standard BFS Loop
        int m = 0;

        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++) arr.add(-1);

        while (!stack.empty()) {
            //remove from top
            int u = stack.pop();
            b = false;
            // visited[u] = true;

            System.out.println("popped" + u + " ");
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

                //m=-1;
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
                //b=false;
            }


        }

        // If we reached sink in BFS starting from source, then
        //return true, else false
        return (visited[t] == true);
    }

    // private static int[] parent = new int[V];


    /* static boolean dfs(int rGraph[][], int s, int t, int parent[]) {
         // Create a visited array and mark all vertices as not
         // visited
         boolean visited[] = new boolean[V];


         // Create a queue, enqueue source vertex and mark
         // source vertex as visited
         //LinkedList<Integer> queue = new LinkedList<Integer>();
         Stack<Integer> stack = new Stack<Integer>();
         stack.add(s);
         visited[s] = true;
         parent[s] = -1;
         boolean b = false;
         int scources = 0;

         for(int i=0;i<V;i++)
         {
             if(rGraph[0][i]>0)
                 scources++;
         }

         System.out.println("Scources :"+scources);
         int u=0;
         // Standard BFS Loop
         while (!(u==0 && scources==0)) {
             //remove from top

             visited[u] = true;
 //            System.out.println("popped" + u + " ");
             for (int v = 0; v < V; v++) {
                 if (visited[v] == false && rGraph[u][v] > 0) {
                     stack.push(v);
                     parent[v] = u;
                     visited[v] = true;
                     System.out.println("was here");
                     u=v;
                     b = true;
                     if(u==0)
                         scources--;
                     break;
                 }
             }
             if (b == false) {
                 u = stack.pop();
             }
             System.out.println("u :"+u);

         }

         return (visited[t] == true);

     }
 */
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
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
//            System.out.println("path*************");
//            for (int i = V - 1; i >= 0;) {
//                System.out.print(i+" :"+parent[i] + " ");
//                i=parent[i];
//            }
            System.out.println();
            System.out.println("*************");

            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);

            }
            System.out.println("pathe min=" + path_flow);

            // update residual capacities of the edges and
            // reverse edges along the path
            System.out.println("path : ");
            for (v = t; v != s; v = parent[v]) {
                System.out.print(v + " ");
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

        // Return the overall flow
        return max_flow;
    }

    // Driver program to DepthFirstSearchExample above functions
    public static void main(String[] args) throws java.lang.Exception {
        // Let us create a graph shown in the above example
       /* int graph[][] = new int[][]{
                {0, 10, 8, 0, 0, 0},
                {0, 0, 2, 5, 0, 0},
                {0, 0, 0, 0, 10, 0},
                {0, 0, 0, 0, 0, 7},
                {0, 0, 0, 8, 0, 10},
                {0, 0, 0, 0, 0, 0}
        };
*/
//        System.out.println();
        int input = 99;
        while (input != 0) {
            System.out.println("*********___Menu___***********:");
            System.out.println("Press 1 Generate Graph");
            System.out.println("Press 2 View Graph");
            System.out.println("Press 3 MaxFlow");
            System.out.println("Press 0 EXIT");


            /*do{
                System.out.println("Enter No:");
                try{

                input = sc.nextInt();

                }
                catch (Exception ex){
                    System.out.println("Invlaid input");
                }

            }
            while(!sc.hasNext());*/
            System.out.println("Enter No:");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            input= sc.nextInt();





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
                    MaxFlow m = new MaxFlow();

                    System.out.println("The maximum possible flow is " +
                            m.fordFulkerson(g.graph, g.s, g.t));
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