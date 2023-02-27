package com.kh.gym;

import com.kh.gym.dao.MemberInfoDAO;
import com.kh.gym.method.Member;

import java.util.Scanner;

public class GymMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Member member = new Member();
        MemberInfoDAO memberInfoDAO = new MemberInfoDAO();
        while(true){
            System.out.println("===== GYM MANAGEMENT PROGRAM =====");
            System.out.println("메뉴를 선택하세요. ");
            System.out.println("[1] 회원정보, [2] 매출정보, [3] 라커관리, [4] 회원 출석조회, [5] 상품, [6] 종료");
            int sel = sc.nextInt();
            switch(sel){
                case 1: member.management();
                        break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: System.out.println("헬스장 관리 프로그램을 종료합니다."); return;
            }
        }
    }
}
