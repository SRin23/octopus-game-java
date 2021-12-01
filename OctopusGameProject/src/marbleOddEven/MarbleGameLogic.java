package marbleOddEven;

import java.util.Random;
import java.util.Scanner;

public class MarbleGameLogic {
   //기본 setting
   Scanner sc = new Scanner(System.in);
   Random rand = new Random();
   
   //홀짝
   final int odd = 1;
   final int even = 0;
   
   //전역변수
   boolean start;
   int heart;
   int userNumber;
   int computerScore;   //컴퓨터의 구슬의 개수
   int userScore;   //사용자의 구슬의 개수
   
   //초기값 설정
   MarbleGameLogic(boolean start, int heart, int userNumber){
      this.start = start;
      this.heart = heart;
      this.userNumber = userNumber;
   }
   
   //start가 false면 컴퓨터부터, true면 사용자부터 구슬을 쥔다.
   public int gameStart() {
      marbleSetting();
      gamePlay();
      return heart;
   }

   //사용할 구슬 개수 설정
   void marbleSetting() {
      int marble;   //사용할 구슬의 개수
      //사용 구슬의 개수 묻기(최대 구슬 사용 개수 50개)
      while(true) {
         System.out.print("몇개의 구슬을 사용하시겠습니까?(최대 구슬 개수 : 50개) -> ");
         marble = sc.nextInt();
         if(marble>50) {
            System.out.println("최대 구슬 사용 개수를 넘었습니다. 다시 입력해 주십시오.");
            System.out.println();
            continue;
         }
         break;
      }
      
      //사용자&컴퓨터 점수 reset
      computerScore = marble;
      userScore = marble;
   }
   
   //게임 play
      void gamePlay() {
         while(userScore>0&&computerScore>0) {
            System.out.println();
            if(start==true) attack();
            else if(start==false) defence();
         }
         if(userScore<=0) {
            System.out.println("\n");
            System.out.println("----------결과----------");
            System.out.println("당신의 구슬이 존재하지 않습니다.");
            System.out.println("목숨이 하나 사라집니다.");
            heart--;
         }
         else if(computerScore<=0) {
            System.out.println();
            System.out.println("----------결과----------");
            System.out.println("457번의 구슬이 존재하지 않습니다.");
            System.out.println("당신이 이겼습니다.");
            System.out.println("무사히 다음 게임으로 넘어갑니다.");
         }
      }
   
   //사용자 공격(구슬 쥐기)
   void attack() {
      System.out.println("----------공격----------");
      int userSelect;
      
      int marbleCheck = 0;
      
      //손에 쥘 구슬 개수 입력
      while(true) {
         System.out.print("0~" + userScore + "중 손에 쥘 구슬의 개수를 입력하십시오 -> ");
         userSelect = sc.nextInt();
         marbleCheck++;
         if(marbleCheck>=3) {
            System.out.println("범위를 3번 잘못 입력하셨습니다.");
            System.out.println("다음 턴으로 돌아갑니다.");
            start = false;
            break;
         }
         if(userSelect > userScore) {
            System.out.println(userSelect + "만큼의 구슬이 존재하지 않습니다.");
            System.out.println("다시 선택해 주십시오");
            continue;
         }
         break;
      }
      
      //컴퓨터가 홀/짝 중 선택
      int computerOddEven = rand.nextInt(2);
      
      //bool 타입을 string으로 변환
      String rand_str;
      if(computerOddEven==0) {
         rand_str = "짝";
      }else {
         rand_str = "홀";
      }
      
      
      if((userSelect%2==0&&rand_str.equals("짝"))||(userSelect%2==1&&rand_str.equals("홀"))) {
         System.out.println("당신의 구슬 개수 : " + userSelect);
         System.out.println("457번이 " + rand_str + "을 맞췄습니다.");
         System.out.println("당신의 구슬이 " + userSelect + "만큼 차감됩니다.");
         userScore-=userSelect;
         computerScore+=userSelect;
      }
      else {
         System.out.println("당신의 구슬 개수 : " + userSelect);
         System.out.println("457번이 " + rand_str + "로, 틀렸습니다.");
         System.out.println("당신의 구슬이 " + userSelect + "만큼 증가됩니다.");
         userScore+=userSelect;
         computerScore-=userSelect;
      }
      
      if(userScore<0) {
         userScore = 0;
      }else if(computerScore<0) {
         computerScore = 0;
      }
      
      System.out.println("\n| 현재 구슬의 개수  | " + userNumber + "번(나) : " + userScore + "개 | 457번 : " + computerScore + "개 | ");
      start=false;
   }
   
   //사용자 수비(홀짝 맞추기)
   void defence() {
      //System.out.println("----------후공----------");
      System.out.println("----------수비----------");
      
      //컴퓨터가 랜덤으로 구슬의 개수를 정하는 코드
      int computerSelect = rand.nextInt(computerScore)+1;
      
      //사용자가
      System.out.println("457번이 구슬을 쥐었습니다.");
      System.out.print("홀짝을 선택해 주십시오 -> ");
      
      //사용자가 홀짝을 입력
      String user_oddeven = sc.next();
      
      if((computerSelect%2==0&&user_oddeven.equals("짝"))||(computerSelect%2==1&&user_oddeven.equals("홀"))) {
         System.out.println("457번의 구슬 개수 : " + computerSelect);
         System.out.println(user_oddeven + "을 맞췄습니다.");
         System.out.println("당신의 구슬이 " + computerSelect + "만큼 증가됩니다.");
         userScore+=computerSelect;
         computerScore-=computerSelect;
      }else {
         System.out.println("457번의 구슬 개수 : " + computerSelect);
         System.out.println("당신의 구슬이 " + computerSelect + "만큼 차감됩니다.");
         userScore-=computerSelect;
         computerScore+=computerSelect;
      }
      
      if(userScore<0) {
         userScore = 0;
      }else if(computerScore<0) {
         computerScore = 0;
      }
      System.out.println("\n| 현재 구슬의 개수  | " + userNumber + "번(나) : " + userScore + "개 | 457번 : " + computerScore + "개 | ");
      start=true;
   }

}