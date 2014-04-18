package bu.ec504.hw4Q4;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class uisnake {

	static class TCPClient extends JPanel implements ActionListener,
			KeyListener {
		static String[][] GAME;
		static int boardSizeX;
		static int boardSizeY;
		static BufferedReader inFromUser;
		static Socket clientSocket;
		static DataOutputStream outToServer;
		static BufferedReader inFromServer;
		static String points;
		static String name;

		public void setName(String name) {
			TCPClient.name = name;
		}

		static double x, y;
		static double velX = 0, velY = 0;

		public static String[][] getGAME() {
			return GAME;
		}

		public static int getBoardSizeX() {
			return boardSizeX;
		}

		public static int getBoardSizeY() {
			return boardSizeY;
		}

		public static String getPoints() {
			return points;
		}

		public static boolean send(String message) throws Exception {
			String m = "";
			outToServer.writeBytes(message + "\n");
			while (!m.contains("You sent:")) {
				m = fetch();
				if (m.contains("GAME OVER!")) {
					return false;
				}
			}
			if (m.contains("BOARD")) {
				while (!m.contains("Board Size:")) {
					m = fetch();
					if (m.contains("GAME OVER!")) {
						return false;
					}
				}
				boardSizeX = Integer.parseInt(m.substring(m.indexOf(":") + 2,
						m.indexOf("x") - 1));
				boardSizeY = Integer.parseInt(m.substring(m.indexOf("x") + 2,
						m.indexOf("x") + 4));
				String heightInfo = m.substring(20);
				for (int i = 0; i < boardSizeY; i++)
					for (int j = 0; j < boardSizeX; j++) {
						GAME[i][j] = heightInfo.substring(i
								* (2 * boardSizeX + 1) + 2 * j, i
								* (2 * boardSizeX + 1) + 2 * j + 1);
					}
			} else if (m.contains("SNAKES")) {
				while (!m.contains("java.awt.Point")) {
					m = fetch();
					if (m.contains("GAME OVER!")) {
						return false;
					}
				}
				while (m.contains("java.awt.Point")) {
					m = m.substring(m.indexOf("java.awt.Point") + 14);
					int snakeX, snakeY;
					snakeX = Integer.parseInt(m.substring(m.indexOf("x=") + 2,
							m.indexOf(",")));
					snakeY = Integer.parseInt(m.substring(m.indexOf("y=") + 2,
							m.indexOf("]")));
					GAME[snakeY][snakeX] = "S";
				}
			} else if (m.contains("FOOD")) {
				while (!m.contains("Number of foods:")) {
					m = fetch();
					if (m.contains("GAME OVER!")) {
						return false;
					}
				}
				while (m.contains("java.awt.Point")) {
					m = m.substring(m.indexOf("java.awt.Point") + 14);
					int foodX, foodY;
					foodX = Integer.parseInt(m.substring(m.indexOf("x=") + 2,
							m.indexOf(",")));
					foodY = Integer.parseInt(m.substring(m.indexOf("y=") + 2,
							m.indexOf("]")));
					GAME[foodY][foodX] = "F";
				}
			} else if (m.contains("POINTS")) {
				while (!m.contains("Points:")) {
					m = fetch();
					if (m.contains("GAME OVER!")) {
						return false;
					}
				}
				points = m.substring(8, m.length());
			} else if (m.contains("UP")) {

			} else if (m.contains("DOWN")) {

			} else if (m.contains("RIGHT")) {

			} else if (m.contains("LEFT")) {

			} else {
				while (!m.contains("You will now be called")) {
					m = fetch();
					if (m.contains("GAME OVER!")) {
						return false;
					}
				}
			}
			return true;
		}

		public void show() {
			try {
				if (send("BOARD") && send("FOOD") && send("SNAKES")
						&& send("POINTS"))
					repaint();
				else
					JOptionPane.showMessageDialog(null, "GAME OVER!");

			} catch (Exception e) {
				e.printStackTrace();
			}
			// for (int i = 0; i < boardSizeY + 2; i++) {
			// for (int j = 0; j < boardSizeX + 12; j++) {
			// if (i == 0 || i == boardSizeY + 1 || j == 0
			// || j == boardSizeX + 1 || j == boardSizeX + 11) {
			// System.out.print("###");
			// } else if (i > 0 && i < boardSizeY + 1 && j > 0
			// && j < boardSizeX + 1) {
			// System.out.print(" " + GAME[i - 1][j - 1] + " ");
			// }
			// // Game Stats
			// else {
			// if (i == 1) {
			// if (j == boardSizeX + 2)
			// System.out.print("        Game  Stats        ");
			// } else if (i == 3) {
			// if (j == boardSizeX + 2) {
			// int pointLen = points.length();
			// for (int x = 0; x < (9 - pointLen / 2); x++) {
			// System.out.print(" ");
			// }
			// System.out.print("Points: " + points);
			// for (int x = 0; x < (10 - pointLen + pointLen / 2); x++) {
			// System.out.print(" ");
			// }
			// }
			// } else
			// System.out.print("   ");
			// }
			// }
			// System.out.print("\n");
			// }
		}

		public static String fetch() throws IOException {
			char[] buf = new char[1000];
			int d = 0;
			String tmp = "";
			d = inFromServer.read(buf);
			tmp = new String(buf);
			tmp = tmp.substring(0,d);
//			if (d > 0) {
//				System.out.print(d + " ");
//				System.out.println("fetched: " + tmp);
//			}
			return tmp;
		}

		public TCPClient() throws Exception {
			super();
			GAME = new String[100][100];
			boardSizeX = 0;
			boardSizeY = 0;
			inFromUser = new BufferedReader(new InputStreamReader(System.in));
			clientSocket = new Socket("algorithmics.bu.edu", 11000);
			// clientSocket = new Socket("192.168.1.118", 11000);
			outToServer = new DataOutputStream(clientSocket.getOutputStream());
			inFromServer = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			points = "0";
			name = "EC504";
			Timer t = new Timer(80, this);
			t.start();
			addKeyListener(this);
			setFocusable(true);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			Font f1 = new Font("Times New Roman", Font.PLAIN, 32);
			Font f2 = new Font("Times New Roman", Font.PLAIN, 20);
			g2.setFont(f1);
			g2.drawString("Welcome to the Snake Game", 100, 90);
			for (int i = 0; i < boardSizeY; i++)
				for (int j = 0; j < boardSizeX; j++) {
					Shape SQ = new Rectangle.Double(100 + j * 20, 100 + i * 20,
							20, 20);
					if (GAME[i][j].equals("F"))
						g2.setColor(Color.RED);
					else if (GAME[i][j].equals("S"))
						g2.setColor(Color.GREEN);
					else
						g2.setColor(new Color(255 - 20 * Integer
								.parseInt(GAME[i][j]), 255 - 20 * Integer
								.parseInt(GAME[i][j]), 255));
					g2.fill(SQ);
				}
			g2.setColor(Color.BLACK);
			g2.setFont(f2);
			g2.drawString("Game Stats", 520, 150);
			g2.drawString("Name: " + name, 520, 200);
			g2.drawString("Score: " + points, 520, 250);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			show();

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_W) {
				try {
					send("UP");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			if (code == KeyEvent.VK_S) {
				try {
					send("DOWN");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (code == KeyEvent.VK_A) {
				try {
					send("LEFT");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if (code == KeyEvent.VK_D) {
				try {
					send("RIGHT");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	public static void main(String args[]) throws Exception {
		String s = (String) JOptionPane
				.showInputDialog("Please enter your name!");
		TCPClient tcpc = new TCPClient();
		tcpc.setName(s);
		TCPClient.send(s);
		JFrame f = new JFrame();
		f.add(tcpc);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);

	}

}