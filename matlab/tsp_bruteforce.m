%% TSP (travelling salesman problem)
% solved using brute force - try every single option (recursion)

clc; clear; close all;

global count;
global min_dist;
global best_route;

%% INPUT GRAPH HERE - DISTANCE MATRIX + STARTING NODE %%%%%%%%%%%%%%%%%%%%%
% example matrix    
%nodes:  1    2    3    4    5
graph = [nan, 10,  8,   9,   7;    %1
         10,  nan, 10,  5,   6;    %2
         8,   10,  nan, 8,   9;    %3
         9,   5,   8,   nan, 6;    %4
         7,   6,   9,   6,   nan]; %5
     
starting = 1; % starting node
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


nodes = 1:length(graph); % array of nodes

count = 0; % solution count
min_dist = inf; % minimum distance route
best_route = []; % best solution

dist = 0; % initialize distance
visited = starting; % initialize visited nodes with starting point
unvisited = setdiff(nodes,visited); % intialize unvisited set of nodes

% search all options -recursive call
bruteForce(graph,starting,nodes,visited,unvisited,dist); 

% print best results
fprintf('BEST ROUTE: '); printRoute(best_route);
fprintf('DISTANCE: %d\n',min_dist);


