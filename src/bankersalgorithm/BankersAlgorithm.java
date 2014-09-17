/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankersalgorithm;

import java.util.Scanner;

public class BankersAlgorithm{
    private int need[][],allocate[][],max[][],avail[][],sequence[],np,nr,p[],count;
    
    private void input(){
     Scanner sc=new Scanner(System.in);
     System.out.println("Enter no. of processes and resources : ");
     np=sc.nextInt();  //no. of process
     nr=sc.nextInt();  //no. of resources
     need=new int[np][nr];  //initializing arrays
     max=new int[np][nr];
     allocate=new int[np][nr];
     avail=new int[1][nr];
     sequence = new int [np];
     p = new int [nr];
     count = 0;
     
     System.out.println("Enter the number of instances :");
     for(int j=0;j<nr;j++)
        avail[0][j]=sc.nextInt();  //available matrix
     
     System.out.println("Enter allocation table :");
     for(int i=0;i<np;i++)
          for(int j=0;j<nr;j++)
         allocate[i][j]=sc.nextInt();  //allocation matrix
      
     System.out.println("Enter max table :");
     for(int i=0;i<np;i++)
          for(int j=0;j<nr;j++)
         max[i][j]=sc.nextInt();  //max matrix
        
     sc.close();
     
     for(int i = 0; i<np; i++){
         p[0] += allocate[i][0];
         p[1] += allocate[i][1];
         p[2] += allocate[i][2];
     }
      
    }
    
    private int[][] calc_need(){
       for(int i=0;i<np;i++)
         for(int j=0;j<nr;j++)  //calculating need matrix
          need[i][j]=max[i][j]-allocate[i][j];
       
       return need;
    }
 
    private boolean check(int i){
       //checking if all resources for ith process can be allocated
       for(int j=0;j<nr;j++) 
       if(((avail[0][j])-(p[j]))<need[i][j])
          return false;
   
    return true;
    }

    public void isSafe(){
       input();
       calc_need();
       boolean done[]=new boolean[np];
       int j=0;

       while(j<np){  //until all process allocated
       boolean allocated=false;
       for(int i=0;i<np;i++)
        if(!done[i] && check(i)){  //trying to allocate
            for(int k=0;k<nr;k++)
            avail[0][k]=avail[0][k]-need[i][k]+max[i][k];
         sequence[count] = (i);
         count++;
         allocated=done[i]=true;
               j++;
             }
          if(!allocated) break;  //if no allocation
       }
       if(j==np){  //if all processes are allocated
        System.out.print("The safe sequence is : ");
        for(int i = 0; i<np; i++){
            System.out.print("  " + sequence[i]);
        }
       }
       else
        System.out.println("Unsafe State");
    }
    
    public static void main(String[] args) {
  new BankersAlgorithm().isSafe();
    }
}
