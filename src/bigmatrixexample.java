public class bigmatrixexample {
    int [][] board = new int [20][10];


    public int[][] generateSquare(int [][] gameboard){

        gameboard[0][5]=1;
        gameboard[0][6]=1;
        gameboard[1][5]=1;
        gameboard[1][6]=1;

        return gameboard;
    }

    public void drawboard(int [][]gameboard){
        for(int i=0; i<10; i++){
            for(int j=0; j<20; j++){
                if(gameboard[i][j]==1){
                    //draw filled square
                }
                else{
                    //draw empty square
                }
            }
        }
    }

    //public void move


}
