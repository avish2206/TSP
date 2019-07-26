# -*- coding: utf-8 -*-
"""
Created on Fri Jul 26 02:56:40 2019

@author: Aditya
"""
import numpy as np

class Path:
    
    def __init__(self, graph,starting):
        self.graph = graph;
        self.starting = starting
        self.nodes = list(range(0,len(graph[0])))
        self.count = 0
        self.best_route = []
        self.min_dist = np.inf

def bruteForce(path, visited, unvisited, dist):
    if len(unvisited)==1:
        overall = dist + path.graph[visited[-1]][unvisited[0]] + path.graph[unvisited[0]][path.starting]
        temp_visited = visited + unvisited
        if int(overall) < path.min_dist:
            path.min_dist = overall
            path.best_route = temp_visited + [starting]
        path.count += 1
        print("Solution %d: Distance %d"%(path.count,overall))
    elif (len(unvisited)==len(path.nodes)-1):
        for loop in unvisited:
            temp_dist = path.graph[starting][loop]
            temp_visited = [path.starting,loop]
            temp_unvisited = [x for x in path.nodes if x not in temp_visited]
            bruteForce(path,temp_visited,temp_unvisited,temp_dist)
    else:
        for loop in unvisited:
            temp_dist = dist + path.graph[visited[-1]][loop]
            temp_visited = visited + [loop]
            temp_unvisited = [x for x in path.nodes if x not in temp_visited]
            bruteForce(path,temp_visited,temp_unvisited,temp_dist)
        
def printRoute(route):
    for i in route:
        print(i, end=", ")
    print('\n')

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

path = Path(graph,starting) 
dist = 0
visited = [starting]
unvisited = [x for x in path.nodes if x not in visited]
bruteForce(path, visited, unvisited, dist)
print(path.best_route)
print(path.min_dist)