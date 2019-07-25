%% TSP (travelling salesman problem)
% solved using brute force - try every single option (tree structure)
%             1
%      2    3    4    5   (for loop 1)
%     345  245  235  234  (for loop 2)
%  45 35 34
%  ...................................24 TOTAL SOLUTIONS! (4P4)

clc; clear; close all;

% matrix    
%nodes:  1    2    3    4    5
graph = [nan, 10,  8,   9,   7;    %1
         10,  nan, 10,  5,   6;    %2
         8,   10,  nan, 8,   9;    %3
         9,   5,   8,   nan, 6;    %4
         7,   6,   9,   6,   nan]; %5
     
starting = 1; % starting node

nodes = [1,2,3,4,5]; % array of nodes

count = 0;
min_dist = inf;
unvisited0 = setdiff(nodes,starting);
for loop1 = unvisited0 
    visited1 = [starting, loop1];
    dist1 = graph(1,loop1);
    unvisited1 = setdiff(nodes, visited1);
    for loop2 = unvisited1
        visited2 = [visited1, loop2];
        dist2 = dist1 + graph(loop1,loop2);
        unvisited2 = setdiff(nodes,visited2);
        for loop3 = unvisited2
         visited3 = [visited2, loop3];
         dist3 = dist2 + graph(loop2,loop3);
         unvisited3 = setdiff(nodes,visited3);           
         for loop4 = unvisited3
             visited4 = [visited3,loop4];
             dist4 = dist3 + graph(loop3,loop4);
             overall = dist4 + graph(loop4,1);
             if overall<min_dist
                 min_dist = overall;
                 best_route = [visited4,1]; 
             end
             count = count + 1;
             fprintf('Soln %d\nRoute %d %d %d %d %d %d\nDistance: %d\n\n', ...
               count, visited4(1), visited4(2), visited4(3), visited4(4), visited4(5), 1,dist4+graph(loop4,1));
         end
        end
    end
end

fprintf('BEST ROUTE: %d %d %d %d %d %d\nDISTANCE: %d\n',best_route(1),best_route(2),best_route(3),...
          best_route(4),best_route(5),best_route(6),min_dist);
             


