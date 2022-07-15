--EMPLOYEES 테이블에서 HIREDATE가 2003~2005년까지인 사원의 정보와 부서명 검색
SELECT * FROM EMPLOYEES;
SELECT * FROM DEPARTMENTS;

SELECT E.*, D.DEPARTMENT_NAME 
FROM EMPLOYEES E JOIN DEPARTMENTS D
ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND HIRE_DATE BETWEEN TO_DATE('2003-01', 'YYYY-MM') AND TO_DATE('2005-12', 'YYYY-MM');

--JOB_TITLE 중 'Manager'라는 문자열이 포함된 직업들의 평균 연봉을 JOB_TITLE별로 검색
SELECT * FROM EMPLOYEES;
SELECT * FROM JOBS;

SELECT JOB_TITLE, AVG(E.SALARY) 
FROM JOBS J JOIN EMPLOYEES E
ON J.JOB_ID = E.JOB_ID AND JOB_TITLE LIKE '%Manager%'
GROUP BY JOB_TITLE;

--EMP 테이블에서 ENAME에 L이 있는 사원들의 DNAME과 LOC 검색
SELECT * FROM EMP;
SELECT * FROM DEPT;

SELECT ENAME, DNAME, LOC 
FROM DEPT D JOIN EMP E
ON D.DEPTNO = E.DEPTNO
AND ENAME LIKE '%L%';

--축구 선수들 중에서 각 팀 별로 키가 가장 큰 선수들 검색
--1. JOIN 사용
SELECT TEAM_ID, MAX(HEIGHT) FROM PLAYER
GROUP BY TEAM_ID
ORDER BY 1;

SELECT * FROM PLAYER;
SELECT * FROM PLAYER
WHERE TEAM_ID = 'K01';

SELECT P1.TEAM_ID, P1.M MAX_HEIGHT, PLAYER_NAME, E_PLAYER_NAME, HEIGHT
FROM
(
	SELECT TEAM_ID, MAX(HEIGHT) M FROM PLAYER
	GROUP BY TEAM_ID
	ORDER BY 1
) P1
JOIN PLAYER P2
ON P1.TEAM_ID = P2.TEAM_ID AND P1.M = P2.HEIGHT
ORDER BY P1.TEAM_ID;

--2. 서브쿼리 사용(WHERE절) - JOIN 안쓰기
SELECT TEAM_ID, PLAYER_NAME, E_PLAYER_NAME, HEIGHT FROM PLAYER
WHERE (TEAM_ID, HEIGHT) IN (SELECT TEAM_ID, MAX(HEIGHT) FROM PLAYER GROUP BY TEAM_ID)
ORDER BY TEAM_ID;

-- 외부 조인(OUTER JOIN)
-- OUTER JOIN을 기준으로 
--왼쪽 테이블의 전체 정보를 보고 싶다면 LEFT를 사용하고
--오른쪽 테이블의 전체 정보를 보고 싶다면 RIGHT를 사용한다.

SELECT * FROM TEAM;
SELECT * FROM STADIUM;

SELECT * FROM TEAM T RIGHT OUTER JOIN STADIUM S
ON T.TEAM_ID = S.HOMETEAM_ID;

SELECT * FROM STADIUM S LEFT OUTER JOIN TEAM T
ON T.TEAM_ID = S.HOMETEAM_ID;

-- 셀프 조인(SELF JOIN)
-- 똑같은 테이블 끼리 JOIN한다.
-- 사원의 이름과 매니저의 이름을 출력한다.
SELECT * FROM EMP;

SELECT E1.ENAME 사원이름, E2.ENAME 매니저이름
FROM EMP E1 JOIN EMP E2
ON E1.MGR = E2.EMPNO;
--------------------------------------------------------------------------------------------------------------
--[브론즈]
--PLAYER 테이블에서 키가 NULL인 선수들은 키를 170으로 변경하여 평균 구하기(NULL 포함)
SELECT AVG(NVL(HEIGHT, 170)) "평균 키" FROM PLAYER;

--[실버]
--PLAYER 테이블에서 팀 별 최대 몸무게
SELECT TEAM_ID, MAX(WEIGHT) || 'kg' "최대 몸무게" 
FROM PLAYER
GROUP BY TEAM_ID
ORDER BY 1;

--[골드]
--AVG 함수를 쓰지 않고 PLAYER 테이블에서 선수들의 평균 키 구하기(NULL 포함)
SELECT ROUND(SUM(HEIGHT) / COUNT(HEIGHT), 2) || 'cm' "평균 키" FROM PLAYER;

--[플래티넘]
--DEPT 테이블의 LOC별 평균 급여를 반올림한 값과 각 LOC별 SAL 총 합을 조회
SELECT * FROM EMP
WHERE DEPTNO = 40;

SELECT * FROM DEPT;

SELECT LOC, ROUND(AVG(SAL),2), SUM(SAL) FROM
DEPT D JOIN EMP E
ON D.DEPTNO = E.DEPTNO
GROUP BY LOC;

--[다이아]
--PLAYER 테이블에서 팀별 최대 몸무게인 선수 검색
SELECT P.TEAM_ID, REGION_NAME, PLAYER_NAME, WEIGHT 
FROM TEAM T JOIN PLAYER P
ON T.TEAM_ID = P.TEAM_ID
AND (P.TEAM_ID, P.WEIGHT) IN (SELECT TEAM_ID, MAX(WEIGHT) FROM PLAYER GROUP BY TEAM_ID);

--[마스터]
--EMP 테이블에서 HIREDATE가 FORD의 입사년도와 같은 사원 전체 정보 조회


--FORD의 입사년도 중 년도부분만 SUBSTR을 사용하여 잘라낸다.
--DATE타입을 SUBSTR에 넣으면 자동으로 문자열로 바뀌고 년도는 두 자리로 측정된다.
--DATE	  SUBSTR(DATE)
--1990 -> 90
--DATE타입과 VARCHAR2타입을 비교할 때 LIKE로 비교하면 TO_CHAR를 사용할 필요가 없다.
SELECT ENAME, HIREDATE 
FROM EMP
WHERE TO_CHAR(HIREDATE, 'YY') = (SELECT SUBSTR(HIREDATE, 1, 2) 입사년 FROM EMP WHERE ENAME = 'FORD');

SELECT * FROM EMP
WHERE SUBSTR(HIREDATE, 1) LIKE
(
	SELECT SUBSTR(HIREDATE, 1, 2) FROM EMP WHERE ENAME = 'FORD'
) || '%';

SELECT * FROM EMP
WHERE HIREDATE LIKE
(
	SELECT SUBSTR(HIREDATE, 1, 2) FROM EMP WHERE ENAME = 'FORD'
) || '%';

SELECT * FROM EMP
WHERE HIREDATE LIKE '80%';















