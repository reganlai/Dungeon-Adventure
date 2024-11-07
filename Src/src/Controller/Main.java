package Controller;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    public static void main(String[] theArgs) {
        MazeGenerator maze = new MazeGenerator(6, 6);
        System.out.println();
        System.out.println(maze.getMySpawnInRow());
        System.out.println(maze.getMySpawnInCol());
        System.out.println(maze.toString());
        System.out.println(maze.getMaze()[maze.getMySpawnInRow()][maze.getMySpawnInCol()].roomToString());
        System.out.println(maze.getMaze()[0][4].roomToString());
        System.out.println("Done!");
    }
}