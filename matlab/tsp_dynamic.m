%% TSP (travelling salesman problem)
% solved using dp - try every single option but use memory (BACKCWARDS)
% formula: g(i,s) = min_{k in S} ( C_{ik} +g(k,S-{k}) )

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

unvisited = setdiff(nodes,starting);

% last step
dist1(1) = nan;
for loop1 = unvisited
    dist1(loop1) = graph(loop1,1); 
end

% second last step
dist2(1,:) = nan;
for loop2 = unvisited
    min_dist = inf;
    for i = setdiff(unvisited,loop2)
        dist = dist1(i) + graph(loop2,i);
        if(dist < min_dist)
           min_dist = dist;
           idx = i;
        end
    end
    dist2(loop2,1) = min_dist;
    dist2(loop2,2) = idx;
end

% second last step
dist3(1,:) = nan;
for loop3 = unvisited
    min_dist = inf;
    for i = setdiff(unvisited,[loop3])
        dist = dist2(i,1) + graph(loop3,i);
        next = dist2(i,2);
        if (dist < min_dist && next~=loop3)
           min_dist = dist;
           idx = i;
        end
    end
    dist3(loop3,1) = min_dist;
    dist3(loop3,2) = idx;
end

% third last step
dist4(1) = nan;
for loop4 = unvisited
    min_dist = inf;
    for i = setdiff(unvisited,[loop4])
        dist = dist3(i,1) + graph(loop4,i);
        next = dist3(i,2);
        next2 = dist2(next,2);
        if(dist < min_dist && next~=loop4 && next2~=loop4)
           min_dist = dist;
           idx = i;
        end
    end
    dist4(loop4,1) = min_dist;
    dist4(loop4,2) = idx;
end

% last step
dist5 = dist4;
dist5(2:end,1) = dist5(2:end,1) + graph(1,2:end)';
min_dist = min(dist5(:,1));
idx = find(dist5(:,1)==min_dist);
next = dist4(idx,2);
next2 = dist3(next,2);
next3 = dist2(next2,2);

fprintf('BEST ROUTE: %d %d %d %d %d %d\nDISTANCE: %d\n',1,idx,next,...
          next2,next3,1,min_dist);
      
             


