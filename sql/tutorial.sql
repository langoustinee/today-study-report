-- 테이블 모든 데이터 조회 쿼리
select * from tCity;
desc tCity;

SELECT * FROM tStaff;
desc tStaff;

select * from DEPT;

select * from EMP;

select * from SALGRADE;

select * from usertbl;

select * from buytbl;

-- 테이블 특정 컬럼 조회 쿼리
select name as n, popu as p from tCity;

-- 개수
select count(*) as allCnt from tCity;


-- 계산식
select name, popu*10000 as "인구(명)" from tCity;
select 360*24 as yearToHour;

-- concat 
-- 2개 이상의 컬럼이나 연산식을 하나로 합쳐서 출력하기 위함.
-- MyBatis와 같은 SQL Mapper Freamwork에서 like를 사용하기 위해서 알아둬야 함.
select CONCAT(name, popu) from tCity;
select ename, job, CONCAT(ename, "-", job) from EMP;

-- DISTINCT
-- select 절의 맨 앞에 한번만 작성 가능
-- 컬럼의 중복 제거하는 역할을 수행함.
select DISTINCT region from tCity;
select DISTINCT region, name from tCity order by region;

-- order by절
-- 조회된 데이터를 정렬하기 위한 절
-- tStaff 직원 목록을 월급이 적은 사람부터 순서대로 출력하되 월급이 같다면 성취도가 높은 사람을 먼저 조회
select * from tStaff order by salary, score desc;
-- tCity 데이터를 region 별로 정렬하고 동일 값이 있다면 name의 내림차순으로 정렬하고 region, name, area, popu 컬럼을 조회
select region, name, area, popu from tCity order by region, name desc;

-- where절
-- 테이블의 데이터를 행 단위로 분할하기 위한 조건을 설정하는 절
-- select, update, delete 구문과 함께 사용함.
-- 다양한 비교연산자 사용 가능 =, >, <, >=, <=, <>, !=, ^= ...
-- tCity 테이블에서 metro가 y인 모든 데이터 조회
-- 조건절에 y가 아닌 Y를 작성했는데도 조회결과가 같음, (mariaDB의 경우 대소문자를 구분하지 않는다.)
select * from tCity where metro='Y';
-- BINARY 함수를 통해 대소문자를 구분한다.
select * from tCity where BINARY(metro) = 'Y';
-- tCity 테이블에서 name이 서울인 모든 데이터 조회
select * from tCity where name='서울';
-- tCity 테이블에서 popu의 값이 100 이상인 데이터의 모든 컬럼을 조회
-- 크다 작다 크거나 같다 작거나 같다 등의 조건은 반드시 경계값과 경계값 양쪽의 데이터를 테스트하자.
select * from tCity where popu > 100;

-- NULL 비교
-- 아직 알려지지 않은 값으로 표현되며, 일반 연산자로 비교 불가
-- is null과 is not null로 비교해야한다.
-- 데이터베이스에서 null을 저장하는 방법은 공간에 null을 대입하는 개념이 아니라 
-- null을 저장할 수 있는 컬럼에는 데이터를 저장할 수 있는 공간에 하나의 공간을 추가해서 그 공간에 null 여부를 표시하기 때문이다.
select * from tStaff where score is null;
select * from tStaff where score is not null;

-- 논리연산자 AND, OR 제공
-- AND는 두개의 조건이 충족하는 경우에만 조회, 앞의 조건이 일치하지 않으면 뒤 조건 확인하지 않음.
-- OR는 두개의 조건 중 하나라도 충족하는 경우에만 조회, 앞의 조건이 일치하면 뒤의 조건을 확인하지 않음.
-- 우선순위는 AND가 OR보다 높다.
-- tCity 테이블에서 popu가 100만 이상이고, area가 700 이상인 데이터의 모든 컬럼을 조회
select * from tCity where popu>100 and area >= 700;


-- LIKE
-- 부분 일치하는 데이터를 조회
-- 와일드 카드 문자: _(하나의 문자와 매칭), %(글자 수 상관없음), [](문자를 나열하면 문자중 하나와 일치), [^](문자를 나열하면 문자에 포함되지 않는지)
-- 와일드 카드 문자를 검색하고자 할 경우 escape를 활용해야 한다.
select * from tCity where name like '%천%';
select * from tCity where name like '%천';
-- EMP 테이블에서 ename에 S가 포함된 데이터를 조회
select * from EMP where ename like '%S%';
-- EMP 테이블에서 ename이 N으로 끝나는 6자리 이름을 가진 데이터 조회
select * from EMP where ename like '_____N';
-- ex) SALE에 30%가 포함된 데이터를 조회, escape '#', # 뒤에 문자는 있는 그대로 해석하도록 지시
-- where sale like '30#%' ESCAPE '#'

-- BETWEEN ~ AND
-- BETWEEN A AND B 형태로 작성하면 A부터 B까지의 데이터 조회
-- 숫자, 날짜, 문자열 모두 사용가능
-- 단순 AND 구문으로도 가능함.
select * from tCity where popu BETWEEN 50 AND 100;
select * from tCity where popu >= 50 AND popu <= 100;
-- 문자 크기 비교는 맨 앞글자부터 순서대로 하나씩 비교함.
select * from tStaff where name BETWEEN '가' AND '사';
-- tCity에서 name이 ㅊ으로 시작하는 데이터 조회
-- select * from tCity where name BETWEEN '차' AND '카';
select * from tCity where name >= '차' and name < '카';
-- ㄱ으로 시작하는 데이터 조회
select * from tStaff where name >= '가' and name < '나';
-- 2015년 1월 1일부터 2018년 12월 31일 사이 데이터 조회
select * from tStaff where joindate BETWEEN '20150101' AND '20181231';

-- IN 연산자
-- ()에 값을 나열하면 나열된 값에 포함될 경우 조회
select * from tCity where region IN('경상', '전라');


-- LIMIT
-- 행의 개수를 제한할 때 활용 -> TOP N
-- LIMIT [건너뛸 행의 개수], 조회할 개수
-- 최근에는 LIMIT 개수 OFFSET 건너뛸 개수로 사용한다.
-- order by 절과 같이 사용되는 경우가 많다.
-- popu 큰 순서로 1등~4등 데이터 조회
select * from tCity order by popu desc limit 4;
-- 5등~6등 조회
select * from tCity order by popu desc limit 4,2;
select * from tCity order by popu desc limit 2 OFFSET 4;
