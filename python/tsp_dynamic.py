# -*- coding: utf-8 -*-
"""
Created on Sat Jul 27 02:45:32 2019

@author: Aditya
"""

# -*- coding: utf-8 -*-
"""
Created on Fri Jul 26 02:56:40 2019

@author: Aditya
"""
import numpy as np

def getMin(nums):
    minval = np.inf
    idx = np.nan
    for i in range(0,len(nums)):
        if nums[i]<minval:
            minval = nums[i]
            idx = i
    return [minval,idx]

# CHANGE THESE - - - - - - - - - - - - - - - -
# distance matrix
graph = np.array([[np.nan, 10, 8, 9, 7],
                 [10, np.nan, 10, 5, 6],
                 [8, 10, np.nan, 8, 9],
                 [9, 5, 8, np.nan, 6],
                 [7, 6, 9, 6, np.nan]])
# starting node
starting = 0  
# - - - - - - - - - - - - - - - - - - - - - - -

# list of nodes
nodes = list(range(0,len(graph[0])))

# initialize distance matrix
dist = np.empty((len(graph[0]),len(graph[0])))
dist[:] = np.nan

# initialize previous matrix
prev = np.empty((len(graph[0]),len(graph[0])))
prev[:] = np.nan
prev[:,0] = 0

# define starting point
visited = [starting]

# define unvisited nodes 
unvisited = [x for x in nodes if x not in visited]

for i in nodes:
    if i==0:
        for loop in unvisited:
            dist[loop,i] = graph[loop,starting]
    elif i==nodes[-1]:
        dist[:,i] = dist[:,i-1] + np.transpose(graph[starting,:])
        [minval,idx] = getMin(dist[:,i])
        route = [starting,idx]
        nextnode = int(prev[idx,i-1]) 
        for j in list(range(i-2,0,-1)):
            route = route + [nextnode]
            nextnode = int(prev[nextnode,j])
        route = route + [nextnode]
        print(route)
        #printRoute(route)
        print(minval)
    else:
        for loop in unvisited:
            min_dist = np.inf
            idx = np.nan
            vec = [x for x in unvisited if x not in [loop]]
            for j in vec:
                temp = dist[j,i-1] + graph[loop,j]
                visited = []
                for k in list(range(i-1,0,-1)):
                    visited = visited + [prev[j,k]]
                if loop not in visited and temp < min_dist:
                    min_dist = temp
                    idx = j
            dist[loop,i] = min_dist
            prev[loop,i] = idx
                
