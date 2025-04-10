SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE GENOTYPE & 2 = 0
    AND ((GENOTYPE & 1 > 0) OR (GENOTYPE & 4 > 0));
    
 
/* 
[ 비트 연산 ]
• 1번 형질: 1(0001)
• 2번 형질: 2(0010)
• 3번 형질: 4(0100)
• 4번 형질: 8(1000)

• 2번 형질을 보유하지 않은 경우: GENOTYPE & 2 = 0
• 1번 또는 3번 형질을 보유한 경우: (GENOTYPE & 1 > 0) OR (GENOTYPE & 4 > 0)
*/
