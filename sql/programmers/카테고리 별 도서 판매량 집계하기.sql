with book_info as (
    select b.book_id, b.CATEGORY, b.price, bs.sales_date, bs.sales
    from book as b
    inner join book_sales as bs
        on b.book_id = bs.book_id
    where bs.sales_date >= '2022-01-01'
        and bs.sales_date < '2022-02-01'
)

select CATEGORY as CATEGORY, sum(sales) as TOTAL_SALES
from book_info
group by CATEGORY
order by CATEGORY asc
