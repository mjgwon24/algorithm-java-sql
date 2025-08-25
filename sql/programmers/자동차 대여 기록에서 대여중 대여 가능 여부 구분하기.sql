-- desc car_rental_company_rental_history

-- 2022년 10월 16일에 대여 중인 자동차
WITH AVAILABLE_CAR_TABLE AS (
    SELECT CAR_ID
    FROM car_rental_company_rental_history
    WHERE START_DATE <= '2022-10-16' AND END_DATE >= '2022-10-16'
    GROUP BY CAR_ID
)

-- ac.CAR_ID가 존재할 경우 '대여중', 존재하지 않을 경우 '대여 가능'
SELECT 
    cr.CAR_ID,
    CASE
        WHEN ac.CAR_ID IS NOT NULL THEN '대여중'
        ELSE '대여 가능'
    END AS 'AVAILABILITY'
FROM car_rental_company_rental_history as cr
LEFT OUTER JOIN AVAILABLE_CAR_TABLE as ac
    ON cr.CAR_ID = ac.CAR_ID
GROUP BY cr.CAR_ID
ORDER BY cr.CAR_ID DESC

/*
car_rental_company_rental_history 자동차 대여 기록 정보
---
[1] 2022년 10월 16일에 대여 중인 자동차인 경우 '대여중'이라고 표시
-> start_date 2022-10-16 이하 and end_date 2022-10-17 이상
[2] 대여 중이지 않은 경우 '대여 가능' 표시
-> [1]의 car_id에 포함되지 않은 자동차
[3] select car_id, availability
[4] order by 자동차 ID DESC
*/
