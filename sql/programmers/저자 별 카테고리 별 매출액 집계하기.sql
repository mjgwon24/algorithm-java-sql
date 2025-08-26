-- 2022년 1월 판매 도서 판매량
WITH 2022_SALES AS (
    SELECT BOOK_ID, SUM(SALES) AS SALES_SUM
    FROM BOOK_SALES
    WHERE SALES_DATE >= '2022-01-01' AND SALES_DATE < '2022-02-01'
    GROUP BY BOOK_ID
)

SELECT bk.AUTHOR_ID, ath.AUTHOR_NAME, bk.CATEGORY, SUM(sl.SALES_SUM * bk.PRICE) AS TOTAL_SALES # 매출액(판매량 * 판매가)
FROM BOOK AS bk
JOIN 2022_SALES AS sl
    ON bk.BOOK_ID = sl.BOOK_ID
JOIN AUTHOR AS ath
    ON bk.AUTHOR_ID = ath.AUTHOR_ID
group by bk.AUTHOR_ID, bk.CATEGORY
order by AUTHOR_ID ASC, CATEGORY DESC

/*
author 저자 정보
book 판매중인 도서 정보
book_sales 각 도서의 날짜 별 판매량 정보
---
select AUTHOR_ID, AUTHOR_NAME, CATEGORY, TOTAL_SALES 매출액(판매량 * 판매가)

v 2022년 1월의 도서 판매 데이터 기준 -> 출판 날짜는 상관없음.
v where SALES_DATE >= '2022-01-01' AND SALES_DATE < '2022-02-01'

group by 저자 별(AUTHOR_ID), 카테고리 별(CATEGORY)
order by AUTHOR_ID ASC, CATEGORY DESC
*/
