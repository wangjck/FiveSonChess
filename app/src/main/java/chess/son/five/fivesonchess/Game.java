package chess.son.five.fivesonchess;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View {
    Paint paint = new Paint();
    private Bitmap White;
    private Bitmap Black;
    public int SingelHeight = 72;
    public int PanelWidth = 1080;

    public static final int BOARDSIZE = 15;
    public static final int WINNING = 5;
    private int[][] board;
    public boolean ended = false;
    public int winner = 0;
    public boolean isWhite = true;


    public Game(Context context, AttributeSet attributes) {
        super(context, attributes);

        init();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < BOARDSIZE; i++) {
            int startX = (int) SingelHeight / 2;
            int endX = (int) (PanelWidth - SingelHeight / 2);
            int y = (int) ((0.5 + i) * SingelHeight);
            canvas.drawLine(startX, y, endX, y, paint);
            canvas.drawLine(y, startX, y, endX, paint);
        }
        if (ended) {
            paint.setTextSize(100);
            canvas.drawText("Player " + winner + " is the winner", 50,200,paint);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                switch (board[i][j]) {
                    case 1:
                        canvas.drawOval(i * SingelHeight, j * SingelHeight , i *SingelHeight+SingelHeight, j * SingelHeight + SingelHeight, paint);
                        break;
                    case 2:
                        canvas.drawRect(i * SingelHeight+5, j * SingelHeight+5 , i *SingelHeight+SingelHeight-5, j * SingelHeight + SingelHeight-5, paint);
                        break;
                }
            }
        }

    }
    public void onMeasure(int width, int height) {
        int widthSize = MeasureSpec.getSize(width);
        int widthMode = MeasureSpec.getMode(width);

        int heightSize = MeasureSpec.getSize(height);
        int heightMode = MeasureSpec.getMode(height);


        setMeasuredDimension(1080, 1080);
    }

    public void init() {
        board = new int[BOARDSIZE][BOARDSIZE];
        for (int[] i:board) {
            for (int j:i) {
                j = 0;
            }
        }
        ended = false;
        winner = 0;
        isWhite = true;
        paint = new Paint();
        paint.setColor(0x88000000);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        postInvalidate();
        //White = BitmapFactory.decodeResource(getResources(), R.drawable.white);
        //Black = BitmapFactory.decodeResource(getResources(), R.drawable.black);
    }
    public boolean move(int x, int y, int playerid) {
        if (x < 0 || y < 0 || x >= BOARDSIZE || y >= BOARDSIZE) {
            return false;
        }
        if (board[x][y] != 0) {
            return false;
        }
        board[x][y] = playerid;
        int lr = 1;
        int ud = 1;
        int lurd = 1;
        int ldru = 1;
        for (int i = 1; i < WINNING; i++) {
            if (x + i >= 0 && x + i < BOARDSIZE && board[x + i][y] == playerid) {
                lr++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x - i >= 0 && x - i < BOARDSIZE && board[x - i][y] == playerid) {
                lr++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (y + i >= 0 && y + i < BOARDSIZE && board[x][y + i] == playerid) {
                ud++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (y - i >= 0 && y - i < BOARDSIZE && board[x][y - i] == playerid) {
                ud++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x + i >= 0 && x + i < BOARDSIZE && y + i >= 0 && y + i < BOARDSIZE && board[x + i][y + i] == playerid) {
                lurd++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x - i >= 0 && x - i < BOARDSIZE && y - i >= 0 && y - i < BOARDSIZE && board[x - i][y - i] == playerid) {
                lurd++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x + i >= 0 && x + i < BOARDSIZE && y - i >= 0 && y - i < BOARDSIZE && board[x + i][y - i] == playerid) {
                ldru++;
            } else {
                break;
            }
        }
        for (int i = 1; i < WINNING; i++) {
            if (x - i >= 0 && x - i < BOARDSIZE && y + i >= 0 && y + i < BOARDSIZE && board[x - i][y + i] == playerid) {
                ldru++;
            } else {
                break;
            }
        }
        if (lr >= WINNING || ud >= WINNING || ldru >= WINNING || lurd >= WINNING) {
            winner = playerid;
            ended = true;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (ended) {
            return false;
        }
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int x = (int)event.getX() / SingelHeight;
            int y = (int)event.getY() / SingelHeight;
            if (isWhite) {
                if (move(x, y, 1)) {
                    isWhite = !isWhite;
                }
            } else {
                if (move(x, y, 2)) {
                    isWhite = !isWhite;
                }
            }
        }
        postInvalidate();
        return true;
    }
}
