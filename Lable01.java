import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;

public class Lable01 extends JInternalFrame {
    private Container cp;
    private JTextArea jta = new JTextArea(); // 打字的地方
    private JScrollPane jsp = new JScrollPane(jta);
    private JTextField jtf = new JTextField("30", 10); // 字體大小
    public static JButton jmLast = new JButton("<-");
    private JButton jmSave = new JButton("Save");
    private JLabel jmFont = new JLabel("字體大小：", JLabel.RIGHT);
    private JButton jmOk = new JButton("Ok");

    private JLabel jmPic = new JLabel(new ImageIcon("Blue.jpg"));
    private ImageIcon jImage[] = new ImageIcon[4];
    int picture = 0;

    private JButton jbntClear = new JButton("Clear");
    private JButton jbntBGColor = new JButton("BG Color");
    private JButton jbntTextColor = new JButton("Text Color");
    private JComboBox jcbox = new JComboBox();
    private JPanel imPanel = (JPanel) getContentPane(); // 背景顏色
    private JPanel enterPanel = new JPanel();
    private JPanel downPanel = new JPanel(new GridLayout(1, 4, 5, 5));

    //滾輪
    private DemoScrollBarUI_Blue ScrollBarUI_Blue = new DemoScrollBarUI_Blue();
    private DemoScrollBarUI_Pink ScrollBarUI_Pink = new DemoScrollBarUI_Pink();
    private DemoScrollBarUI_Green ScrollBarUI_Green = new DemoScrollBarUI_Green();
    private DemoScrollBarUI_Yellow ScrollBarUI_Yellow = new DemoScrollBarUI_Yellow();

    public Lable01(String title, boolean flag1, boolean flag2, boolean flag3, boolean flag4) {
        init();
    }

