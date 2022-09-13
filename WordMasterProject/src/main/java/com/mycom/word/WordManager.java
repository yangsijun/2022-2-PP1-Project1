package com.mycom.word;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class WordManager {
    Scanner sc = new Scanner(System.in, "EUC-KR");
    WordCRUD wordCRUD;

    WordManager() {
        wordCRUD = new WordCRUD(sc);
    }
    public int selectMenu() {
        System.out.print(
                "*** 영단어 마스터 ***\n"
                + "********************\n"
                + "1. 모든 단어 보기\n"
                + "2. 수준별 단어 보기\n"
                + "3. 단어 검색\n"
                + "4. 단어 추가\n"
                + "5. 단어 수정\n"
                + "6. 단어 삭제\n"
                + "7. 파일 저장\n"
                + "0. 나가기\n"
        );

        return sc.nextInt();
    }
    public void start() {

        wordCRUD.loadFile();
        while (true) {
            int menu = selectMenu();

            if (menu == 0) {
                System.out.println("프로그램 종료! 다음에 만나요~");
                break;
            }

            switch(menu) {
                case 1:
                    wordCRUD.listAll();
                    break;
                case 2:
                    wordCRUD.listByLevel();
                    break;
                case 3:
                    wordCRUD.searchWord();
                    break;
                case 4:
                    wordCRUD.addWord();
                    break;
                case 5:
                    wordCRUD.updateWord();
                    break;
                case 6:
                    wordCRUD.deleteWord();
                    break;
                case 7:
                    wordCRUD.saveFile();
                    break;
                default:
                    System.out.println("잘못된 메뉴 선택입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }
}
