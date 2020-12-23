

import java.io.*;
import java.util.*;
import java.net.*;
public class Client{
    public static void main(String[] args) throws Exception {
        DatagramSocket ds=new DatagramSocket();
        System.out.println("Client is online");
        
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the file to be received from the server:");
        String fname=sc.nextLine();
        File f1=new File(fname);
        byte[] b1=fname.getBytes();
        InetAddress ia= InetAddress.getLocalHost();
        DatagramPacket dp1=new DatagramPacket(b1,b1.length,ia,6666);
        ds.send(dp1);
        byte[] b2=new byte[1024];
        DatagramPacket dp2=new DatagramPacket(b2,b2.length);
        ds.receive(dp2);
        int isthere=Integer.parseInt(new String(dp2.getData()).trim());
        if(isthere==1){
            String inputline;
            FileWriter fw=new FileWriter(f1);
            do{
                byte[] b3=new byte[1024];
                DatagramPacket dp3=new DatagramPacket(b3,b3.length);
                ds.receive(dp3);
                inputline=new String(dp3.getData(),0,dp3.getLength());
                fw.write(inputline);
                fw.flush();
            }while(!inputline.equals(null));
            System.out.println("File received");
            fw.close();
        }
        
        else{
            System.out.println("The file is not there in the server");
            f1.delete();
        }
        
        sc.close();
        ds.close();


        
    }
}