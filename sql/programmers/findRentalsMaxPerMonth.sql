-- 총 대여횟수 5회 이상인 자동차 목록 (2022년 8월~10월 기준)
WITH limitedCount AS (
  SELECT CAR_ID
  FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
  WHERE START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
  GROUP BY CAR_ID
  HAVING COUNT(CAR_ID) >= 5
)

-- 조건1. 2022년 8월 ~ 2022년 10월, MONTH로 그룹화
-- 조건2. 총 대여횟수 5회 이상인 자동차 정보 - limitedCount 테이블에 없는 id는 나오지않게 하기
-- 월별 자동차 ID 별 총 대여 횟수 (RECORDS)
-- 월을 기준으로 오름차순 정렬하고, 월이 같다면 자동차 ID를 기준으로 내림차순 정렬
-- 출력: MONTH, CAR_ID, RECORDS
SELECT MONTH(c.START_DATE) AS MONTH, c.CAR_ID AS CAR_ID, COUNT(c.CAR_ID) AS RECORDS
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY c
JOIN limitedCount lc
  ON c.CAR_ID = lc.CAR_ID
WHERE c.START_DATE BETWEEN '2022-08-01' AND '2022-10-31'
GROUP BY MONTH(c.START_DATE), c.CAR_ID
ORDER BY MONTH(c.START_DATE), c.CAR_ID DESC;


-- from -> where(join) -> group by -> having -> select -> order by -> limit
