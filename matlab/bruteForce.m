% Brute force calculation - recursive function
function bruteForce(graph,starting,nodes,visited,unvisited,dist)

global count;
global min_dist;
global best_route;

% BASE CASE: final route
if length(unvisited)==1
    overall = dist + graph(visited(end),unvisited) + graph(unvisited,starting);
    temp_visited = [visited, unvisited];
    if overall < min_dist
        min_dist = overall;
        best_route = [temp_visited, starting];
    end
    count = count + 1;
    fprintf('Soln %d\nRoute: ',count);
    printRoute([temp_visited,1]);
    fprintf('Distance: %d\n\n',overall);
   
% FIRST CASE: first route
elseif length(unvisited)==length(nodes)-1
    for loop = unvisited
        temp_dist = graph(starting,loop);
        temp_visited = [starting,loop];
        unvisited = setdiff(nodes,temp_visited);
        bruteForce(graph,starting,nodes,temp_visited,unvisited,temp_dist);
    end
    
% EVERY OTHER CASE
else
    for loop = unvisited
        temp_dist = dist + graph(visited(end),loop);
        temp_visited = [visited,loop];
        unvisited = setdiff(nodes,temp_visited);
        bruteForce(graph,starting,nodes,temp_visited,unvisited,temp_dist);
    end
end

             


