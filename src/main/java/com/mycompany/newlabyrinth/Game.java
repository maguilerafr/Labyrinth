package com.mycompany.newlabyrinth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {

    private ArrayList<String[]> labyrinth = new ArrayList();
    ArrayList<Position> route = new ArrayList<>();

    public ArrayList<String[]> readFromFile() {
        String file = "C:\\Users\\loloa\\Escritorio"
                + "\\Máster Programación y Desarrollo de Aplicaciones"
                + "\\Introducción a la programación\\laberintos\\lab2\\laberinto1.txt";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "UTF-8"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splittedLine = line.split("");
                this.labyrinth.add(splittedLine);
            }
            return this.labyrinth;

        } catch (IOException e) {
            System.err.println("Error al leer datos desde el archivo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void print() {
        ArrayList<String[]> laben2 = new ArrayList<>();

        for (int i = 0; i < labyrinth.size(); i++) {
            laben2.add((String[]) labyrinth.get(i).clone());
        }
        
        for (int i = 0; i < route.size(); i++) {
            
            laben2.get(route.get(i).getX())[route.get(i).getY()] = "*";
        }

        for (int i = 0; i < labyrinth.size(); i++) {
            for (int j = 0; j < laben2.get(i).length; j++) {
                String[] row = laben2.get(i);
                for (int k = 0; k < row[j].length(); k++) {
                    System.out.print(row[j].charAt(k));
                }
            }
            System.out.println();
        }
    }

    public Position findEntrance() throws Exception {
        Position initialPos = null;
        for (int x = 0; x < labyrinth.size(); x++) {
            for (int y = 0; y < labyrinth.get(x).length; y++) {
                if ("E".equals(labyrinth.get(x)[y])) {
                    initialPos = new Position(x, y);
                }
            }
        }
        if (initialPos == null) {
            throw new Exception("Entrance not found");
        }
        return initialPos;
    }

    public boolean isEmptySpace(Position pos) {

        return (" ".equals(labyrinth.get(pos.getX())[pos.getY()])) || ("S".equals(labyrinth.get(pos.getX())[pos.getY()]));
    }

    public boolean isExit(Position pos) {
        return "S".equals(labyrinth.get(pos.getX())[pos.getY()]);
    }

    private boolean canMove(Position pos, ArrayList<Position> visited) {
        return (isInRange(pos) && isEmptySpace(pos) && !visited.contains(pos));
    }

    private boolean isInRange(Position position) {
        var ySize = labyrinth.get(0).length - 1;
        var xSize = labyrinth.size() - 1;
        return !(position.getX() < 0 || position.getY() < 0 || position.getX() > xSize || position.getY() > ySize);
    }

    public void gameMovement(Position initialPos) {

        ArrayList<Position> visited = new ArrayList<>();
        Position currentPos;
        currentPos = initialPos;
        if (currentPos.getX() >= 0 && currentPos.getX() <= labyrinth.size()
                && currentPos.getY() >= 0 && currentPos.getY() <= labyrinth.get(0).length) {
            while (!isExit(currentPos)) {
                if (canMove(currentPos.getDown(), visited)) {
                    currentPos = currentPos.getDown();
                    route.add(currentPos);

                } else if (canMove(currentPos.getLeft(), visited)) {
                    currentPos = currentPos.getLeft();
                    route.add(currentPos);

                } else if (canMove(currentPos.getRight(), visited)) {
                    currentPos = currentPos.getRight();
                    route.add(currentPos);

                } else if (canMove(currentPos.getUp(), visited)) {
                    currentPos = currentPos.getUp();
                    route.add(currentPos);

                } else {
                    route.remove(currentPos);
                    if (route.size() - 1 < 0) {
                        System.out.println("No exit found");
                        return;
                    }
                    currentPos = route.get(route.size() - 1);
                }
                visited.add(currentPos);
            }
            route.remove(currentPos);
            print();
            System.out.println("");
            System.out.println("He ganao, pringao");

        }

    }

    public void printRoute() {

        ArrayList<String[]> laben2 = new ArrayList<>();

        for (int i = 0; i < labyrinth.size(); i++) {
            laben2.add((String[]) labyrinth.get(i).clone());
        }

        for (int i = 0; i < route.size(); i++) {
            laben2.get(route.get(i).getX())[route.get(i).getY()] = "*";
        }
        
        

        for (int i = 0; i < labyrinth.size(); i++) {
            for (int j = 0; j < laben2.get(i).length; j++) {
                String cell = laben2.get(i)[j];
                if ("*".equals(cell)) {
                    System.out.print("*");
                } else if ("E".equals(labyrinth.get(i)[j])){
                System.out.print("E");
                } else if ("S".equals(cell)){
                System.out.print("S");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }

    public ArrayList<Position> getRoute() {
        return this.route;
    }
}
