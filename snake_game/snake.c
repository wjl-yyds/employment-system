#include <conio.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>

#define WIDTH 20
#define HEIGHT 20
#define MAX_SNAKE_LENGTH (WIDTH * HEIGHT)

typedef struct {
    int x;
    int y;
} Point;

typedef enum {
    STOP = 0,
    LEFT,
    RIGHT,
    UP,
    DOWN
} Direction;

static Point snake[MAX_SNAKE_LENGTH];
static int snakeLength;
static Point food;
static Direction direction;
static int score;
static int gameOver;

void hideCursor(void) {
    HANDLE console = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_CURSOR_INFO cursorInfo;

    GetConsoleCursorInfo(console, &cursorInfo);
    cursorInfo.bVisible = FALSE;
    SetConsoleCursorInfo(console, &cursorInfo);
}

void moveCursorToTop(void) {
    COORD position;
    position.X = 0;
    position.Y = 0;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), position);
}

int isOnSnake(int x, int y) {
    int i;
    for (i = 0; i < snakeLength; i++) {
        if (snake[i].x == x && snake[i].y == y) {
            return 1;
        }
    }
    return 0;
}

void spawnFood(void) {
    do {
        food.x = rand() % WIDTH;
        food.y = rand() % HEIGHT;
    } while (isOnSnake(food.x, food.y));
}

void initGame(void) {
    snakeLength = 3;
    snake[0].x = WIDTH / 2;
    snake[0].y = HEIGHT / 2;
    snake[1].x = snake[0].x - 1;
    snake[1].y = snake[0].y;
    snake[2].x = snake[1].x - 1;
    snake[2].y = snake[1].y;

    direction = RIGHT;
    score = 0;
    gameOver = 0;
    spawnFood();
}

void draw(void) {
    int x;
    int y;

    moveCursorToTop();

    for (x = 0; x < WIDTH + 2; x++) {
        printf("#");
    }
    printf("\n");

    for (y = 0; y < HEIGHT; y++) {
        printf("#");
        for (x = 0; x < WIDTH; x++) {
            if (x == snake[0].x && y == snake[0].y) {
                printf("O");
            } else if (x == food.x && y == food.y) {
                printf("*");
            } else if (isOnSnake(x, y)) {
                printf("o");
            } else {
                printf(" ");
            }
        }
        printf("#\n");
    }

    for (x = 0; x < WIDTH + 2; x++) {
        printf("#");
    }
    printf("\n");

    printf("Score: %d\n", score);
    printf("Control: W A S D\n");
}

void input(void) {
    if (_kbhit()) {
        switch (_getch()) {
        case 'a':
        case 'A':
            if (direction != RIGHT) {
                direction = LEFT;
            }
            break;
        case 'd':
        case 'D':
            if (direction != LEFT) {
                direction = RIGHT;
            }
            break;
        case 'w':
        case 'W':
            if (direction != DOWN) {
                direction = UP;
            }
            break;
        case 's':
        case 'S':
            if (direction != UP) {
                direction = DOWN;
            }
            break;
        case 'x':
        case 'X':
            gameOver = 1;
            break;
        default:
            break;
        }
    }
}

void update(void) {
    int i;
    Point newHead = snake[0];
    int nextLength = snakeLength;

    switch (direction) {
    case LEFT:
        newHead.x--;
        break;
    case RIGHT:
        newHead.x++;
        break;
    case UP:
        newHead.y--;
        break;
    case DOWN:
        newHead.y++;
        break;
    default:
        break;
    }

    if (newHead.x < 0 || newHead.x >= WIDTH || newHead.y < 0 || newHead.y >= HEIGHT) {
        gameOver = 1;
        return;
    }

    for (i = 0; i < snakeLength; i++) {
        if (snake[i].x == newHead.x && snake[i].y == newHead.y) {
            gameOver = 1;
            return;
        }
    }

    if (newHead.x == food.x && newHead.y == food.y) {
        nextLength++;
    }

    for (i = nextLength - 1; i > 0; i--) {
        snake[i] = snake[i - 1];
    }
    snake[0] = newHead;

    if (newHead.x == food.x && newHead.y == food.y) {
        snakeLength = nextLength;
        score += 10;
        if (snakeLength < MAX_SNAKE_LENGTH) {
            spawnFood();
        } else {
            gameOver = 1;
        }
    } else {
        snakeLength = nextLength;
    }
}

int main(void) {
    srand((unsigned int)time(NULL));
    hideCursor();
    initGame();

    while (!gameOver) {
        draw();
        input();
        update();
        Sleep(120);
    }

    draw();
    printf("Game Over! Final Score: %d\n", score);
    printf("Press any key to exit...\n");
    _getch();

    return 0;
}
