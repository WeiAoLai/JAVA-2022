import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer; //用 Timer 的時候 
import java.util.TimerTask; //用 TimerTask 的時候
import javax.imageio.ImageIO;   //上面小小圖片的
import java.io.IOException;     //上面小小圖片的

public class try01Code extends JFrame {
    private Container cp;
    private JButton jbAccept = new JButton("Accept.");
    private JButton jbBox = new JButton("Box.");
    private JButton jbWrite = new JButton("Write+");
    private ImageIcon im2 = new ImageIcon("S2.png");
    private JButton jbSetting = new JButton(new ImageIcon("S2.png"));
    // private JLabel jlbTime = new JLabel("12:00 PM");
    public JMenuBar jmb2 = new JMenuBar(); // 菜單欄，上面那一槓
    private JMenu jmF = new JMenu("Help"); // 菜單
    private JMenuItem jmiFO = new JMenuItem("account"); // 帳戶資訊
    private JMenuItem jmiAbout = new JMenuItem("about"); // 菜單下面的一大坨
    private JMenuItem jmiExit = new JMenuItem("exit");
    // add thing
    private ImageIcon image = new ImageIcon("R.jpg");
    private JLabel jlbBG = new JLabel(new ImageIcon("R.jpg"), JLabel.CENTER);
    // Time
    public static JLabel jT[] = new JLabel[6];
    private Timer time;
    private Font fontButtn = new Font("Algerian", Font.BOLD, 28);
    private Font font1 = new Font("STCaiyun", Font.BOLD, 24);
    private Font font2 = new Font("STCaiyun", Font.BOLD, 48);
    // Muyao-Softbrush 字體

    // 移動小人
    // private ImgmovePanel imagmove = new ImgmovePanel(true, true, true, true);
    private final int PANEL_WIDTH = 550;
    private final int PANEL_HEIGHT = 750;
    private ImageIcon enemy[] = new ImageIcon[10];
    public static JButton button = new JButton();
    private Image backgroundImage;
    private Timer timerMove;
    private Timer timerChangeR;
    private Timer timerChangeL;
    private Timer timerPress; // 讓他停留在跌倒XD
    private int xVelocity = 2;
    private int yVelocity = 1;
    private int x = 450;
    private int y = 600;
    private int role = 0;
    private int right = 1; // left = -1
    private int num = 0;
    public static boolean flag = true;
    private int decided = 0;
    int sce = 1; // 控制停留幾秒

    // 日記
    private JDesktopPane desktop = new JDesktopPane(); // 要開內部視窗就要這個
    private Lable01 writeD = new Lable01("日記", true, true, true, true);

    // Box
    // private BOX boxInternalFeame = new BOX("Box", true, true, true, true);
    private BOX boxInternalFeame = new BOX(); // 登入進去後的頁面

    // 音樂
    public MusicBG musicBG = new MusicBG();
    public static JButton musicstop = new JButton();
    private ImageIcon musicIcon[] = new ImageIcon[2];
    public static int music_number = 0;

    // settings
    public RaidoBottons mfrm = new RaidoBottons();
    public static int timeSet = 0;
    public static int garrySet = 0;
    public static int musicSet = 0;

    public try01Code() {
        init();
    }

