import java.awt.*;
import javax.swing.*;
// import javax.swing.swt.graphics.GC;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class DemoScrollBarUI_Green extends BasicScrollBarUI {

    // 滾輪寬度
    private static final int thumbWidth = 12;

    // 滾輪透明度
    private static final float opaque = 0.5f;

    // 手柄颜色
    private static final Color thumbColorFrom = new Color(243, 246, 200);// (175, 210, 252);
    private static final Color thumbColorTo = new Color(161, 226, 194);

    // 滑道颜色
    private static final Color backColorFrom = new Color(161, 226, 194);
    private static final Color backColorTo = new Color(243, 246, 215);

    // 邊框顏色
    @Override
    protected void configureScrollBarColors() {
        thumbColor = Color.GRAY;
        thumbHighlightColor = Color.BLUE;
        thumbDarkShadowColor = Color.BLACK;
        thumbLightShadowColor = Color.YELLOW;
    }

    // 設定滾輪寬度！
    @Override
    public Dimension getPreferredSize(JComponent c) {

        c.setPreferredSize(new Dimension(thumbWidth, 0));
        return super.getPreferredSize(c);
        // super 很像 this ，一個是用在classe，一個用物件
    }

    // 重劃滾輪滑動區域的顏色
    @Override
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = null;
        // 判斷滾輪是否垂直，我自動換行後就不需要判斷了
        // 直的！
        gp = new GradientPaint(0, 0, backColorFrom, 0, trackBounds.height, backColorTo);   //拿來漸層
        // if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
        // // 颜色漸層
        // gp = new GradientPaint(0, 0, backColorFrom, 0, trackBounds.height,
        // backColorTo);
        // }
        // if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
        // gp = new GradientPaint(0, 0, backColorFrom, trackBounds.width, 0,
        // backColorTo);
        // }

        g2.setPaint(gp);
        // 填充Track
        // g2.fillRoundRect(trackBounds.x, trackBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        // g2.drawRoundRect(trackBounds.x, trackBounds.y, thumbBounds.width - 1, thumbBounds.height - 1, 10, 10);

        // 繪製滾輪外面不變的框框的顏色
        g2.setColor(new Color(200, 155, 200));
        // 左上角座標(20，30)，寬，高，圓角的長軸是18，短軸是15。
        // RoundRectangle2D rectRound = new RoundRectangle2D.Double(trackBounds.x, trackBounds.y, trackBounds.width - 1, trackBounds.height - 1, 18, 15);
        // g2.drawRoundRectangle((float)trackBounds.x, 
        //         (float)trackBounds.y, (float)(trackBounds.width - 1), (float)(trackBounds.height - 1), (float)18, 
        //         (float)15);
        g2.drawRect(trackBounds.x, trackBounds.y, trackBounds.width - 1, trackBounds.height - 1);

        if (trackHighlight == BasicScrollBarUI.DECREASE_HIGHLIGHT) {
            this.paintDecreaseHighlight(g);
        }
        if (trackHighlight == BasicScrollBarUI.INCREASE_HIGHLIGHT) {
            this.paintIncreaseHighlight(g);
        }
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2D = (Graphics2D) g;

        // 以繪製區的x、y座標當原點：一定要加上，不然就不給你動！！
        g2D.translate(thumbBounds.x, thumbBounds.y);
        // 滾輪顏色
        g2D.setColor(thumbColor);

        // 讓滾輪的角角變圓一點
        // g2D.drawRoundRect(x座標, y, 寬度, 高度, 10, 10);
        // 畫矩形，讓他的角角變圓形(改面圓角弧度，後面兩個數字
        g2D.drawRoundRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1, 10, 10);

        // 消除鋸齒
        Graphics2D g2 = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.addRenderingHints(rh);

        // 半透明
        // g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opaque));

        // 填滿顏色，漸層由下往上
        g2.setPaint(new GradientPaint(c.getWidth() / 2, 1, thumbColorFrom, c.getWidth() / 2, c.getHeight(),
                thumbColorTo));

        // 填滿矩形的顏色，角角變圓形，後面兩個數字
        g2.fillRoundRect(0, 0, thumbBounds.width - 1, thumbBounds.height - 1, 10, 10);

    }

    // null禁止出現上方按鈕
    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton();
        button.setBorder(null);
        return button;
    }

    // null禁止出現下方按鈕
    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new JButton();
        button.setBorder(null);
        return button;
    }

    public static void main(String[] args) {

        JFrame jf = new JFrame("測試測試！！！");
        jf.setSize(550, 750);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        JTextArea jta = new JTextArea("123");

        jta.setFont(new Font("Arial", Font.BOLD, 100));
        jp.setLayout(null);

        for (int index = 0; index < 1000; index++) {
            jp.add(new JButton("asssssssssssssssssssssssssssssssssssss"));
        }

        jta.setLineWrap(true); // 自動換行

        JScrollPane scrollPane = new JScrollPane(jta);
        scrollPane.getVerticalScrollBar().setUI(new DemoScrollBarUI_Green());
        // scrollPane.getHorizontalScrollBar().setUI(new DemoScrollBarUI());
        scrollPane.setViewportView(jta);
        jf.add(scrollPane);
        jf.setVisible(true);
    }
}
