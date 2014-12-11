package game.states;

import javax.swing.*;
import java.io.*;

/**
 * ARTURO POLANCO CARRILLO
 * 01200720
 * 10/12/2014
 * Juego
 */
public class Score {
	public static String score;

	public static void leer(){
		FileReader fr = null;
		BufferedReader br;

		try{
		   fr = new FileReader( new File("best score.txt") );
			br = new BufferedReader( fr );
			score = br.readLine();
		}catch ( Exception e  ){
			e.printStackTrace();
		}finally {
			try{
				if(null != fr)
					fr.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}

	}

	public static String getScore(){
		FileReader fr = null;
		BufferedReader br;

		try{
			fr = new FileReader( new File("best score.txt") );
			br = new BufferedReader( fr );
			score = br.readLine();
		}catch ( Exception e  ){
			e.printStackTrace();
		}finally {
			try{
				if(null != fr)
					fr.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return score;
	}
	public static void mostrarRecords(){

		leer(  );

	 JTextArea areaText = new JTextArea();

		areaText.setText( score );

		JOptionPane.showMessageDialog( null , areaText, "Los Mejores" , JOptionPane.INFORMATION_MESSAGE );
	}


	 public static void escribir(String record){
		 FileWriter archivo= null;
		 PrintWriter pw;
		 try{
			 archivo= new FileWriter("best score.txt");
			 pw= new PrintWriter( archivo );
			 pw.println(record);
		 }catch ( Exception e ){
			 e.printStackTrace();
		 }finally {
			 try{
				 if(null != archivo)
					 archivo.close();
			 }catch(Exception e2){
				 e2.printStackTrace();
			 }
		 }
	 }
}