    private void init() {

        this.setTitle("journal ~ *･゜ﾟ･*:.｡..｡.:*･'(*ﾟ▽ﾟ*)'･*:.｡. .｡.:*･゜ﾟ･*");
        cp = this.getContentPane();
        this.setBounds(100, 60, 550, 750);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp.setLayout(null);
        this.setResizable(false);  //不能調視窗大小(鎖定)

        // 設定時間
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");
        // System.out.println("yyyy/MM/dd HH:mm:ss-> " +
        for (int i = 0; i < 6; i++) {
            jT[i] = new JLabel();
        }

        jT[0].setText(year.format(LocalDateTime.now()));
        jT[0].setBounds(300, 190, 100, 50);
        jT[0].setFont(font1);
        jT[1].setText(month.format(LocalDateTime.now()));
        jT[1].setBounds(370, 190, 100, 50);
        jT[1].setFont(font1);
        jT[2].setText(day.format(LocalDateTime.now()));
        jT[2].setBounds(400, 190, 100, 50);
        jT[2].setFont(font1);
        jT[3].setText(hour.format(LocalDateTime.now()));
        jT[3].setBounds(280, 150, 200, 50);
        jT[3].setFont(font2);
        jT[4].setText(min.format(LocalDateTime.now()));
        jT[4].setBounds(350, 150, 200, 50);
        jT[4].setFont(font2);
        jT[5].setText(sec.format(LocalDateTime.now()));
        jT[5].setBounds(420, 155, 100, 50);
        jT[5].setFont(new Font("STCaiyun", Font.BOLD, 24));
        // 設定時間
        time = new Timer();
        time.schedule(new DayTime(), 0, 1000); //第0秒開始，每1秒執行一次
        // (前面是持續多久，後面是過多久跑一次)

        // 小小人
        button.setBounds(430, 680, 110, 80);
        cp.add(button);

        //右邊的圖
        enemy[0] = new ImageIcon("garry7-0.png");
        enemy[1] = new ImageIcon("garry7-1.png");
        enemy[2] = new ImageIcon("garry7-2.png");
        // 左邊的圖
        enemy[3] = new ImageIcon("garry0.png");
        enemy[4] = new ImageIcon("garry1.png");
        enemy[5] = new ImageIcon("garry2.png");
        // 跌倒的圖
        enemy[6] = new ImageIcon("garry3.png");
        enemy[7] = new ImageIcon("garry3-3.png");
        enemy[8] = new ImageIcon("garry4.png");
        enemy[9] = new ImageIcon("garry4-4.png");

        // enemyPress[0] = new ImageIcon("garry3.png");
        // enemyPress[1] = new ImageIcon("garry3-3.png");
        // enemyPress[2] = new ImageIcon("garry4.png");
        // enemyPress[3] = new ImageIcon("garry4-4.png");

        timerMove = new Timer(); // 每10毫秒，在視窗裡移動
        timerMove.schedule(new timerTaskMove(), 200, 200); //第0.2秒開始，每0.2秒執行一次
        // if (xVelocity >= 0) {  //時間右的執行續
            timerChangeR = new Timer();
            timerChangeR.schedule(new timerTaskCheckR(), 450, 450);
        // }
        timerChangeL = new Timer();// 時間左的執行續
        timerChangeL.schedule(new timerTaskCheckL(), 500, 500);
        // 如果timerChange.schedule(new TimerTask(), 0, 1000); 第一個圖直接被換成第二張
        // (new TimerTask(), 前, 後) => 後：圖片切換的速度。
        // 時間(為了讓他跌倒的點停留)
        timerPress = new Timer();
        timerPress.schedule(new timerPressTask(), 300, 300);

        System.out.println("-----");
        // 小小人

        //背景的圖片調整
        Image temp = image.getImage().getScaledInstance(550, 750, image.getImage().SCALE_DEFAULT); // 設定圖片大小(縮放)
        image = new ImageIcon(temp);
        jlbBG = new JLabel(image, JLabel.CENTER);
        //左下的設定圖片調整
        temp = im2.getImage().getScaledInstance(70, 70, im2.getImage().SCALE_DEFAULT);
        im2 = new ImageIcon(temp);
        jbSetting = new JButton(im2);
        jbSetting.setOpaque(false);            // 為了讓按鈕上的圖片去背
        jbSetting.setContentAreaFilled(false); // 為了讓按鈕自己去被只剩按鈕上的圖片，這樣就不會擋到背景圖片
        jbSetting.setBorder(null);             // 讓他沒有按鈕的框框，好像叫焦點來著

        //背景音樂的撥放鍵  -> 連動 MusicBG
        musicIcon[0] = new ImageIcon("Stop.png");
        musicIcon[1] = new ImageIcon("continue.png");
        musicstop.setContentAreaFilled(false); // 是否填滿按鈕所在的區域
        musicstop.setOpaque(false);            // 設置按鈕是否透明
        musicstop.setBorderPainted(false);     // 是否要有邊框
        musicstop.setFocusPainted(false);      // 是否要邊框虛線
        temp = musicIcon[0].getImage().getScaledInstance(35, 35, musicIcon[0].getImage().SCALE_DEFAULT); // 設定圖片大小(縮放)
        musicIcon[0] = new ImageIcon(temp);
        temp = musicIcon[1].getImage().getScaledInstance(35, 35, musicIcon[1].getImage().SCALE_DEFAULT); // 設定圖片大小(縮放)
        musicIcon[1] = new ImageIcon(temp);
        musicstop.setIcon(musicIcon[0]);

        // JMenuBar 的添加
        this.setJMenuBar(jmb2);
        jmb2.add(jmF);
        jmF.add(jmiFO);
        jmF.add(jmiAbout);
        jmF.add(jmiExit);
        //按鈕的設定(字體、顏色)
        jbBox.setFont(fontButtn);
        jbWrite.setFont(fontButtn);
        jbAccept.setForeground(Color.WHITE);
        jbBox.setForeground(Color.WHITE);
        jbWrite.setForeground(Color.WHITE);

        jbBox.setOpaque(false);               // 設置按鈕是否透明
        jbBox.setBorderPainted(false);        // 是否要有邊框
        jbBox.setContentAreaFilled(false);    // 是否填滿按鈕所在的區域
        jbBox.setFocusPainted(false);         // 設置按鈕是否透明

        jbWrite.setContentAreaFilled(false);  // 是否填滿按鈕所在的區域
        jbWrite.setOpaque(false);             // 設置按鈕是否透明
        jbWrite.setBorderPainted(false);      // 是否要有邊框
        jbWrite.setFocusPainted(false);       // 是否要邊框虛線

        //功能表
        jbBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //問題一
                boxInternalFeame.setVisible(true);   //把new放最上面，不然會無限新增
                BOXInternalFrame02.jta.setEditable(false);                
            }
        });
        //按下write後把這個內部視窗放到最上面，用放進內容面板的方式
        jbWrite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try01Code.this.setContentPane(writeD);
                // cp.setVisible(true);
                //進去後偵測，如果按了返回鍵就回來
                Lable01.jmLast.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // 回到主畫面
                        try01Code.this.setContentPane(cp);
                    }
                });
            }
        });

        jbSetting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                mfrm.setVisible(true);
                try {
                    Image image = ImageIO.read(this.getClass().getResource("garry_Happy.png")); // 建立圖片物件
                    mfrm.setIconImage(image);// 設定圖示
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        jmiExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Object[] options = { "我關！.AUA.", "我考慮一下oOvOo" };
                int state_1 = JOptionPane.showOptionDialog(
                        try01Code.this, "別關我QAQ拜託別關我......", "TAT別關我別關我別關我別關我.......", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry_Sad01.png"), options, options[0]);
                if (state_1 == 0) {
                    Object[] options02 = { "我再關！", "好好好，陪你陪你 TvT" };
                    int state_2 = JOptionPane.showOptionDialog(
                            try01Code.this, "          你忍心？真的忍心？？", "TAT別關我別關我別關我別關我.......", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry_angry.png"), options02, options02[0]);
                    if(state_2 == 0){
                        Object[] options03 = { "💢別逼我按右上那個「X」！", "ヾ(╹◡╹)ﾉ 那如果我留下來陪你呢？" };
                        int state_3 = JOptionPane.showOptionDialog(
                                try01Code.this, "                   哈！你以為想關就關得掉嗎？", "( T_T)\\(^-^ )別關我別關我別關我別關我.......", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry_angry02.png"), options03, options03[0]);
                        if(state_3 == 0){
                            Object[] options04 = { "你不讓我走我就再也不看你💢", "好啦~下次再來看你" };
                            int state_4 = JOptionPane.showOptionDialog(
                                    try01Code.this, "                我只是希望你不要走......", "TAT別關我別關我別關我別關我.......", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry_Sad02.png"), options04, options04[0]);
                            if( state_4 == 0 || state_4 == 1){
                                Object[] options05 = { "(*´◒`*)豪！！" };
                                int state_5 = JOptionPane.showOptionDialog(try01Code.this, "那好吧...要快點回來呦~", "❤️我等你___",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry_Sad03.png"), options05,
                                        options05[0]);
                                if (state_5 == 0) {
                                    System.exit(0);
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(try01Code.this, "真的嗎？耶耶！！最愛你惹~❤️", "❤️謝謝你留下來陪我",
                                        JOptionPane.INFORMATION_MESSAGE, new ImageIcon("garry_Happy.png"));
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(try01Code.this, "真的嗎？耶耶！！最愛你惹~❤️", "❤️謝謝你留下來陪我",
                                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("garry_Happy.png"));
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(try01Code.this, "真的嗎？耶耶！！最愛你惹~❤️", "❤️謝謝你留下來陪我", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("garry_Happy.png"));
                    }
                }    
                else{
                    JOptionPane.showMessageDialog(try01Code.this, "真的嗎？耶耶！！最愛你惹~❤️", "❤️謝謝你留下來陪我", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("garry_Happy.png"));
                }           
            }
        });

        jmiFO.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ImageIcon iconAbout = new ImageIcon("about.png");
                JOptionPane.showMessageDialog(try01Code.this, "使用者名稱：馬萁蔚\n班級：資訊一甲", "╰(*´︶`*)╯♡ Account",
                        JOptionPane.INFORMATION_MESSAGE, iconAbout);
            }
        });

        jmiAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ImageIcon iconAbout = new ImageIcon("About01.png");
                JOptionPane.showMessageDialog(try01Code.this, "日記 : 寫下此時此刻的心情吧。\nWrite+ : 寫下你的想法。\nBox：開啟之前的檔案懷念一下吧~ ",
                        "╰(*´︶`*)╯♡ About",
                        JOptionPane.INFORMATION_MESSAGE, iconAbout);
            }
        });

        //連動到 -> MusicBG：
        musicstop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                music_number++;
                musicstop.setIcon(musicIcon[music_number % 2]);
                MusicBG.should_I_stop = 0;  //一開始按下去就停
                System.out.println("music_number % 2 = " + music_number % 2);
            }
        });

        //按住的時候一張圖，放開一張，我要延遲放開那張，所以加了個sce，計秒
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (role < 3) {
                    button.setPressedIcon(enemy[7]);
                    button.setIcon(enemy[9]);
                    // button.setReleasedIcon(enemy[9]);
                } else {
                    button.setPressedIcon(enemy[6]);
                    button.setIcon(enemy[8]);
                    // button.setReleasedIcon(enemy[8]);
                }
                sce = 0;
                // ReleasedIcon();
            }
        });

        jlbBG.setBounds(0, 0, 550, 750);
        jbBox.setBounds(62, 115, 150, 35);
        jbWrite.setBounds(66, 217, 150, 35);
        jbSetting.setBounds(50, 590, 70, 70);
        musicstop.setBounds(474, 637, 35, 35);

        // add
        cp.add(jbBox);
        cp.add(jbWrite);
        cp.add(jbSetting);
        cp.add(musicstop);

        for (int i = 0; i < 6; i++) {
            jT[i].setForeground(Color.WHITE);
            cp.add(jT[i]);
        }

        cp.add(jlbBG); // 要放最下面，不然會出事

        try {
            Image image = ImageIO.read(this.getClass().getResource("QIcon.png")); // 建立圖片物件
            this.setIconImage(image);// 設定圖示
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ReleasedIcon() {
        if (role < 3) {         //右
            while (sce < 1) {
                button.setIcon(enemy[9]);
            }

        } else {                //左
            while (sce < 1) {
                button.setIcon(enemy[8]);
            }
        }
    }

    class DayTime extends TimerTask {
        @Override
        public void run() {
            DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
            DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH");
            DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");

            jT[0].setText(year.format(LocalDateTime.now()));
            jT[1].setText(month.format(LocalDateTime.now()));
            jT[2].setText(day.format(LocalDateTime.now()));
            jT[3].setText(hour.format(LocalDateTime.now()));
            jT[4].setText(min.format(LocalDateTime.now()));
            jT[5].setText(sec.format(LocalDateTime.now()));
        }
    }

    //x & y 的移動
    class timerTaskMove extends TimerTask {
        @Override
        public void run() {
            if (x >= PANEL_WIDTH - 100 || x < -30) {
                xVelocity = xVelocity * -1;
                right = right * -1;
            }
            x = x + xVelocity; // 往X方向移動
          
            if (y >= PANEL_HEIGHT - 130 || y < 0) {
                yVelocity = yVelocity * -1;
            }
            y = y + yVelocity; // 往X方向移動
           
            if (sce > 1) { // 跌倒時不動，不然會站跌站跌
                button.setIcon(enemy[role % 6]);
            }

            // System.out.println("x " + x + "y " + y);
            // button.setLocation(x, y);
            button.setContentAreaFilled(false); // 是否填滿按鈕所在的區域
            button.setOpaque(false); // 設置按鈕是否透明
            button.setBorderPainted(false); // 是否要有邊框
            button.setFocusPainted(false); // 是否要邊框虛線
            button.setBounds(x, y, 110, 80);
        }
    }

    class timerTaskCheckR extends TimerTask {
        @Override
        public void run() {
            if (xVelocity >= 0 && sce > 1) {
                role++;
                role %= 3;  //幾張圖就%幾
            }
            if (sce == 0) {         //第0秒等於剛放開，剛放開就執行放開的函式，不要右
                ReleasedIcon();
            }

        }
    }

    class timerTaskCheckL extends TimerTask {
        @Override
        public void run() {
            if (xVelocity < 0 && sce > 1) {
                role++;
                role = role % 3 + 3;
            }
            if (sce == 0) {         // 第0秒等於剛放開，剛放開就執行放開的函式，不要左
                ReleasedIcon();
            }

        }
    }
    //計時器
    class timerPressTask extends TimerTask {  
        public void run() {
            sce++;
        }
    }

    public static void main(String[] args) {
        try01Code mfrm = new try01Code();
        mfrm.setVisible(true);
        String filepath = "因為喜歡你.wav";   //音樂的部分，一開始就告訴他
        MusicBG musicObject = new MusicBG();
        musicObject.playMusic(filepath);
    }

}

