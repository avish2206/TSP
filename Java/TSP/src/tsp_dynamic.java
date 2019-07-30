import java.util.Arrays;

public class tsp_dynamic {
	
	public static void main(String[] args) {
		
		int[][] graph = {
				{Integer.MAX_VALUE, 10, 8, 9, 7},
				{10, Integer.MAX_VALUE, 10, 5, 6},
				{8, 10, Integer.MAX_VALUE, 8, 9},
				{9, 5, 8, Integer.MAX_VALUE, 6},
				{7, 6, 9, 6, Integer.MAX_VALUE}
		};
		
		int starting = 0;
		
		int[] nodes = new int[graph.length];
		for(int i=0; i<nodes.length; i++) {
			nodes[i] = i;
		}
		
		int[] unvisited = setDiff(nodes,starting);
		
		// initialize distance matrix
		int[][] dist = new int[nodes.length][nodes.length];
		int[][] prev = new int[nodes.length][nodes.length];
		for(int i=0; i<nodes.length; i++) {
			for(int j=0; j<nodes.length; j++) {
				dist[i][j] = Integer.MAX_VALUE;
				if(j==0) {
					prev[i][j] = 0;
				} else {
					prev[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		calcRoute(graph,starting,nodes,dist,prev,unvisited);
	}
	
	
	// Calculate route using dynamic programming
	public static void calcRoute(int[][] graph,int starting, int[] nodes, int[][] dist, int[][] prev, int[] unvisited) {
		for(int i=0; i<nodes.length; i++) {
			
			if(i==0) {
				for(int loop: unvisited) {
					dist[loop][i] = graph[loop][starting];
				}
			
			} else if (i==nodes.length-1) {
				int minval = Integer.MAX_VALUE;
				int idx    = -1;
				for (int j=0; j<nodes.length; j++) {
					if(dist[j][i-1]==Integer.MAX_VALUE || graph[starting][j]==Integer.MAX_VALUE) {dist[j][i]=Integer.MAX_VALUE;}
					else{dist[j][i] = Math.abs(dist[j][i-1] + graph[starting][j]);}
					if(dist[j][i] < minval) {
						minval = dist[j][i];
						idx    = j;
					}
				}
				int[] route = new int[nodes.length+1];
				route[0] = starting;
				route[1] = idx;
				int nextnode = prev[idx][i-1];
				int count = 2;
				for(int j=i-2; j>=0; j--) {
					route[count++] = nextnode;
					nextnode = prev[nextnode][j];
				}
				route[count] = nextnode;
				System.out.println(Arrays.toString(route));
				System.out.println("MINIMUM DISTANCE: "+minval);
			
			} else {
				for(int loop:unvisited) {
					int minval = Integer.MAX_VALUE;
					int idx = -1;
					int[] vec = setDiff(unvisited,loop);
					for(int j: vec) {
						int temp = Math.abs(dist[j][i-1] + graph[loop][j]);
						int[] temp_visited = new int[i];
						int count =0;
						boolean flag = false;
						for(int k=i-1; k>=0; k--) {
							temp_visited[count++] = prev[j][k];
							if(prev[j][k]==loop) {
								flag = true;
							}
						}
						if(!flag && temp<minval) {
							minval = temp;
							idx = j;
						}
					}
					dist[loop][i] = minval;
					prev[loop][i] = idx;
				}
			}
		}
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
		for(int i=0; i<nodes1.length; i++) {			
			if(nodes2.length==0) {
				if(idx_count==0) {
					output[0] = nodes1[i];
					idx_count++;
					continue;
				}
				output = setAdd(output,nodes1[i]);
				continue;
			}
			
			for(int j=0; j<nodes2.length; j++) {
				if(nodes1[i]==nodes2[j]) {
					nodes2 = removeTheElement(nodes2,j);
					break;
				}
				if(j==nodes2.length-1) {
					if(idx_count==0) {
						output[0] = nodes1[i];
						idx_count++;
					} else {
						output = setAdd(output,nodes1[i]);
					}
				}
			}
		}
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
	
	// Remove an element from an array
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
