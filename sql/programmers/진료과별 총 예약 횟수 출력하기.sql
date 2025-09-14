SELECT MCDP_CD AS '진료과 코드', COUNT(APNT_NO) AS '5월예약건수'
FROM APPOINTMENT
WHERE APNT_YMD >= '2022-05-01' AND APNT_YMD < '2022-06-01'
GROUP BY MCDP_CD
ORDER BY COUNT(APNT_NO) ASC, MCDP_CD ASC

/*
select 진료과 코드, count(환자번호) 환자 수, 
where 2022년 5월에 예약
group by 진료과 코드
order by 환자 수 오름차순, 진료과 코드 오름차순
*/
