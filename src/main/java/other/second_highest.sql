-- SQL join - get employee name and their department name
-- Table information:
-- Employee table: id, name, age, department_id
-- Department table: id, department_name
--
-- Find second-oldest employee in department using SQL


-- *** use window function rank if getting from each department
with ranked as (
    select
        e.name, e.age, e.department_id, d.department_name,
        dense_rank() over (
            partition by e.department_id
            order by e.age desc
        ) as rnk
    from employee e
    join department d on e.department_id = d.id
    )
select name, age, department_name from ranked
where rnk = 2;

--  ** if getting from all departments
select e.name, e.age, e.department_id, d.department_name
from employee e
join department d on d.id = e.department_id
where e.age = (
    select distinct age
    from employee
    order by age desc
    limit 1 offset 1
);


-- Can aggregate functions help find the 2nd oldest employee?
--
-- yes, if used in a subquery

-- if getting from each department
select department_id, max(age) as second_oldest_age from employee
where (department_id, age) not in (
    select department_id, max(age)
    from employee
    group by department_id
)
group by department_id;

-- if getting from all departments
select max(age) as second_highest_age from employee
where age < (
    select max(age) from employee
);


-- SQL select - find the employee with second-highest salary
-- Table information: EmployeeId, Name, Salary
--
-- Return the 2nd highest salary: Distinct + order by desc + limit + offset

select distinct salary from employee
order by salary desc
limit 1 offset 1;
