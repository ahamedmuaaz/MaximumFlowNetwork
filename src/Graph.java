public class Graph {
    public int V;
    int s;
    int t;
    int [][] graph;


    public Graph(){
        V=(int)(Math.random() * ((12 - 6) + 1)) + 6;
        V=3;
        s=0;
        t=V-1;
       // this.generateGraph();



        System.out.println(V);
        System.out.println(s);
        System.out.println(t);

    }

    public static void main(String[] args) {
        Graph g=new Graph();

    }

    public void generateGraph(){
        this. graph=new int[V][V];
        for(int i=0;i<graph.length;i++){
            for(int v=0;v<graph.length;v++){
                if(i==v || v==0 ||i==t){
                    graph[i][v]=0;
                }
                else{
                    graph[i][v]=(int)(Math.random() * ((20 - 5) + 1)) + 5;

                }

            }
        }

        for(int i=0;i<graph.length;i++){
            for(int m=0;m<V;m++){
                System.out.print(graph[i][m]+" ");
            }
            System.out.println();
        }



    }

}
