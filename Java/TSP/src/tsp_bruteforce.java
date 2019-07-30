import java.util.Arrays;

public class tsp_bruteforce {
	
	public static class Path{
		public int [][] graph;
		public int start;
		public int[] nodes;
		public int count;
		public int[] best_route;
		public int min_dist;
		
		public Path(int[][] graph, int start) {
			this.graph = graph;
			this.start = start;

			this.count = 0;
			this.best_route = new int[graph.length];
			this.min_dist = Integer.MAX_VALUE;
			
			this.nodes = new int[graph.length];
			for(int i=0; i<graph.length; i++) {
				this.nodes[i] = i;
			}
		}
	}
	
	public static void main(String[] args) {
		
		int[][] graph = {
				{Integer.MAX_VALUE, 10, 8, 9, 7},
				{10, Integer.MAX_VALUE, 10, 5, 6},
				{8, 10, Integer.MAX_VALUE, 8, 9},
				{9, 5, 8, Integer.MAX_VALUE, 6},
				{7, 6, 9, 6, Integer.MAX_VALUE}
		};
		
		int starting = 0;
		
		Path myPath = new Path(graph,starting);
		
		int[] visited = {starting};
		int[] unvisited = setDiff(myPath.nodes,starting);
		int dist = 0;
		
		calcRoute(myPath,visited,unvisited,dist);
		System.out.println(Arrays.toString(myPath.best_route));
		System.out.println("MINUMUM DISTANCE: "+myPath.min_dist);
	}
	
	
	// Calculate route using brute force (check every single solution)
	public static void calcRoute(Path myPath, int[] visited, int[] unvisited, int dist) {
		if (unvisited.length == 1) {
			int overall = dist + myPath.graph[visited[visited.length-1]][unvisited[0]] + myPath.graph[unvisited[0]][myPath.start];
			int[] temp_visited = setAdd(visited,unvisited[0]);
			if(overall < myPath.min_dist) {
				myPath.min_dist = overall;
				myPath.best_route = setAdd(temp_visited,myPath.start);
			}
			myPath.count++;
			System.out.println("Solution "+myPath.count+": Distance = "+ overall);
		} else if (unvisited.length == myPath.nodes.length-1) {
			for(int loop : unvisited) {
				int temp_dist = myPath.graph[myPath.start][loop];
				int[] temp_visited = {myPath.start, loop};
				int[] temp_unvisited = setDiff(myPath.nodes, temp_visited);
				calcRoute(myPath,temp_visited,temp_unvisited,temp_dist);
			}
		} else {
			for(int loop : unvisited) {
				int temp_dist = dist + myPath.graph[visited[visited.length-1]][loop];
				int[] temp_visited = setAdd(visited,loop);
				int[] temp_unvisited = setDiff(myPath.nodes, temp_visited);
				calcRoute(myPath,temp_visited,temp_unvisited,temp_dist);
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
