import java.io.*;
import java.util.*;
import java.lang.*;

class Solution {
    static int recordsLength; // 입출차 기록 개수
    static HashMap<String, Integer> parkingTime = new HashMap<>(); // 차량별 누적 주차 시간
    static HashMap<String, String> inTimeMap = new HashMap<>();    // 차량별 입차 시간
    static TreeMap<String, Integer> indivAmount = new TreeMap<>(); // 차량별 누적 요금 - 오름차순
    static int[] answer;
    
    public int[] solution(int[] fees, String[] records) {
        recordsLength = records.length;

        // 차량별 누적 주차 시간 계산
        for (String record : records) {
            // String을 3개의 String으로 나누기
            String[] info = record.split(" ");
            String time = info[0]; // 시각(시:분)
            String carNumber = info[1]; // 차량 번호
            String content = info[2]; // 입/출 IN, OUT
            
            // 누적 주차 시간 갱신
            if (content.equals("IN")) {
                inTimeMap.put(carNumber, time);
            } else {
                // 출차일 시, 해당 차량의 입차 시간을 꺼내와서 누적 시간 갱신 후 입차 시간 삭제
                String inTime = inTimeMap.get(carNumber);
                
                // 누적 시간 = 출차 - 입차
                // 만약 이미 누적 시간이 존재한다면, 해당 누적 시간에 합산 - getOrDefault
                parkingTime.put(carNumber, 
                                parkingTime.getOrDefault(carNumber, 0) + calDiff(inTime, time));
                
                inTimeMap.remove(carNumber);
            }
        }
        
        // 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주
        // 입차 Map에 기록이 남았다면, 출차 기록이 없음을 의미
        // int lastTotalMinutes = 23 * 60 + 59;
        for (String remainCarNumber : inTimeMap.keySet()) {
            parkingTime.put(remainCarNumber, 
                                parkingTime.getOrDefault(remainCarNumber, 0) 
                            + calDiff(inTimeMap.get(remainCarNumber), "23:59"));
        }
        
        // indivAmount 갱신
        for (String carN : parkingTime.keySet()) {
            int carTime = parkingTime.get(carN);
            
            // 누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구
            if (carTime <= fees[0]) indivAmount.put(carN, fees[1]);
            else {
                // 누적 주차 시간이 기본 시간을 초과
                int sum = fees[1];
                
                // 초과 시간에 대해 단위시간마다 단위요금 청구
                carTime -= fees[0];
                int divTime = 0;
                
                // 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림
                if (carTime % fees[2] != 0) {
                    divTime = carTime / fees[2] + 1;
                } else {
                    divTime = carTime / fees[2];
                }
                
                sum += divTime * fees[3];
                
                indivAmount.put(carN, sum);
            }
        }
        
        // 차량 번호가 작은 순으로 answer에 넣기
        answer = new int[parkingTime.size()];
        
        int i = 0;
        for (int n : indivAmount.values()) {
            answer[i] = n;
            i++;
        }
        
        return answer;
    }
    
    // 누적 시간 계산 (출차 시간 - 입차 시간)
    static int calDiff(String inTime, String outTime) {
        // string을 int로 변환(분으로 변환)
        String[] inTimeStr = inTime.split(":");
        String[] outTimeStr = outTime.split(":");
        
        // System.out.println(outTime);
        // System.out.println(outTimeStr[1]);
        // System.out.println();
        
        int inTimeHour = Integer.parseInt(inTimeStr[0]);
        int inTimeMinutes = Integer.parseInt(inTimeStr[1]);
        int outTimeHour = Integer.parseInt(outTimeStr[0]);
        int outTimeMinutes = Integer.parseInt(outTimeStr[1]);
        
        int inTimeTotalMinutes = inTimeHour * 60 + inTimeMinutes;
        int outTimeTotalMinutes = outTimeHour * 60 + outTimeMinutes;
        
        return outTimeTotalMinutes - inTimeTotalMinutes;
    }
}

/***
[ 요구사항 ]
차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return

00:00부터 23:59까지의 입/출차 내역을 바탕으로 차량별 누적 주차 시간을 계산하여 요금을 일괄로 정산
- 어떤 차량이 입차된 후에 출차된 내역이 없다면, 23:59에 출차된 것으로 간주
- 누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구
- 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구
  => 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림

[ 입력 ]
int[] fees: 주차 요금
fees[0]: 기본 시간(분)	
fees[1]: 기본 요금(원)	
[2]: 단위 시간(분)	
[3]: 단위 요금(원)

String[] records: 자동차의 입/출차 내역을 나타내는 문자열 배열 
["05:34 5961 IN", 
"06:00 0000 IN", 
"06:34 0000 OUT", 
"07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]

fees
[180, 5000, 10, 600]	

result
[14600, 34400, 5000]

차량의 개수... 각 차량별 키, value로 관리해야함. => 해시맵
HashMap<String, Integer> parkingTime 누적시간

***/
