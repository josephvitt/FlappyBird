package src.main.java;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {

    //管道之间的间距
    public static  int PIPE_DELAY;
    private Boolean paused;

    private int pauseDelay;

    private int restartDelay;
    private int pipeDelay;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Keyboard keyboard;

    public int score;
    public Boolean gameover;
    public Boolean started;

    public Game() {
        keyboard = Keyboard.getInstance();
        restart();
    }

    public static int getPipeDelay() {
        return PIPE_DELAY;
    }

    public static void setPipeDelay(int pipeDelay) {
        PIPE_DELAY = pipeDelay;
    }

    //重置原始状态
    public void restart() {
        System.out.println("Game restart");
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;

        bird = new Bird();
        pipes = new ArrayList<Pipe>();
    }

    //game  逻辑上Updata
    public void update() {
//        System.out.println("Game update");
        watchForStart();

        if (!started)
        {
            //判断game是否启动 如果未启动 直接return 无需update
//            System.out.println("Game nerver start");
            return;
        }

        watchForPause();
        watchForReset();

        if (paused)
        {
            //判断game是否暂停 如若暂停 直接return 无需update
//            System.out.println("Game Paused");
            return;
        }

        bird.update();

        if (gameover) {
            if(bird.y<App.HEIGHT - 80 - bird.height){ }
            else {
                bird.y = App.HEIGHT - 80 - bird.height;
            }
            //判断game是否Over 如若Over 直接return 无需update
//            System.out.println("Game Over");
            return;
        }

//        movePipes();

        CreatPipes();
        checkForCollisions();
    }

    //UI上更新
    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        //添加背景
        renders.add(new Render(0, 0, "lib/background.png"));
        //添加管道
        for (Pipe pipe : pipes)
            renders.add(pipe.getRender());
        //添加路牙
        renders.add(new Render(0, 0, "lib/foreground.png"));
        //添加像素鸟
        renders.add(bird.getRender());
        return renders;
    }

    private void watchForStart() {
        //start为true标志游戏开始
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }
    }

    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            return;
        }
    }

    //创建管道
    private void CreatPipes(){
        pipeDelay--;
        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Pipe northPipe = new Pipe("north");
            Pipe southPipe = new Pipe("south");
            //确定northPipe位置
            northPipe.y = southPipe.y + southPipe.height+175;
            pipes.add(northPipe);
            pipes.add(southPipe);

            //Pipe离开屏幕后 ArrayList删除元素 节省空间
            for(Iterator iterator=pipes.iterator();iterator.hasNext();){
                Pipe pipe= (Pipe) iterator.next();
                if (pipe.x - pipe.width < 0) {
                    iterator.remove();
                }
            }
        }
        //更新在ArrayList中的Pipe
        for (Pipe pipe : pipes) {
            pipe.update();
        }
    }
//    private void movePipes() {
////        System.out.println("pipeDelay:"+pipeDelay);
//        pipeDelay--;
//
//        //pipeDelay < 0 建立管道
//        if (pipeDelay < 0) {
////            System.out.println("pipeDelay<0");
//            //初始化管道间距和 上下管道
//            pipeDelay = PIPE_DELAY;
//            Pipe northPipe = null;
//            Pipe southPipe = null;
//
//            if (northPipe == null) {
//                System.out.print("新建管道1");
//                Pipe pipe = new Pipe("north");
//                pipes.add(pipe);
//                northPipe = pipe;
//            }
//            if (southPipe == null) {
//                System.out.println("  新建管道2");
//                Pipe pipe = new Pipe("south");
//                pipes.add(pipe);
//                southPipe = pipe;
//            }
//            System.out.println("southPipe.y:"+southPipe.y);
//            System.out.println("southPipe.height:"+southPipe.height);
//            //确定northPipe位置
//            northPipe.y = southPipe.y + southPipe.height+175;
//
//            //Pipe离开屏幕后 ArrayList删除元素 节省空间
//            for(Iterator iterator=pipes.iterator();iterator.hasNext();){
//                Pipe pipe= (Pipe) iterator.next();
//                if (pipe.x - pipe.width < 0) {
//                    iterator.remove();
//                }
//            }
//        }
//
//        //更新在ArrayList中的Pipe
//        for (Pipe pipe : pipes) {
//            pipe.update();
//        }
//    }

    //检测碰撞
    private void checkForCollisions() {
        //管道碰撞检测
        for (Pipe pipe : pipes) {
            if (pipe.collides(bird.x, bird.y, bird.width, bird.height)) {
                gameover = true;
                bird.dead = true;
            } else if (pipe.x == bird.x && pipe.orientation.equalsIgnoreCase("south")) {
                score++;
            }
        }

        // 地板碰撞检测
//        System.out.println(bird.y + bird.height+"         "+(App.HEIGHT - 80));
        if (bird.y + bird.height > App.HEIGHT - 80) {
            System.out.println("true");
            gameover = true;
            bird.dead = true;
            bird.y = App.HEIGHT - 80 - bird.height;
        }

        //强制天花板
        if(bird.y<0){
            bird.y=0;
        }
    }
}
