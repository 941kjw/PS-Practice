-- 코드를 작성해주세요
WITH RECURSIVE Generation AS(
    SELECT ID, PARENT_ID ,1 AS Gen
    FROM ECOLI_DATA
    WHERE PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT e.ID, e.PARENT_ID, g.Gen+1 as Gen
    FROM ECOLI_DATA e
    INNER JOIN Generation g
    on e.PARENT_ID = g.ID
)

SELECT ID
FROM Generation
WHERE Gen = 3