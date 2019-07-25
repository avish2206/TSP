# -*- coding: utf-8 -*-
"""
Created on Fri Jul 26 02:56:40 2019

@author: Aditya
"""
import numpy as np

def printRoute(route):
    for i in route:
        print(i, end=", ")


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

nodes = [0,1,2,3,4] # list of nodes

count = 0
min_dist = np.inf
unvisited0 = [x for x in nodes if x!=starting]
for loop1 in unvisited0:
    visited1 = [starting, loop1]
    dist1 = graph[starting][loop1]
    unvisited1 = [x for x in nodes if x not in visited1]
    for loop2 in unvisited1:
        visited2 = visited1 + [loop2] 
        dist2 = dist1+graph[loop1][loop2]
        unvisited2 = [x for x in nodes if x not in visited2]
        for loop3 in unvisited2:
            visited3 = visited2 + [loop3] 
            dist3 = dist2+graph[loop2][loop3]
            unvisited3 = [x for x in nodes if x not in visited3]
            for loop4 in unvisited3:
                visited4 = visited3 + [loop4]
                dist4 = dist3 + graph[loop3][loop4]
                overall = dist4 + graph[loop4][starting]
                if overall < min_dist:
                   min_dist = overall
                   best_route = visited4 + [starting]
                count+=1
                print('Soln %d %d' % (count,overall))
print("min distance route: %d" %min_dist)

printSet(best_route)


