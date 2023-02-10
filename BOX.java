import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;   //上面小小圖片的
import javax.imageio.ImageIO; //上面小小圖片的

public class BOX extends JFrame {
    private Container cp;
    private JDesktopPane jdp = new JDesktopPane();
    private JTabbedPane jtp = new JTabbedPane();
    private BOXInternalFrame jif1 = new BOXInternalFrame("journal 01", true, true, true, true);
    private BOXInternalFrame02 jif2 = new BOXInternalFrame02("journal 02", true, true, true, true);
    private JMenuBar jmb1 = new JMenuBar();    
    private JMenu jmF = new JMenu("Data");
    private JMenuItem jmiFO = new JMenuItem("Clean");
    private JMenuItem jmiFS = new JMenuItem("Sava");

    private JMenuBar jmb2 = new JMenuBar();
    private JMenu jmF2 = new JMenu("Data");
    private JMenuItem jmiFO2 = new JMenuItem("Clean");
    private JMenuItem jmiFS2 = new JMenuItem("Sava");

    public BOX() {
        init();
    }

    private void init() {
        cp = this.getContentPane();
        this.setTitle("Look ~ journal -OvO-");
        this.setResizable(false);

        jif1.setVisible(true);
        jif1.setOpaque(false);
        jif2.setVisible(true);
        jif2.setOpaque(false);

        jif1.setJMenuBar(jmb1);
        jif2.setJMenuBar(jmb2);

        cp.add(jtp);
        jtp.addTab("journal 01", jif1);        
        jtp.addTab("journal 02", jif2);
        jmb1.add(jmF);
        jmF.add(jmiFO);
        jmF.add(jmiFS);
        jmb2.add(jmF2);
        jmF2.add(jmiFO2);
        jmF2.add(jmiFS2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //關掉自己就好，不要關主程式
        this.setBounds(700, 60, 550, 750);

        jmiFO.addActionListener(new ActionListener() {   //Cleaning
            public void actionPerformed(ActionEvent ae) {
                Object[] options = {"對，清除QAQ", "Nooo住手"};
                int n = JOptionPane.showOptionDialog(BOX.this, "真的要清除嗎？你的心不痛嗎？", "Clearing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry6.png"), options, options[0]);
                if( n == 0){
                    BOXInternalFrame.jta.setText("");
                }
                
            }
        });

        jmiFS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser jfc = new JFileChooser(".\\"); // mac"./"
                if (jfc.showSaveDialog(BOX.this) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    try {
                        FileWriter fw = new FileWriter(selectedFile);   //會複寫，如果檔名一樣
                        char data[] = BOXInternalFrame.jta.getText().toCharArray();
                        fw.write(data);
                        fw.close();
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                }
            }
        });

        jmiFO2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Object[] options = { "對，清除QAQ", "Nooo住手" };
                int n = JOptionPane.showOptionDialog(null, "真的要清除嗎？你的心不痛嗎？", "Clearing", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, new ImageIcon("garry6.png"), options, options[0]);
                if (n == 0) {
                    BOXInternalFrame02.jta.setText("");
                }
            }
        });

        jmiFS2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser jfc = new JFileChooser(".\\"); // mac"./"
                if (jfc.showSaveDialog(BOX.this) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    try {
                        FileWriter fw = new FileWriter(selectedFile);
                        char data[] = BOXInternalFrame.jta.getText().toCharArray();
                        fw.write(data);
                        fw.close();
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                }
            }
        });

        try {
            Image image = ImageIO.read(this.getClass().getResource("QIcon.png")); // 建立圖片物件
            this.setIconImage(image);// 設定圖示
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    BOX mfrm = new BOX();
    mfrm.setVisible(true);
    }
}
