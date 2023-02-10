import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RaidoBottons extends JFrame {
    private JLabel label = new JLabel(" ");
    private JRadioButton timeSetButton = new JRadioButton("close time   ");
    private JRadioButton garrySetButton = new JRadioButton("close garry   ");
    private JRadioButton musicSetButton = new JRadioButton("close music   ");
    private Font font1 = new Font("Muyao-Softbrush", Font.BOLD, 23);
    private JPanel jpn1 = new JPanel(new FlowLayout(FlowLayout.LEFT));   //放按鈕的地方
    private JLabel jpnRun = new JLabel(new ImageIcon("run.jpg"));
    private ImageIcon image;
    
    // private int timeSet = 0;
    // private int garrySet = 0;
    // private int musicSet = 0;
    
    public RaidoBottons(){
        init();
    }

    public void init(){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));
        this.setTitle("Set");
        this.setBounds(660, 635, 320, 175);
        this.setResizable(false);

        image = new ImageIcon("run.png");
        Image temp = image.getImage().getScaledInstance(70, 75, image.getImage().SCALE_DEFAULT); // 設定圖片大小(縮放)
        image = new ImageIcon(temp);
        jpnRun = new JLabel(image, JLabel.CENTER);

        this.add(jpnRun, BorderLayout.CENTER);   //先放圖片，圖片就會在左半邊
        this.add(jpn1);  // 右半邊，擠到讓他們變直的，舒服
               

        timeSetButton.setForeground(Color.BLUE);
        timeSetButton.setFont(font1);
        timeSetButton.setOpaque(false); // 為了讓按鈕去背
        timeSetButton.setContentAreaFilled(false); // 為了讓按鈕去背，讓背景圖片得以顯現
        timeSetButton.setBorder(null); // 讓他沒有按鈕的框框
        timeSetButton.setFocusPainted(false); // 為了不要有一槓

        garrySetButton.setForeground(Color.RED);
        garrySetButton.setFont(font1);
        garrySetButton.setOpaque(false); // 為了讓按鈕去背
        garrySetButton.setContentAreaFilled(false); // 為了讓按鈕去背，讓背景圖片得以顯現
        garrySetButton.setBorder(null); // 讓他沒有按鈕的框框
        garrySetButton.setFocusPainted(false); // 為了不要有一槓

        musicSetButton.setForeground(Color.GREEN);
        musicSetButton.setFont(font1);
        musicSetButton.setOpaque(false); // 為了讓按鈕去背
        musicSetButton.setContentAreaFilled(false); // 為了讓按鈕去背，讓背景圖片得以顯現
        musicSetButton.setBorder(null); // 讓他沒有按鈕的框框
        musicSetButton.setFocusPainted(false); // 為了不要有一槓

        // label
        label.setOpaque(false); // 為了讓按鈕去背

        //因為關掉後再開會清零，所以把變數記在主程式裡面，打開這個的時候就可以先辨識。
        if (try01Code.timeSet == 1) {
            timeSetButton.setSelected(true);
        } 
        else {
            timeSetButton.setSelected(false);
        }

        if( try01Code.garrySet == 1){
            garrySetButton.setSelected(true);
        }
        else{
            garrySetButton.setSelected(false);
        }

        if (try01Code.musicSet == 1) {
            musicSetButton.setSelected(true);
        } else {
            musicSetButton.setSelected(false);
        }

        timeSetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (try01Code.timeSet == 0) {
                    for (int i = 0; i < 6; i++) {
                        try01Code.jT[i].setVisible(false);
                    }
                    try01Code.timeSet = 1;   //按鈕要變
                    System.out.println("TimeSet");
                } else {
                    System.out.println("no TimeSet");
                    for (int i = 0; i < 6; i++) {
                        try01Code.jT[i].setVisible(true);
                    }
                    try01Code.timeSet = 0;   // 按鈕要變
                }
                
            }
        });

        garrySetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (try01Code.garrySet == 0) {
                    System.out.println("garrySet");
                    //
                    try01Code.button.setVisible(false);
                    //
                    try01Code.garrySet = 1;
                } else {
                    System.out.println("no garrySet");
                    try01Code.button.setVisible(true);
                    try01Code.garrySet = 0;
                }
            }
        });

        musicSetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (try01Code.musicSet == 0) {
                    System.out.println("musicSet");
                    try01Code.musicstop.setVisible(false);
                    try01Code.musicSet = 1;
                } else {
                    System.out.println("no musicSet");
                    try01Code.musicstop.setVisible(true);
                    try01Code.musicSet = 0;
                }
            }
        });

        jpn1.add(label);
        jpn1.add(timeSetButton);
        jpn1.add(garrySetButton);
        jpn1.add(musicSetButton);

    }

    public static void main(String[] args){
        RaidoBottons mfrm = new RaidoBottons();
        mfrm.setVisible(true);
    }
}
