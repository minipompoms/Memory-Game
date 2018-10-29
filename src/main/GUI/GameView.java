package main.GUI;

import main.CardsBuilder;
import main.GridBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class GameView extends JComponent {

    private int rows;
    private int cols;
    private int cardWidth;
    private int cardHeight;

    private GridBuilder gridBuilder;

    private CardsBuilder cards;

    private CardIcons icons;
    private List<BufferedImage> iconList;

    public GameView() {
        gridBuilder = new GridBuilder(rows, cols);
        cards = gridBuilder.getCardsBuilder();
        icons = new CardIcons(cards);
        icons.assignCardIcons();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        paintGrid(g);
        paintCards(g);

    }

    public void paintGrid(Graphics graphics){
        cardWidth = getWidth() / rows;
        cardHeight = getHeight() / cols;
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.black);
        BasicStroke bs = new BasicStroke(3, 1, BasicStroke.JOIN_ROUND);
        g.setStroke(bs);
        Color backgroundColor = new Color(205, 212, 205);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, (rows * cardWidth), (cols * cardHeight));

    }

    private void paintCards(Graphics graphics){
        cardWidth = getWidth() / rows;
        cardHeight = getHeight() / cols;
        Point point = new Point(0,0);
        icons.setCardIcons();
        Graphics2D g = (Graphics2D) graphics;

        for(int i = 0; i < rows ; i++){
            for(int j = 0; j < cols; j++){
                point.setLocation(getX()+ (cardWidth * j), getY() + (cardHeight * i));
                g.setColor(Color.black);
                paintBoard(g, point.x, point.y);
                g.drawImage(icons.getHiddenCardIcon(), point.x, point.y, null);
               /* if (iconList.get(i).equals("hidden")) {

                }
                else if (iconList.get(i).equals("matched")) {
                    Color backgroundColor = new Color(205, 212, 205);
                    g.setColor(backgroundColor);
                    g.fillOval(point.x, point.y, cardWidth - 45, cardHeight - 30);
                }
                else if (iconList.get(i).equals("guessed")) {
                    g.setColor(Color.pink);
                    g.drawImage(icons.getCardIconList().get(i), point.x, point.y, null);
                }*/

            }

        }

    }

    private void paintBoard(Graphics graphics, int x, int y){
        cardWidth = getWidth() / rows;
        cardHeight = getHeight() / cols;
        Graphics2D g = (Graphics2D) graphics;

        g.setColor(Color.white);
        g.fillOval(x-15, y-20, cardWidth-35, cardHeight-20);
        BasicStroke bs = new BasicStroke(11, 1, BasicStroke.JOIN_ROUND);
        g.setStroke(bs);
        g.setColor(Color.lightGray);
        g.drawOval(x-11, y-16, cardWidth-45, cardHeight-30);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        Point center = new Point(x-15, y-20);
        float radius = cardWidth-50;
        float[] dist = {0f, 1f};
        Color[] colors = {new Color(0, 0, 0, 0), new Color(0, 0, 0, 255)};

        RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);
        g.setPaint(rgp);
        g.fill(new Ellipse2D.Double(center.x, center.y, radius , radius));
        g.fillOval(x-15, y-20, cardWidth-35, cardHeight-20);

    }
    public void testIcons(){
        icons.assignCardIcons();
    }
    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }


}