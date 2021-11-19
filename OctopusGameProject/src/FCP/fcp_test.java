package FCP;

import java.util.Timer;
import java.util.TimerTask;

public class fcp_test {
	static int countDown = 0;
	
	public static void main(String[] args) {
		int heart=3;
		fcp_gui fg = new fcp_gui();

		//3초 뒤 게임 시작
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				if(countDown<3) {
					System.out.println(3-countDown+"...");
					countDown++;
				}
				else {
					t.cancel();
					System.out.println("게임 스타트");
					fg.startGame(heart);
				}
			}
		};
		t.schedule(tt, 1000,1000);
	}
	
}
