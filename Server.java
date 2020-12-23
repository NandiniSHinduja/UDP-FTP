

import java.io.*;
import java.net.*;
import java.util.*;
public class Server {
    public static void main (String args[]) throws Exception{
         DatagramSocket ds=new DatagramSocket(6666);
         System.out.println("Server is online");
         File newfile=new File("hello.txt");
         Scanner sc=new Scanner(newfile);
         int isthere=0;
         byte[] b1=new byte[1024];
         DatagramPacket dp1=new DatagramPacket(b1,b1.length);
         ds.receive(dp1);
         String fname=new String(dp1.getData(),0,dp1.getLength());
         if(fname.equals(newfile.getName())){
             isthere=1;
             String isthr= String.valueOf(isthere);
             byte[] b2=isthr.getBytes();
             DatagramPacket dp2=new DatagramPacket(b2,b2.length,dp1.getAddress(),dp1.getPort());
             ds.send(dp2);
             System.out.println("File exists");
             String inputline;
             while(sc.hasNextLine()){
                 inputline=sc.nextLine();
                 byte[] b3=inputline.getBytes();
                 DatagramPacket dp3=new DatagramPacket(b3,b3.length,dp1.getAddress(),dp1.getPort());
                 ds.send(dp3);

             }

         }
         else{
             System.out.println("File not found");
             String isthr= String.valueOf(isthere);
             byte[] b4=isthr.getBytes();
             DatagramPacket dp3=new DatagramPacket(b4,b4.length,dp1.getAddress(),dp1.getPort());
             ds.send(dp3);
         }
         sc.close();
         ds.close();
         

    }
    
}
