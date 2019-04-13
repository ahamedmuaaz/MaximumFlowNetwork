import javax.swing.*;
import java.awt.*;

public class Graph {
    public int V;

    int s;
    int t;
    int [][] graph;


    public Graph(){
        V=(int)(Math.random() * ((12 - 6) + 1)) + 6;
       // V=6;
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
                if(i==v || v==0 ||i==t||(i==s && v==t)||graph[v][i]>0){
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
    public void visualize(int[][] arr){
        JFrame frm = new JFrame();
        JPanel pan1 = new JPanel(new GridBagLayout());

        JLabel lbl1 = new JLabel("0_29");
        JLabel lbl2 = new JLabel("30_39");
        JLabel lbl3 = new JLabel("40_69");
        JLabel lbl4 = new JLabel("70_100");

        //JLabel lbla = new JLabel("n");
        JLabel lblb = new JLabel("a");
        JLabel lblc = new JLabel("m");
        JLabel lbld = new JLabel("s");

        GridBagConstraints a = new GridBagConstraints();
        a.anchor = GridBagConstraints.CENTER;

        a.insets = new Insets(10, 10, 10, 10);
        //int h=0;
        //int v=0;

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
		/*a.gridx = 0;
		a.gridy = 1;
		pan1.add(lbl1, a);
		a.gridx = 0;
		a.gridy = 2;
		pan1.add(lbl2, a);
		a.gridx = 0;
		a.gridy = 3;
		pan1.add(lbl3, a);
		a.gridx = 0;
		a.gridy = 4;
		pan1.add(lbl4, a);

		a.gridx = 1;
		a.gridy = 1;
		pan1.add(lbla, a);

		a.gridx = 1;
		a.gridy = 2;
		pan1.add(lblb, a);

		a.gridx = 1;
		a.gridy = 3;
		pan1.add(lblc, a);

		a.gridx = 1;
		a.gridy = 4;
		pan1.add(lbld, a);*/

        frm.add(pan1);
        frm.add(pan1, BorderLayout.WEST);
        frm.setVisible(true);
        frm.setSize(500, 300);

    }


}
