package com.kh.gym.method;

import com.kh.gym.dao.EntranceDAO;
import com.kh.gym.vo.LockerVO;

import java.util.Scanner;

public class Management {

    Scanner sc = new Scanner(System.in);
    Member member = new Member();
    Product product = new Product();
    Sales sales = new Sales();
    Entrance entrance = new Entrance();
    Locker locker = new Locker();
    Pt pt = new Pt();




    public void adminMode() {
        while (true) {
            System.out.println("===== GYM MANAGEMENT PROGRAM =====");
            System.out.println("메뉴를 선택하세요. ");
            System.out.println("[1] 회원정보, [2] 매출정보, [3] 라커관리, [4] 회원 출석 조회, [5] 상품관리, [6] PT회원 관리 [7] 종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    member.management();
                    break;
                case 2:
                    sales.sales();
                    break;
                case 3:
                    locker.locker();
                    break;
                case 4:
                    entrance.entrance();
                    break;
                case 5:
                    product.product();
                    break;
                case 6:
                    pt.ptManagement();
                    break;
                case 7:
                    System.out.println("관리자 모드를 종료합니다.");
                    return;
            }
        }
    }

    public void gymProgram() {
        while(true){
            System.out.println("===== GYM PROGRAM =====");
            System.out.println("[1] 회원 입장, [2] 관리자 모드 진입, [3] 프로그램 종료");
            int sel = sc.nextInt();
            switch(sel){
                case 1: entrance.entMemProgram();
                        break;
                case 2: if(getAdmin()) adminMode();
                        break;
                case 3: System.out.println("프로그램을 종료합니다.");
                        return;
            }
        }
    }
    public boolean getAdmin(){
            System.out.println("비밀번호 입력 : ");
            String pwd = sc.next();
            if(pwd.equals("getadmin")) return true;
            else return false;
    }
}
