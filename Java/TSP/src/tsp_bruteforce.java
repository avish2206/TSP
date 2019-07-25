import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class tsp_bruteforce {
	public static void main(String[] args) {
		int[][] graph = {
				{Integer.MAX_VALUE, 10, 8, 9, 7},
				{10, Integer.MAX_VALUE, 10, 5, 6},
				{8, 10, Integer.MAX_VALUE, 8, 9},
				{9, 5, 8, Integer.MAX_VALUE, 6},
				{7, 6, 9, 6, Integer.MAX_VALUE}
		};
		
		int starting = 1;
		
		int[] nodes = {1,2,3,4,5};

		calcRoute(graph,starting,nodes);
	}
	
	
	// Calculate route using brute force (check every single solution)
	public static void calcRoute(int[][] graph, int starting, int[] nodes) {
		int count = 0;
		int min_dist = Integer.MAX_VALUE;
		int[] unvisited0 = setDiff(nodes,starting);
		int[] best_route = new int[nodes.length+1];
		for(int i=0; i<unvisited0.length; i++) {
			int loop1 = unvisited0[i];
			int[] visited1 = setAdd(starting,loop1);
			int dist1 = graph[starting][loop1];
			int[] unvisited1 = setDiff(nodes, visited1);
			System.out.println("nodes: "+Arrays.toString(nodes)+", visited1: "+Arrays.toString(visited1)+", unvisited1: "+Arrays.toString(unvisited1));
			for(int j=0; i<unvisited1.length; j++) {
				int loop2 = unvisited1[j];
				int[] visited2 = setAdd(visited1,loop2);
				int dist2 = dist1 + graph[loop1][loop2];
				int[] unvisited2 = setDiff(nodes, visited2);
				for(int k=0; k<unvisited2.length; k++) {
					int loop3 = unvisited2[k];
					int[] visited3 = setAdd(visited2,loop3);
					int dist3 = dist2 + graph[loop2][loop3];
					int[] unvisited3 = setDiff(nodes, visited3);
					for(int l=0; l<unvisited3.length; l++) {
						int loop4 = unvisited3[l];
						int[] visited4 = setAdd(visited3,loop4);
						int dist4 = dist3 + graph[loop3][loop4];
						int overall = dist4 + graph[loop4][starting];
						if(overall < min_dist) {
							min_dist = overall;
							best_route = setAdd(visited4,1);
						}
						count++;
						System.out.println("Soln "+count+", Distance: "+dist4+graph[loop4][starting]);
					}
				}
			}
		}
		
		System.out.println("BEST ROUTE: "+ Arrays.toString(best_route)+", DISTANCE: "+min_dist);
		
	}
	
	// Removing a single element from an array
	public static int[] setDiff(int[] nodes, int node) {
		int[] output = new int[nodes.length-1];
		int idx_count = 0;
		for(int i=0; i<nodes.length; i++) {
			if(nodes[i]!=node) {
				output[idx_count] = nodes[i];
				idx_count++;
			}
		}
		return output;
	}
	
	// Removing a set of nodes from an array
	public static int[] setDiff(int[] nodes1, int[] nodes2) {
		int[] output = new int[1];
		int idx_count = 0;
		int jstart = 0;
		for(int i=0; i<nodes1.length; i++) {
			for(int j=0; j<nodes2.length; j++) {
				if(nodes1[i]==nodes2[j]) {
					System.out.println(nodes2[j]);
					nodes2 = removeTheElement(nodes2,j);
					break;
				} else {
					if(idx_count==0) {
						output[0] = nodes1[i];
						idx_count++;
						//System.out.println(Arrays.toString(output));
						break;
					}
					output = setAdd(output,nodes1[i]);
					//System.out.println(Arrays.toString(output));
					break;
				}
			}
			
			if(nodes2.length==0) {
				System.out.println(i);
				if(idx_count==0) {
					output[0] = nodes1[i];
					idx_count++;
					break;
				}
				output = setAdd(output,nodes1[i]);
				break;
			}
			//System.out.println(Arrays.toString(output));	
		}
		System.out.println(Arrays.toString(output));
		return output;
	}
	
	// Add a node to an array
	public static int[] setAdd(int[] nodes, int node) {
		int[] output = new int[nodes.length+1];
		for(int i=0; i<nodes.length; i++) {
			output[i] = nodes[i];
		}
		output[nodes.length] = node;
		return output;
	}
	
	// Add two numbers to an array
	public static int[] setAdd(int node1, int node2) {
		int[] output = new int[2];
		output[0] = node1;
		output[1] = node2;
		return output;
	}
	
	// Function to remove the element 
    public static int[] removeTheElement(int[] arr, 
                                          int index) 
    { 
  
        // If the array is empty 
        // or the index is not in array range 
        // return the original array 
        if (arr == null
            || index < 0
            || index >= arr.length) { 
  
            return arr; 
        } 
  
        // Create another array of size one less 
        int[] anotherArray = new int[arr.length - 1]; 
  
        // Copy the elements except the index 
        // from original array to the other array 
        for (int i = 0, k = 0; i < arr.length; i++) { 
  
            // if the index is 
            // the removal element index 
            if (i == index) { 
                continue; 
            } 
  
            // if the index is not 
            // the removal element index 
            anotherArray[k++] = arr[i]; 
        } 
  
        // return the resultant array 
        return anotherArray; 
    } 
}
