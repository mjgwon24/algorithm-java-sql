WITH GROUP_NAME AS (
    SELECT NAME, COUNT(*) AS COUNT
    FROM ANIMAL_INS
    WHERE NAME IS NOT NULL
    GROUP BY NAME
)

select *
from GROUP_NAME
where COUNT >= 2
order by name

/*
이름 없는 동물은 집계에서 제외
select 해당 이름이 쓰인 횟수
where 동물 이름 중 두 번 이상 쓰인 이름
order by 이름순 asc
*/
