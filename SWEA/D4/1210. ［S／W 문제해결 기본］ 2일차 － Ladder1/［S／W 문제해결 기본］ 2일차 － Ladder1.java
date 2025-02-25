//package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/**
 * 사다리를 따라서 내려가자. 아니 거꾸로 올라가자? 위에서부터 내려가면 시작점 개수만큼 탐색 해야함. 하지만 이 경우 도착점을 알고 있으니, 도착점에서 탐색하면 1번만 해도 되지 않을까? !!!주의!!!
 * 교차점에서 '무조건' 방향전환해야함.
 * <p>
 * 1.입력 받기
 *      1-1. 입력을 받으면서 동시에 가로방향으로 연결된 징검다리 정보를 연결한다. 이때, index를 동시에 기록할 것이므로 지형 정보를 음수로 받아야 함.
 *          1-1-a. 시작점에 끝점의 열 번호를, 끝점에 시작점의 열 번호를 적되, 벽에 도달해서 다리가 끝난 경우를 보정하기 위해 열 번호를 보정한다.
 * 2. 도착점에서 탐색 실시.
 *      2-1 반대 다리 정보가 있으면 즉시 그 좌표로 이동.
 *      2-2 그 전까지 위로 직진.
 * 3. 도달한 출발점의 열 좌표를 표시.
 */
public class Solution {

    private static final int dy = -1;
    private static final int[] endPoint = new int[2];
    private static final int[][] ladderMap = new int[100][100];

    private static int climb() {
        int row = endPoint[0];
        int col = endPoint[1];
        while (true) {
            //만약 좌표가 적혀있다면? 즉시 그 열로 이동함.
            if (ladderMap[row][col] >= 0) {
                col = ladderMap[row][col];
            }
            //위 방향으로 이동
            row += dy;
            if (row == 0) {
                return col;
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("./res/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer;
        StringBuilder builder = new StringBuilder();
        for (int testIdx = 1; testIdx <= 10; testIdx++) {
            reader.readLine();
            boolean isBridgeStarted;
            for (int row = 0; row < 100; row++) {
                tokenizer = new StringTokenizer(reader.readLine());
                isBridgeStarted = false;
                int startX = 0;
                int startY = 0;
                //다리는 가로로 이어져 있고, 입력은 가로 방향으로 입력받는 것을 이용
                for (int col = 0; col < 100; col++) {
                    int value = Integer.parseInt(tokenizer.nextToken()) - 3;
                    ladderMap[row][col] = value;
                    //다리만들기가 시작되지 않았고, 가로 행으로 1이 연속되는경우, 현재 열 - 1의 지점에서 다리가 시작된 것이다.
                    if (!isBridgeStarted && col > 0 && (ladderMap[row][col - 1] == -2) && ladderMap[row][col] == -2) {
                        isBridgeStarted = true;
                        startX = col - 1;
                        startY = row;
                    }
                    //만약, 다리 만들기 상태이며, 벽 끝에 도달했으면 (col = 99) 현재 열에서, 현재 위치가 0이고 이전 위치가 1이었으면, 현재 열 -1의 지점에서 다리가 끝난 것이다.
                    if (isBridgeStarted && (col == 99 || (ladderMap[row][col - 1] == -2
                            && ladderMap[row][col] == -3))) {
                        isBridgeStarted = false;
                        int adjustedCol = col == 99 ? 99 : col - 1;
                        //각각 반대 좌표를 적어줌.
                        ladderMap[startY][startX] = adjustedCol;
                        ladderMap[row][adjustedCol] = startX;
                    }
                    //시작 지점 기록.
                    if (value == -1) {
                        endPoint[0] = row;
                        endPoint[1] = col;
                    }
                }
            }
            builder.append('#').append(testIdx).append(' ').append(climb()).append('\n');
        }
        writer.write(builder.toString());
        reader.close();
        writer.close();
    }
}
