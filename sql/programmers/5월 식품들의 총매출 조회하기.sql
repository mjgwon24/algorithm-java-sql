-- 총매출 서브쿼리
# select fp.PRODUCT_ID, fp.PRICE * sum(fo.AMOUNT) as TOTAL_SUM
# from FOOD_PRODUCT as fp
# join FOOD_ORDER as fo on fp.PRODUCT_ID = fo.PRODUCT_ID
# group by PRODUCT_ID

SELECT fp.PRODUCT_ID, fp.PRODUCT_NAME, fo.TOTAL_SUM
from FOOD_PRODUCT as fp
join (
    select fp.PRODUCT_ID as PRODUCT_ID, fp.PRICE * sum(fo.AMOUNT) as TOTAL_SUM
    from FOOD_PRODUCT as fp
    join FOOD_ORDER as fo on fp.PRODUCT_ID = fo.PRODUCT_ID
    where fo.PRODUCE_DATE >= '2022-05-01' and fo.PRODUCE_DATE < '2022-06-01'
    group by PRODUCT_ID
) as fo on fp.PRODUCT_ID = fo.PRODUCT_ID
order by fo.TOTAL_SUM DESC, fp.PRODUCT_ID ASC
