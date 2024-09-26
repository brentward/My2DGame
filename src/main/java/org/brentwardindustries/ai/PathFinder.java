package org.brentwardindustries.ai;

import org.brentwardindustries.main.GamePanel;
import org.brentwardindustries.tileinteractive.InteractiveTile;

import java.util.ArrayList;

public class PathFinder {
    GamePanel gp;
    Node[][] nodes;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes() {
        nodes = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            nodes[col][row] = new Node(col, row);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            nodes[col][row].open = false;
            nodes[col][row].checked = false;
            nodes[col][row].solid = false;
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        startNode = nodes[startCol][startRow];
        currentNode = startNode;
        goalNode = nodes[goalCol][goalRow];
        openList.add(currentNode);
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            // SET SOLID NODE
            // CHECK TILES
            int tileNum = gp.tileManager.mapTileNum[gp.currentMap][col][row];
            if (gp.tileManager.tile[tileNum].collision) {
                nodes[col][row].solid = true;
            }
            // CHECK INTERACTIVE TILES
            for (InteractiveTile interactiveTile : gp.interactiveTiles[gp.currentMap]) {
                if (interactiveTile != null && interactiveTile.destructible) {
                    int itCol = interactiveTile.worldX / gp.tileSize;
                    int itRow = interactiveTile.worldY / gp.tileSize;
                    nodes[itCol][itRow].solid = true;
                }
            }
            // GET COST
            getCost(nodes[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node) {
        // GET G COST
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        // GET H COST
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        // GET F COST
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (!goalReached && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            // OPEN THE UP NODE
            if (row - 1 >= 0) {
                openNode(nodes[col][row - 1]);
            }
            // OPEN THE LEFT NODE
            if (col - 1 >= 0) {
                openNode(nodes[col - 1][row]);
            }
            // OPEN THE DOWN NODE
            if (row + 1 < gp.maxWorldRow) {
                openNode(nodes[col][row + 1]);
            }
            // OPEN THE RIGHT NODE
            if (col + 1 < gp.maxWorldCol) {
                openNode(nodes[col + 1][row]);
            }
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for (int i = 0; i < openList.size(); i++) {
                // Check if this node's F cost is better
                if (openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodeFCost) { // if F cost is equal check the G cost
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            if (openList.isEmpty()) {
                break;
            }
            // After the loop, we get the best node which is nour next step
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath() {
        Node current = goalNode;
        while (current != startNode) {
            pathList.addFirst(current);
            current = current.parent;
        }
    }
}
