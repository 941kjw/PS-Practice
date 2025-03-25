-- 코드를 입력하세요

WITH REVIEW_RANK AS (
    SELECT MEMBER_ID, COUNT(*) AS REVIEW_COUNT
    FROM REST_REVIEW
    GROUP BY MEMBER_ID
)

SELECT MEMBER_NAME, REVIEW_TEXT, DATE_FORMAT(REVIEW_DATE, "%Y-%m-%d") AS REVIEW_DATE
FROM REST_REVIEW R LEFT JOIN REVIEW_RANK RR
ON R.MEMBER_ID = RR.MEMBER_ID
LEFT JOIN MEMBER_PROFILE RRR
ON R.MEMBER_ID = RRR.MEMBER_ID
WHERE RR.REVIEW_COUNT = (
    SELECT MAX(REVIEW_COUNT)
    FROM REVIEW_RANK
)
ORDER BY REVIEW_DATE, REVIEW_TEXT;