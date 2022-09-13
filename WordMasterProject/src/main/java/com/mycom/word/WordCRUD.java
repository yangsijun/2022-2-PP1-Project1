package com.mycom.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner sc;
    final String FILENAME = "data.dat";

    public WordCRUD(Scanner sc) {
        list = new ArrayList<>();
        this.sc = sc;
    }

    @Override
    public Object add() {
        System.out.println("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = sc.nextInt();
        String word = sc.nextLine();

        System.out.println("=> 뜻 입력 : ");
        String meaning = sc.nextLine();
        return new Word(0, level, word, meaning);
    }


    @Override
    public Object update(Object obj) {
        sc.nextLine();
        System.out.print("=> 뜻 입력: ");
        String meaning = sc.nextLine();
        ((Word) obj).setMeaning(meaning);
        return obj;
    }

    @Override
    public int delete(Object obj) {
        list.remove((Word) obj);
        return 0;
    }

    @Override
    public void selectOne(int id) {
    }

    // 1. 모든 단어 보기
    public void listAll() {
        System.out.println("--------------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");
    }

    // 2. 수준별 단어 보기
    public void listByLevel() {
        System.out.print("=> 난이도를 선택하세요. (1,2,3): ");
        int level = sc.nextInt();

        if (level < 1 || level > 3) {
            System.out.println("잘못된 난이도 선택입니다.");
            return;
        }

        System.out.println("--------------------------------");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLevel() == level) {
                System.out.print((i + 1) + " ");
                System.out.println(list.get(i).toString());
            }
        }
        System.out.println("--------------------------------");
    }

    // 3. 단어 검색
    public void searchWord() {
        System.out.print("=> 검색할 단어를 입력하세요: ");
        String word = sc.next();
        System.out.println("--------------------------------");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWord().contains(word)) {
                System.out.print((i + 1) + " ");
                System.out.println(list.get(i).toString());
            }
        }
        System.out.println("--------------------------------");
    }

    // 4. 단어 추가
    public void addWord() {
        Word word = (Word) add();
        list.add(word);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    // 5. 단어 수정
    public void updateWord() {
        System.out.print("=> 수정할 단어 검색: ");
        String keyword = sc.next();
        ArrayList<Integer> idList = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택: ");
        int id = sc.nextInt();

        Word word = (Word) update(list.get(idList.get(id - 1)));
        list.set(idList.get(id - 1), word);

        System.out.println("\n단어가 성공적으로 수정되었습니다.");
    }

    // 6. 단어 삭제
    public void deleteWord() {
        System.out.print("=> 삭제할 단어 검색: ");
        String keyword = sc.next();
        ArrayList<Integer> idList = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택: ");
        int id = sc.nextInt();

        System.out.print("=> 정말로 삭제하시겠습니까? (Y/n): ");
        String confirm = sc.next();

        if (Objects.equals(confirm, "Y") || Objects.equals(confirm, "y")) {
            delete(list.get(idList.get(id - 1)));
            System.out.println("\n단어가 성공적으로 삭제되었습니다.");
        } else {
            System.out.println("\n단어 삭제가 취소되었습니다.");
        }
    }

    // 7. 파일 저장
    public void saveFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
            for (Word word : list) {
                bw.write(word.toFileString());
                bw.newLine();
            }
            bw.close();
            System.out.println("\n모든 단어를 " + FILENAME + "에 저장하였습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일 불러오기
    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            String line;
            int cnt = 0;

            while(true) {
                line = br.readLine();
                if (line == null) break;
                String[] data = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                cnt++;
            }
            br.close();
            System.out.println("=> " + cnt + "개 단어를 불러왔습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 수정할 단어 검색 결과 리스트 반환
    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idList = new ArrayList<>();
        System.out.println("--------------------------------");
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWord().contains(keyword)) {
                System.out.print((j + 1) + " ");
                System.out.println(list.get(i).toString());
                idList.add(i);
                j++;
            }
        }
        System.out.println("--------------------------------");
        return idList;
    }

}