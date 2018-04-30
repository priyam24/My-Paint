/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roypaint;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class MyFrame extends JFrame{
    public MyFrame(){
        this.setTitle("MY PAINT");
        this.setLayout(null);
        ImageIcon icon=new ImageIcon(getClass().getResource("ab.jpg"));
        this.setIconImage(icon.getImage());
        this.setResizable(false);       
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(536,700);        
        this.setContentPane(new JLabel(new ImageIcon(getClass().getResource("a.jpg"))));
    }
}

public class RoyPaint extends JPanel implements ActionListener, MouseListener {
Timer t = new Timer(1, this);
private static int putki=1;
int [][]a,b,c,r;
String [][]str;
Color []ac,bc,cc,rc,sc;
private static int lim,lim1,lim2,lim3,lim4;
private static int shape;
private static Color clr;

public RoyPaint() {
	t.start();
        this.setBounds(10, 50, 500, 500);
        a=new int[100][4];
        b=new int[100][4];
        c=new int[10000000][2];
        r=new int[100][4];
        str=new String[100][4];
        ac=new Color[100];
        bc=new Color[100];
        cc=new Color[10000000];
        rc=new Color[100];
        sc=new Color[100];
        lim=-1;
        lim1=-1;
        lim2=-1;
        lim3=-1;
        lim4=-1;
	addMouseListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
}

@Override
public void paintComponent(Graphics g) {
	super.paintComponent(g);
        if(lim>-1){
            for(int i=0;i<=lim;i++){
                g.setColor(ac[i]);
                g.drawOval(a[i][0], a[i][1], a[i][2], a[i][3]);
            }
        }
        if(lim1>-1){
            for(int i=0;i<=lim1;i++){
                g.setColor(bc[i]);
                g.drawLine(b[i][0], b[i][1], b[i][2], b[i][3]);
            }
        }
        if(lim2>-1){
            for(int i=0;i<=lim2;i++){
                g.setColor(cc[i]);
                g.fillOval(c[i][0], c[i][1], 5, 5);
            }
        }
        if(lim3>-1){
            for(int i=0;i<=lim3;i++){
                g.setColor(rc[i]);
                g.drawRect(r[i][0], r[i][1], r[i][2], r[i][3]);
            }            
        }
        if(lim4>-1){
            for(int i=0;i<=lim4;i++){
                g.setColor(sc[i]);
                if(str[i][3]=="done"){
                    g.drawString(str[i][0], Integer.parseInt(str[i][1]), Integer.parseInt(str[i][2]));
                }
            }            
        }        
        
}

@Override
public void actionPerformed(ActionEvent e) {
    if(putki==0){
    Point p=this.getMousePosition();
    if(p!=null){
        int x=(int) p.getX();
        int y=(int)p.getY();
        if(shape==1){
            a[lim][2]=x-a[lim][0];
            a[lim][3]=y-a[lim][1];
        }
        else if(shape==2){
            b[lim1][2]=x;
            b[lim1][3]=y;
        }
        else if(shape==3){
            lim2++;
            cc[lim2]=clr;
            c[lim2][0]=x;
            c[lim2][1]=y;
        }
        else if(shape==4){
            r[lim3][2]=x-r[lim3][0];
            r[lim3][3]=y-r[lim3][1];
        }
    }
    //repaint();
    }
    repaint();
}

public static void main (String arge[]){
        
	MyFrame f = new MyFrame();

	RoyPaint s = new RoyPaint();
        s.setBackground(Color.white);
        
	f.add(s);
        
        shape=0;
        
        JButton oval=new JButton("Oval");
        oval.setBounds(10, 10, 100, 20);
        oval.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shape=1;
            }
        });
        f.add(oval);
        
        JButton line=new JButton("Line");
        line.setBounds(410, 10, 100, 20);  
        line.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shape=2;
            }
        });
        f.add(line);
        
        JButton pen=new JButton("Pen");
        pen.setBounds(200, 10, 100, 20);  
        pen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shape=3;
            }
        });
        f.add(pen);    

        JButton rect=new JButton("Rectangle");
        rect.setBounds(200, 560, 100, 20);  
        rect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shape=4;
            }
        });
        f.add(rect);             
        
        JButton alph=new JButton("ABC");
        alph.setBounds(200, 590, 100, 20);  
        alph.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shape=5;
            }
        });
        f.add(alph); 
       
        JButton save=new JButton("Save");
        save.setBounds(200, 620, 100, 50);  
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage image=new BufferedImage(s.getWidth(),s.getHeight(),BufferedImage.TYPE_INT_RGB);
                s.paint(image.createGraphics());
                    JFileChooser fc=new JFileChooser();   
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fc.setAcceptAllFileFilterUsed(false);
                    int i=fc.showSaveDialog(null);
                    if(i==JFileChooser.APPROVE_OPTION){    
                       File f=fc.getSelectedFile();
                       String filepath=f.getPath();                  
                File folder=new File(filepath);
                String filename=JOptionPane.showInputDialog(s,"Enter your file name !", "Save", JOptionPane.PLAIN_MESSAGE);
                if(filename!=null){
                    File file=new File(folder.getPath()+"//"+filename);
                    try {                
                        ImageIO.write(image, "jpg", file);
                        JOptionPane.showMessageDialog(s, "Saved");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(s, "Problem in saving !!!");
                    }
                }
                    }
            }
        });
        f.add(save);  

        JButton refresh=new JButton("Refresh");
        refresh.setBounds(10, 560, 100, 50);  
        refresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shape=0;
                lim=-1;
                lim1=-1;
                lim2=-1;
                lim3=-1;
                lim4=-1;
                putki=1;
            }
        });
        f.add(refresh);       
        
        JButton color=new JButton("Color");
        color.setBounds(410, 560, 100, 50);  
        color.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                clr=JColorChooser.showDialog(null, "Select a color", Color.green);
            }
        });
        f.add(color);          
        
	f.setVisible(true);

}

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getComponent()!=this){
            return;
        }
        putki=0;
    switch (shape) {
        case 1:
            lim++;
            ac[lim]=clr;
            a[lim][0]=e.getX();
            ;
            a[lim][1]=e.getY();
            a[lim][2]=0;
            a[lim][3]=0;
            break;
        case 2:
            lim1++;
            bc[lim1]=clr;
            b[lim1][0]=e.getX();
            b[lim1][1]=e.getY();
            b[lim1][2]=b[lim1][0];
            b[lim1][3]=b[lim1][1];
            break;
        case 3:
            lim2++;
            cc[lim2]=clr;
            c[lim2][0]=e.getX();
            c[lim2][1]=e.getY();
            break;
        case 4:
            lim3++;
            rc[lim3]=clr;
            r[lim3][0]=e.getX();
            ;
            r[lim3][1]=e.getY();
            r[lim3][2]=0;
            r[lim3][3]=0;
            break;
        case 5:
            lim4++;
            sc[lim4]=clr;
            str[lim4][3]=""; //makes sure so that paintComponent doesn't paint this string uuntill input not taken to avoid null input causing error
            str[lim4][0]=JOptionPane.showInputDialog(this, "Write your text here !", "Input",JOptionPane.PLAIN_MESSAGE);
            str[lim4][3]="done";
            str[lim4][1]=Integer.toString(e.getX());
            str[lim4][2]=Integer.toString(e.getY());
            for(int k=0;k<4;k++){
                if(str[lim4][k]==null){
                    lim4--;
                    break;
                }
            }
            putki=1; //as mouse released is not applicable for string input so we have to perform its job here
            break;
        default:
            break;
    }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        putki=1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }


}