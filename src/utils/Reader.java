package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

	private static BufferedReader reader;
	
	/**
	 * Reads map from file. 
	 */
	public static int[][] readMapFromFile(String path) {
		ArrayList<int[]> map = new ArrayList<int[]>();
		
		try {
			reader = new BufferedReader(new FileReader(path));
			String line;
			
			while ((line = reader.readLine()) != null) {
				int[] tiles = convertLineToArray(line);
				map.add(tiles);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error while reading map from file -> " + path);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while reading map from file -> " + path);
			e.printStackTrace();
		}
		
		return convertMapToMatrix(map);
	}
	
	/**
	 * Converts ArrayList<int[]> to int[][]
	 */
	private static int[][] convertMapToMatrix(ArrayList<int[]> map) {
		int[][] newMap = new int[map.size()][map.get(0).length];
	
		for (int i = 0; i < map.size(); ++i) {
			for (int j = 0; j < map.get(i).length; ++j) {
				newMap[i][j] = map.get(i)[j];
			}
		}
		
		return newMap;
	}
	
	/**
	 * Converts one line from file, to string array
	 * and then parses strings to integers.
	 */
	private static int[] convertLineToArray(String line) {
		String[] tokens = line.split(" ");
		int[] tiles = new int[tokens.length];

		for (int i = 0; i < tokens.length; ++i) {
			tiles[i] = Integer.parseInt(tokens[i]);
		}
		
		return tiles;
	}
}
