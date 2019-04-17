import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.Viewer.CloseFramePolicy;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Graph {
    // no of vertex
    public int V;

    //source node
    public int s;
    //sink node
    public int t;
    //graph
    public int [][] graph;
    Scanner sc=new Scanner(System.in);


    public Graph(){
        this.generateGraph();
    }

    //method to autogenerate graph
    public void generateGraph(){
        //vertex between 6 12
        this.V=(int)(Math.random() * ((12 - 6) + 1)) + 6;

        this.s=0;
        this.t=V-1;

        this. graph=new int[V][V];
        for(int i=0;i<graph.length;i++){
            for(int v=0;v<graph.length;v++){
                if(i==v || v==0 ||i==t||(i==s && v==t)||(graph[v][i]>0)){
                    graph[i][v]=0;
                }
                else{
                    //capacity between 20 and 5
                    graph[i][v]=(int)(Math.random() * ((20 - 5) + 1)) + 5;


                }

            }
        }
        System.out.println("THE GENERATED FLOW NETWORK");
        System.out.println("*****************************");
        System.out.println("Source(s)= "+s+" Sink(t)= "+t);
        System.out.println("-----------------------------");
        for(int i=0;i<graph.length;i++){
            for(int m=0;m<V;m++){
                System.out.print(graph[i][m]+" ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");



    }
    public void visualize(int[][] arr){
        JFrame frm = new JFrame();
        JPanel pan1 = new JPanel(new GridBagLayout());


        GridBagConstraints a = new GridBagConstraints();
        a.anchor = GridBagConstraints.CENTER;

        a.insets = new Insets(10, 10, 10, 10);

        for(int i=0;i<arr.length;i++) {
            a.gridx = 0;
            a.gridy = i+1;
            JLabel lblx = new JLabel("Edge "+String.valueOf(i)+" >>>> ");
            pan1.add(lblx,a);
            int m=1;
            for(int v=0;v<arr.length;v++) {

                if(arr[i][v]>0) {
                    a.gridx =m;
                    a.gridy =i+1;
                    JLabel lbla = new JLabel("Edge "+String.valueOf(v)+" C("+String.valueOf(arr[i][v])+")");
                    lbla.setForeground(Color.blue);
                    pan1.add(lbla,a);
                    m++;

                }
            }
        }

        frm.add(pan1);
        frm.add(pan1, BorderLayout.WEST);
        frm.setVisible(true);
        frm.setSize(500, 300);

    }

    public void graphView(int[][] arr){

        org.graphstream.graph.Graph graph1 = new SingleGraph("Graph View");


        for(int i=0;i<arr.length;i++) {
            Node d=graph1.addNode(String.valueOf(i));
            d.setAttribute("ui.label",d.getId());
        }
        for(int i=0;i<arr.length;i++) {
            for(int v=0;v<arr.length;v++) {
                if(arr[i][v]>0) {
                    Edge e=graph1.addEdge(String.valueOf(i+""+v),String.valueOf(i),String.valueOf(v),true);
                    e.addAttribute("ui.label",String.valueOf(arr[i][v]));
                }
            }
        }


        Viewer view =graph1.display();
        view.setCloseFramePolicy(CloseFramePolicy.CLOSE_VIEWER);
    }

    public void changeCapacity(){

        int node1 ,node2=0;
        System.out.println("Enter the Nodes To change Capacity");
        System.out.println("Node 1:");
        do{
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            node1= sc.nextInt();
        }while (!(node1>=0 && node1<=t));

        System.out.println("Node 2:");
        do {
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!

            }
            node2 = sc.nextInt();
        }while (!(node2>=0 && node2<=t));

        if(graph[node1][node2]>0){
            System.out.println("There is a connection");
            System.out.println("Currrent capacity is "+graph[node1][node2]);
            System.out.println("Enter the new Capacity");
            int capacity=graph[node1][node2];
            do{
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number!");
                    sc.next(); // this is important!
                }
                capacity= sc.nextInt();
            }while (!(capacity<=20 && capacity>0));
            graph[node1][node2]=capacity;

        }
        else{
            System.out.println("No Connection Exists");
        }


        }





}
