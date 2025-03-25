-- 코드를 입력하세요
# select count(*)
# from car_rental_company_rental_history h right outer join car_rental_company_car c
# on h.car_id = c.car_id;

# 아예 없거나, 11월에 대여기록이 없는 차여야 함.
# 전체 차에서, 11월에 대여기록이 있는 차를 뺄까?
# 그리고 그 안에서 다시 금액 계산
#

# 11월 대여 기록이 없는 차 리스트.
select CAR_ID, p.CAR_TYPE, round((daily_fee * 30 * (100 - discount_rate) / 100),0) as FEE
from car_rental_company_car c1 inner join car_rental_company_discount_plan p
on c1.car_type = p.car_type
where p.duration_type like "30%" and (p.car_type = '세단' OR p.car_type = "SUV") and car_id not in(
select h.car_id
from car_rental_company_rental_history h join car_rental_company_car c
on h.car_id = c.car_id
where start_date <= '2022-11-30' and end_date >= '2022-11-01')
and round((daily_fee * 30 * (100 - discount_rate) / 100),0) between 500000 and 2000000
order by FEE desc, car_type,car_id desc;

# select count(*)
# from car_rental_company_car;


# select plan_id,car_type,duration_type,((100 - discount_rate) /100) as rate,discount_rate
# from car_rental_company_discount_plan
# where duration_type like "30%"