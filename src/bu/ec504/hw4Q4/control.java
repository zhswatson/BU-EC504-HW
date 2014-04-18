package bu.ec504.hw4Q4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class control implements KeyListener{
	static int cnt;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		control C = new control();
		while(cnt < 100) {
			;
		}
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
        	System.out.println("UP Sent");
        	cnt++;
        }
        if (code == KeyEvent.VK_S) {
        	System.out.println("DOWN Sent");
        }
        if (code == KeyEvent.VK_A) {
        	System.out.println("LEFT Sent");
        }
        if (code == KeyEvent.VK_D) {
        	System.out.println("DOWN Sent");
        }
 		
	}

}
