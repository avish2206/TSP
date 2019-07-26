%% TSP (travelling salesman problem)
% solved using dp - try every single option but use memory (BACKCWARDS)
% formula: g(i,s) = min_{k in S} ( C_{ik} +g(k,S-{k}) )

clc; clear; close all;

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

unvisited = setdiff(nodes,starting); % set of nodes left to visit

dist = nan(length(nodes), length(nodes)); % distances to store in mem
prev = nan(length(nodes), length(nodes)); % previous best nodes

prev(:,1) = starting; % all final steps point to starting

% for loop iterating through each decision (each level in the decision tree)
for i = 1:size(dist,2)
   if i==1
      for loop = unvisited
          dist(loop,i) = graph(loop,starting);
      end
   elseif i==size(dist,2)
       dist(:,i) =dist(:,i-1)+graph(starting,:)';
       min_dist = min(dist(:,i));
       idx = find(dist(:,i)==min_dist);
       route = starting;
       next = prev(idx,i-1);
       for j = i-2:-1:1
           route = [route, next];
           next = prev(next,j);
       end
       route = [route, next];
       fprintf('Best Route: '); fprintf(repmat('%d ',1,length(nodes)+1),route);
       fprintf('\nDistance: %d\n',min_dist);
   else
      for loop = unvisited
          min_dist = inf;
          idx = nan;
          for j = setdiff(unvisited,loop)
              temp = dist(j,i-1) + graph(loop,j);
              visited = [];
              for k = i-1:-1:1
                  visited = [visited, prev(j,k)]; 
              end
              if sum(ismember(visited,loop))==0 && temp < min_dist
                  min_dist = temp;
                  idx = j;
              end
          end
          dist(loop,i) = min_dist;
          prev(loop,i) = idx;
      end
   end
end
      
             


