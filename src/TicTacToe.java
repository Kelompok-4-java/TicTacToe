import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
// import javax.swing.plaf.TreeUI;

public class TicTacToe {
    int boardWidth = 500;
    int boardHeight = 600;

    JFrame frame = new JFrame("Let's Play Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    
    JButton [][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    JButton resetButton = new JButton("New Game");
    String loser = null;

    boolean gameOver = false;
    int turn = 0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setText("Tic Tac Toe");
        textLabel.setBackground(new Color(20,20,20));
        textLabel.setForeground(new Color(80,80,80));
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(new Color(20,20,20));
        frame.add(boardPanel);

        buttonPanel.setLayout(new FlowLayout());
        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setBackground(Color.black);
        resetButton.setForeground(Color.white);
        resetButton.setBorder(new LineBorder(Color.white, 1));
        resetButton.setOpaque(true);
        resetButton.setPreferredSize(new Dimension(400,50));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {resetGame();}
        });
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(new Color(20,20,20));
                tile.setForeground(Color.LIGHT_GRAY);
                tile.setFont(new Font("Arial", Font.BOLD, 100));
                tile.setFocusable(false); 
                tile.setOpaque(true);
                tile.setBorder(new LineBorder(Color.white, 1));
                
                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turn++;
                            checkWinner();
                            if (!gameOver){
                                currentPlayer = currentPlayer ==playerX ? playerO:playerX;
                                textLabel.setText(currentPlayer + " turn");

                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner(){
        //horizontal
        for (int r=0; r<3; r++) {
            if (board[r][0].getText() == "") continue;
            if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i<3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            } 
        }

        //vertical
        for (int c=0; c<3; c++) {
            if (board[0][c].getText() == "") continue;
            if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i<3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //diagonal kiri ke kanan
        if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != "") {
            for (int i = 0; i<3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        //diagonal kanan ke kiri
        if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        //tie
        if (turn == 9) {
            for (int r=0; r<3; r++) {
                for (int c=0; c<3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.darkGray);
        textLabel.setForeground(Color.GREEN);
        textLabel.setText(currentPlayer + " win!");
        loser = currentPlayer.equals(playerX) ? playerO : playerX;
    }

    void setTie (JButton tile) {
        tile.setForeground(Color.orange);
        textLabel.setForeground(Color.orange);
        textLabel.setText(" Tie!");
        loser = null;
    }

    void resetGame(){
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                board[r][c].setText("");
                board[r][c].setBackground(new Color(20,20,20));
                board[r][c].setForeground(Color.LIGHT_GRAY);
            }
        }

        textLabel.setText("Tic Tac Toe");
        textLabel.setForeground(new Color(80,80,80));
        turn = 0;
        currentPlayer = (loser!=null) ? loser : playerX;
        gameOver = false;
    }
}


