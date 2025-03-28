-- 코드를 입력하세요
SELECT YEAR(SALES_DATE) AS YEAR, MONTH(SALES_DATE) AS MONTH, GENDER, COUNT(DISTINCT I.USER_ID) AS USERS
FROM USER_INFO I JOIN ONLINE_SALE S 
ON I.USER_ID = S.USER_ID
WHERE GENDER IS NOT NULL
GROUP BY YEAR,MONTH,GENDER
ORDER BY YEAR,MONTH,GENDER;