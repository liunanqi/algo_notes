public class Sudoku {
    public static void main(String[] args){
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
		
        boolean isValid = isValidSudoku(board);
		
        System.out.println("Is this Sudoku valid?\n" + isValid);
    }

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] row = new boolean[9];
            boolean[] col = new boolean[9];
            boolean[] box = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                } else if (row[board[i][j] - '1']) return false;
                else row[board[i][j] - '1'] = true;

                if (board[j][i] == '.') {
                } else if (col[board[j][i] - '1']) return false;
                else col[board[j][i] - '1'] = true;

                int m = i / 3 * 3 + j / 3;
                int n = i % 3 * 3 + j % 3;

                if(board[m][n] == '.'){}
                else if(box[board[m][n] - '1']) return false;
                else box[board[m][n] - '1'] = true;
            }
        }
        return true;
    }
}