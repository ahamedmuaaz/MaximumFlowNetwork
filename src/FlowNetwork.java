public class FlowNetwork {

    private static void dfs(int i, int[][] graph, boolean[] visited) {
        if(!visited[i]){
            visited[i] = true; // Mark node as "visited"
            System.out.print(i+1 + " ");

            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j]==1 && !visited[j]) {
                    dfs(j, graph, visited); // Visit node
                }
            }
        }
    }

    public static void main(String[] args) {



        // your matrix declare
        boolean [] visited = new boolean[6];

        int graph[][] =new int[][] {
                {0, 10,8, 0, 0, 0},
                {0, 0,2,5, 0, 0},
                {0, 0, 0, 0, 10, 0},
                {0, 0, 0, 0, 0,7},
                {0, 0, 0, 8, 0,10},
                {0, 0, 0, 0, 0, 0}
        };
        int count = 0;
        for(int i = 0; i < graph.length; i++) {
            if(!visited[i]) {
                System.out.println("Compnent: " );
                dfs(i,graph,visited);
                ++count;
            }
        }
        System.out.println("Total number of Components: " + count);
    }

}
