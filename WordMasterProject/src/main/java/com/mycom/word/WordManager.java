package com.mycom.word;
import java.util.Scanner;

public class WordManager {
    Scanner sc = new Scanner(System.in, "EUC-KR");
    WordCRUD wordCRUD;

    WordManager() {
        wordCRUD = new WordCRUD(sc);
    }
    public int selectMenu() {
        System.out.print(
                """
                *** 영단어 마스터 ***
                ********************
                1. 모든 단어 보기
                2. 수준별 단어 보기
                3. 단어 검색
                4. 단어 추가
                5. 단어 수정
                6. 단어 삭제
                7. 파일 저장
                0. 나가기
                ********************
                => 원하는 메뉴를 입력해주세요:\s"""
        );

        return sc.nextInt();
    }
    public void start() {

        wordCRUD.loadFile();
        System.out.println();

        while (true) {
            int menu = selectMenu();

            if (menu == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            System.out.println();
            switch (menu) {
                case 1 -> wordCRUD.listAll();
                case 2 -> wordCRUD.listByLevel();
                case 3 -> wordCRUD.searchWord();
                case 4 -> wordCRUD.addWord();
                case 5 -> wordCRUD.updateWord();
                case 6 -> wordCRUD.deleteWord();
                case 7 -> wordCRUD.saveFile();
                default -> System.out.println("잘못된 메뉴 선택입니다. 다시 선택해주세요.");
            }
            System.out.println();
        }
    }
}
