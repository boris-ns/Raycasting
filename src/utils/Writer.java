package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	private static PrintWriter writer;
	
	/**
	 * Writes matrix of int's (map) to file at specified path. 
	 */
	public static void writeMapToFile(int[][] map, String path) {
		try {
			writer = new PrintWriter(new FileWriter(path));
			
			for (int i = 0; i < map.length; ++i) {
				for (int j = 0; j < map[i].length; ++j) {
					writer.print(map[i][j]);
				}
				
				writer.println();
			}
			
		} catch (IOException e) {
			System.out.println("Error while writing map to the file -> " + path);
			e.printStackTrace();
		}
		
		System.out.println("File successfully written!");
		writer.close();
	}
}
