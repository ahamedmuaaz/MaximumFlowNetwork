import java.util.LinkedList;
import java.util.Stack;

public class MaxFlow
{    public static Graph g=new Graph();
    static final int  V=g.V;    //Number of vertices in graph

    /* Returns true if there is a path from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      path */
    boolean bfs(int rGraph[][], int s, int t, int parent[])
    {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];


        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        //LinkedList<Integer> queue = new LinkedList<Integer>();
        Stack<Integer> stack=new Stack<Integer>();
        stack.add(s);
        visited[s] = true;
        parent[s]=-1;

        // Standard BFS Loop
        while (!stack.empty())
        {
            //remove from top
            int u =stack.pop();
            visited[u] = true;
            System.out.println("popped"+u + " ");
            for (int v=0; v<V; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    stack.push(v);
                    parent[v] = u;
                   // visited[v] = true;
                    //stop exploring the current node
                  // break;
                }
            }

        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }

    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], int s, int t)
    {
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

        for(int i=0;i<rGraph.length;i++) {
            for(int j=0;j<rGraph.length;j++) {
                //System.out.print(rGraph[i][j]+" ");
            }
            System.out.println();

        }

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t, parent))
        {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            System.out.println("path*************");
            for(int i=V-1;i>=0;i--) {
                System.out.print(parent[i]+" ");
            }
            System.out.println("*************");

            int path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);

            }
            System.out.println("pathe min="+path_flow);

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            System.out.println("***********");
            for(int i=0;i<rGraph.length;i++) {
                for(int j=0;j<rGraph.length;j++) {
                    System.out.print(rGraph[i][j]+" ");
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
    public static void main (String[] args) throws java.lang.Exception
    {
        // Let us create a graph shown in the above example
        int graph[][] =new int[][] {
                {0, 10,8, 0, 0, 0},
                {0, 0,2,5, 0, 0},
                {0, 0, 0, 0, 10, 0},
                {0, 0, 0, 0, 0,7},
                {0, 0, 0, 8, 0,10},
                {0, 0, 0, 0, 0, 0}
        };
        MaxFlow m = new MaxFlow();
        g.generateGraph();

        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(g.graph,g.s,g.t));

    }
}