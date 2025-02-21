-- 대여: start_date와 end_date 사이 날짜는 전부 "대여중"
-- 대여 가능: "대여" 상태가 아닌 모든 자동차
-- 2022년 10월 16일에 대여 중인 자동차인 경우 '대여중' 이라고 표시
-- 대여 중이지 않은 자동차인 경우 '대여 가능'을 표시하는 컬럼(컬럼명: AVAILABILITY)을 추가
-- 자동차 ID와 AVAILABILITY 리스트를 출력
-- 반납 날짜가 2022년 10월 16일인 경우에도 '대여중'으로 표시
-- 자동차 ID를 기준으로 내림차순 정렬
-- 출력 컬럼: CAR_ID, AVAILABILITY


-- 2022년 10월 16일에 대여중인 자동차 ID 목록
-- 빌림: 반드시 2022년 10월 15일 이하여야함
-- 반납: 반드시 2022년 10월 16일 이상이어야함
WITH rentalList AS (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    -- 빌림: 반드시 2022년 10월 16일 이하여야함
    WHERE START_DATE <= '2022-10-16'
    -- 반납: 반드시 2022년 10월 16일 이상이어야함
        AND END_DATE >= '2022-10-16'
    GROUP BY CAR_ID
)

# SELECT * FROM rentalList;

# SELECT CAR_ID, COUNT(CAR_ID) FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY GROUP BY CAR_ID ORDER BY CAR_ID;

-- rentalList와 조인해서 교집합일시 "대여중", 교집합이 아닐시 "대여 가능"
SELECT e1.CAR_ID AS CAR_ID, 
    CASE
        WHEN e2.CAR_ID IS NOT NULL THEN '대여중'
        ELSE '대여 가능'
    END AS AVAILABILITY
FROM (SELECT DISTINCT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY) AS e1
LEFT OUTER JOIN rentalList AS e2
    ON e2.CAR_ID = e1.CAR_ID
ORDER BY e1.CAR_ID DESC;
