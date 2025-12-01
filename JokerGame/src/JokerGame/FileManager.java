package JokerGame;

import java.io.*;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
	private List<String[]> playerDatas;
	
	Scanner sc;
	FileReader in;
	
	private final String filePath = "TextFiles/PlayersData.txt";
	
	public FileManager() {
	    File logFile = new File(filePath); 
	    
	    if (!logFile.exists()) {
	        System.out.println("파일이 존재하지 않음");
	        // 상위 디렉토리가 없으면 생성
	        File parentDir = logFile.getParentFile();
	        if (parentDir != null && !parentDir.exists()) {
	            parentDir.mkdirs();
	        }
	        try {
	            logFile.createNewFile();
	        } catch (IOException e) {
	            System.err.println("파일 생성 중 오류 발생");
	            e.printStackTrace();
	            return;
	        }
	    } else {
	        System.out.println("파일이 이미 존재함");
	    }
	    
	    //파일이 존재 or 생성에 성공하면 읽기 시작
	    try {
	        in = new FileReader(logFile);
	        sc = new Scanner(new BufferedReader(in));
	        LoadAllPlayersData();
	    } catch (FileNotFoundException e) {
	        // 혹시 모를 예외 처리
	        System.err.println("파일을 열 수 없음");
	        e.printStackTrace();
	    }
	}
    public void LoadAllPlayersData() {
        List<String[]> playerRecords = new ArrayList<>();
        
        if (sc == null) {
            System.err.println("스캐너가 초기화되지 않음");
            return;
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            
            if (!line.trim().isEmpty()) { 
                String[] parts = line.split(","); 
                
                if (parts.length == 3) {
                    // 데이터 항목의 불필요한 공백 제거
                    for (int i = 0; i < parts.length; i++) {
                        parts[i] = parts[i].trim();
                    }
                    playerRecords.add(parts); 
                } else {
                	System.out.println("데이터 형식이 올바르지 않은 라인" + line); 
                }
            }
        }
        this.playerDatas = playerRecords;
    }
    public String[] FindPlayerData(String name) {
    	String[] newPlayerData = {name, "0", "0"};
    	if(playerDatas == null)
    		return newPlayerData;
    	
        for (String[] record : this.playerDatas) {
            if (record[0].equals(name.trim())) { 
                //찾는 이름이 있으면
                return record; // 해당 데이터 반환하고 메서드를 종료
            }
        }
        // 찾는 이름이 없으면 새 데이터 반환
        return newPlayerData;
    }
    public void SavePlayerData(String[] playerData) {
        if (playerData == null || playerData.length < 3) {
            System.err.println("저장할 데이터 형식이 올바르지 않음");
            return;
        }
        String nameToFind = playerData[0].trim();
        boolean found = false;

        for (int i = 0; i < this.playerDatas.size(); i++) {
            String[] currentRecord = this.playerDatas.get(i);
            if (currentRecord[0].equals(nameToFind)) {
                this.playerDatas.set(i, playerData);
                found = true;
                break; // 찾았으니 루프 종료
            }
        }
        // 일치하는 이름이 없으면 새로 추가
        if (!found) {
            this.playerDatas.add(playerData);
        }
        System.out.println("데이터 업데이트, 추가 완료: " + nameToFind);
        // 업데이트된 전체 데이터를 파일에 저장
        SaveAllPlayersData();
    }
    public void SaveAllPlayersData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) { 
            for (String[] record : this.playerDatas) {
                if (record.length == 3) {
                    String line = record[0] + "," + record[1] + "," + record[2];
                    writer.write(line);
                    writer.newLine(); // 다음 줄로 이동
                }
            }
            System.out.println("전체 데이터가 파일에 성공적으로 저장됨");
        } catch (IOException e) {
            System.err.println("파일 쓰기 중 오류 발생");
            e.printStackTrace();
        }
    }

	public void close() {
        if (sc != null) { //파일 입출력 뒤에 닫아주기
            sc.close();
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}