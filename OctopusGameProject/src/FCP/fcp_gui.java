package FCP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class fcp_gui {
	final int BTN_CNT = 32; //�� ���� ��ư ��
	final int PAN_WIDTH = 100; //���� ����
	final int PAN_HEIGHT = 100; //���� ����
	static int count = 1;
	
	String nowColor[]=new String[BTN_CNT]; //���� ���� ����
	JButton pan[]=new JButton[BTN_CNT]; //��
	JFrame jf; 
	JLabel timeJl; //���� �ð��� �˷���
	int redCnt = 0; //player�� �� ��(red)
	int blueCnt = 0; //master�� �� ��(blue)
	
	String playerColor = "red"; //������� �� ��
	String masterColor = "blue"; //��ǻ���� �� ��
	
	//������ ����
	fcp_gui(){
		jf = new JFrame("�� ������");
		//â�� ���� �� ���α׷� ����
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.setSize(1200,900);
		//������(���� x �ִ� ��) ���̰� ����
		jf.setVisible(true);
		
		timeJl=new JLabel("���� �ð� : 20");
		timeJl.setBounds(1200, 20, 200, 30);
		//jf.getContentPane().add(timeJl);
		
		int i,a;        
		int pan_y = 100;
		setNowColor();
		for(i=0, a=0; i<BTN_CNT; i++,a++) {
			pan[i] = new JButton();
			
			//�����ϱ� ������  ��Ȱ��ȭ
			pan[i].setEnabled(false);
			
			//��ư �ٹٲ��� ���� �ڵ�
			if(i == 8 || i == 16 || i == 24 ||i == 32) {
				pan_y += 170;
				a = 0;
			}
			pan[i].setBounds(50+(a*140), pan_y, PAN_WIDTH, PAN_HEIGHT);
			if(nowColor[i].equals(playerColor)) pan[i].setBackground(Color.RED);
			else pan[i].setBackground(Color.BLUE);
			
			final int tmp = i; //�̺�Ʈ ������ �ȿ��� ���� ���� ���
			//��ư�鿡 Ŭ�� �̺�Ʈ �ο�
			pan[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//��ư �� �ٲ�
					setColorBtn(tmp);
				}
			});
			
			jf.getContentPane().add(pan[i]);
		}
	}
	
	//�ʱ� �� ������ ���� �Լ�
	private void setNowColor() {
		Random random = new Random();
		int arr[] = new int[32];
		int zeroCnt = 0;
		int i;
		for( i=0; i<arr.length; i++) {
			int ran = random.nextInt(2);
			if(ran == 0) zeroCnt++;
			if(zeroCnt == 16) break;
				arr[i]= ran;
		}
		//������ �Ķ������� ä���
		for(int j=i; j<arr.length; j++) {
			arr[j]=1;
		}   
		
		for(i=0; i<BTN_CNT; i++) {
			if(arr[i] == 0) nowColor[i] = playerColor;
			else nowColor[i] = masterColor;
		}	
	}
	
	//nowColor�� ���� ��ư �� �ٲ�
	private void setColorBtn(int tmp) {
		if(nowColor[tmp].equals(playerColor)) {
			nowColor[tmp] = masterColor;
			pan[tmp].setBackground(Color.BLUE);
		}
		else {
			nowColor[tmp] = playerColor;
			pan[tmp].setBackground(Color.RED);
		}
	}
	
	//������ �������� �� ���� ������ ��
	private boolean countPan() {
		for(int i=0; i<BTN_CNT; i++) {
			if(nowColor[i].equals(playerColor)) redCnt++;
			else blueCnt++;
		}
		if(redCnt>blueCnt) {
			System.out.println("�̰���");
			return true;
		} 
		else if(blueCnt>=redCnt ) {
			System.out.println("����"); 
			return false;
		}
		return true;
	}
	
	//�ڵ����� �� ������
	private void com() {
		Random random = new Random();
		int t = random.nextInt(3000)+1000;
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				if(count <= 20) {
					int ran = random.nextInt(32);
					if(nowColor[ran].equals(playerColor)) {
						nowColor[ran] = masterColor;
						pan[ran].setBackground(Color.BLUE);
					}
				}
				else timer.cancel();
				//���� - �ð�, �ٲ� �迭�� ��
			}
		};
		timer.schedule(task, 0 , t);
	}
	
	//�� ������ ���� ����
	public int startGame(int heart) {

		for(int j=0; j<BTN_CNT; j++) {
			pan[j].setEnabled(true);
		}
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				com();
				if(count<=20) {
					System.out.println("�����ð� : "+(20-count));
					count++;
				}
				else {
					timer.cancel();
					if(!countPan()) {
						System.out.println(playerColor+" : "+redCnt+", "+ masterColor + " : "+blueCnt);
					}
					else {
						System.out.println(playerColor+" : "+redCnt+", "+ masterColor + " : "+blueCnt);
					}
					System.out.println("���� ��");
					for(int i=0; i<BTN_CNT; i++) {
						pan[i].setEnabled(false);
					}
				}
			}
		};
		timer.schedule(task, 1000, 1000);
		
		return heart; 
	}
}
	