    private void init() {
        cp = this.getContentPane();
        this.setTitle("Write");
        this.setBounds(0, 0, 500, 600);
        cp.setLayout(new BorderLayout(1, 1));
        this.getLayeredPane().add(jmPic, Integer.valueOf(Integer.MIN_VALUE));  //標籤放到第二面板
        this.setVisible(true);

        // scroll
        jsp.getVerticalScrollBar().setUI(ScrollBarUI_Blue);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 背景
        jImage[0] = new ImageIcon("Blue.jpg");
        jImage[1] = new ImageIcon("pink.jpg");
        jImage[2] = new ImageIcon("green.jpg");
        jImage[3] = new ImageIcon("addmix.jpg");

        Image temp = jImage[2].getImage().getScaledInstance(550, 870, jImage[2].getImage().SCALE_DEFAULT); // 設定圖片大小(縮放)
        jImage[2] = new ImageIcon(temp);

        temp = jImage[3].getImage().getScaledInstance(550, 750, jImage[3].getImage().SCALE_DEFAULT); // 設定圖片大小(縮放)
        jImage[3] = new ImageIcon(temp);

        jta.setBorder(BorderFactory.createTitledBorder("Text")); // (Color.black, "Text", TitledBorder.CENTER,
        downPanel.setBorder(BorderFactory.createTitledBorder("Tool"));

        jsp.setOpaque(false); // 兩個都要？？我問號？？
        jsp.getViewport().setOpaque(false); // 視窗尺寸的比例，getViewport(): 根據比例幫你弄成透明
        jsp.setBorder(null); // 加了比較好看

        jmPic.setBounds(0, 0, 550, 750);
        jmLast.setBounds(0, 0, 50, 35);
        imPanel.setOpaque(false);
        enterPanel.setOpaque(false);
        downPanel.setOpaque(false);
        jta.setOpaque(false);
        jmPic.setOpaque(false);
        jta.setOpaque(false);

        jta.setLineWrap(true);  //自動換行

        // 字型
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        jcbox = new JComboBox<String>(fonts);
        jcbox.setSelectedItem("Arial");

        enterPanel.add(jmLast);
        enterPanel.add(jmSave);
        enterPanel.add(jmFont);
        enterPanel.add(jtf);
        enterPanel.add(jmOk);
        downPanel.add(jbntClear);
        downPanel.add(jbntBGColor);
        downPanel.add(jbntTextColor);
        downPanel.add(jcbox);   

        imPanel.add(jsp);
        imPanel.add(enterPanel, BorderLayout.NORTH);
        imPanel.add(downPanel, BorderLayout.SOUTH);
        // cp.add(imPanel);

        jta.setFont(new Font(jcbox.getSelectedItem().toString(), Font.PLAIN, Integer.parseInt(jtf.getText())));

        jbntClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Object[] options = { "Yes動手吧", "Nooo住手！" };
                int n = JOptionPane.showOptionDialog(Lable01.this, "真的要清除嗎？", "Clearingﾟ*:.｡.*･'(*ﾟ▽ﾟ*)'･*:.｡. ", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry6.png"), options, options[0]);
                if (n == 0) {
                    jta.setText("");
                }
            }
        });

        jbntBGColor.addActionListener(new ActionListener() { // 改成圖紙的fu
            public void actionPerformed(ActionEvent ae) {
                picture++;
                picture = picture % 4;
                jmPic.setIcon(jImage[picture]);
                jmPic.setIcon(jImage[picture]);
                if (picture % 4 == 0) { // Blue
                    jsp.getVerticalScrollBar().setUI(ScrollBarUI_Blue);
                } else if (picture % 4 == 1) { // Pink
                    jsp.getVerticalScrollBar().setUI(ScrollBarUI_Pink);
                } else if (picture % 4 == 2) { // Green
                    jsp.getVerticalScrollBar().setUI(ScrollBarUI_Green);
                } else if (picture % 4 == 3) { // Green
                    jsp.getVerticalScrollBar().setUI(ScrollBarUI_Yellow);
                }
            }
        });

        jbntTextColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JColorChooser colorChooser = new JColorChooser();
                JDialog dialog = JColorChooser.createDialog(cp, "Choose color", false, colorChooser,
                        e1 -> jta.setForeground(colorChooser.getColor()), null);
                dialog.setVisible(true);
            }
        });

        // jcbox.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent ae) {
        //         if (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("新細明體")) {
        //             jta.setFont(new Font("新細明體", Font.BOLD, Integer.parseInt(jtf.getText())));
        //         } else if (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("標楷體")) {
        //             jta.setFont(new Font("標楷體", Font.BOLD, Integer.parseInt(jtf.getText())));
        //         } else if (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("Muyao-Softbrush")) {
        //             jta.setFont(new Font("Muyao-Softbrush", Font.BOLD, Integer.parseInt(jtf.getText())));
        //         } else if (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("STCaiyun")) {
        //             jta.setFont(new Font("STCaiyun", Font.BOLD, Integer.parseInt(jtf.getText())));
        //         }
        //         // 往下加
        //     }
        // });

        jcbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == jcbox) {
                    jta.setFont(new Font((String) jcbox.getSelectedItem(), Font.PLAIN, jta.getFont().getSize()));
                }
            }
        });

        jmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser jfc = new JFileChooser(".\\"); // mac"./"
                if (jfc.showSaveDialog(Lable01.this) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    try {
                        FileWriter fw = new FileWriter(selectedFile, true);   //不會複寫，但會往後加
                        char data[] = jta.getText().toCharArray();
                        fw.write(data);
                        fw.close();
                    } catch (IOException e) {
                        System.out.println(e.toString());
                        // System.out.println(selectedFile.getName());
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        // System.out.println(selectedFile.getPath());
                    }

                }
            }
        });

        jmLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 回到主畫面               
            }
        });

        jmOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // if (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("新細明體")) {
                // jta.setFont(new Font("新細明體", Font.BOLD, Integer.parseInt(jtf.getText())));
                // } else if (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("標楷體")) {
                // jta.setFont(new Font("標楷體", Font.BOLD, Integer.parseInt(jtf.getText())));
                // } else if
                // (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("Muyao-Softbrush")) {
                // jta.setFont(new Font("Muyao-Softbrush", Font.BOLD,
                // Integer.parseInt(jtf.getText())));
                // } else if (jcbox.getItemAt(jcbox.getSelectedIndex()).equals("STCaiyun")) {
                // jta.setFont(new Font("STCaiyun", Font.BOLD,
                // Integer.parseInt(jtf.getText())));
                // }

                jta.setFont(new Font(jcbox.getSelectedItem().toString(), Font.PLAIN, Integer.parseInt(jtf.getText())));

            }
        });

    }

    // public static void main(String[] args) {
    //     Lable01 mfrm = new Lable01();
    //     mfrm.setVisible(true);
    // }

}