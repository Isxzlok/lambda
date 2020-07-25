-- 1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数
select t1.s_name, t2.s_score, t3.s_score from student t1
left join score t2 on t1.s_id = t2.s_id and t2.c_id = 01
left join score t3 on t1.s_id = t3.s_id and t3.c_id = 02
where t2.s_score > t3.s_score

-- 查询"01"课程比"02"课程成绩低的学生的信息及课程分数
select t1.s_name, t2.s_score 01_sorce, t3.s_score 02_score from student t1
left join score t2 on t1.s_id = t2.s_id and t2.c_id = 01
left join score t3 on t1.s_id = t3.s_id and t3.c_id = 02
where t2.s_score < t3.s_score

-- 3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
select t1.s_id, t2.s_name, round(avg(t1.s_score), 2) as score from score t1
left join student t2 on t2.s_id = t1.s_id
GROUP BY t1.s_id having score > 60


-- 4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩
-- (包括有成绩的和无成绩的)
select t1.s_id, t2.s_name, round(avg(t1.s_score), 2) as score from score t1
left join student t2 on t2.s_id = t1.s_id
GROUP BY t1.s_id having score < 60
union
select t1.s_id, t1.s_name, 0 as score from student t1
where t1.s_id not in (select DISTINCT(s_id) from score)

-- 5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩
select t1.s_id, t1.s_name, count(t2.s_id), ifnull(sum(t2.s_score), 0) from student t1
left join score t2 on t1.s_id = t2.s_id
group by t2.s_id

-- 6、查询"李"姓老师的数量
select count(*) from teacher where t_name like '李%'

-- 7、查询学过"张三"老师授课的同学的信息
select DISTINCT(t6.s_id), t6.s_name from
(
	select t3.s_id s_id, t3.c_id c_id, t4.t_id t_id from
						(select t1.s_id s_id, t2.c_id c_id from score t1
							left join course t2 on t1.c_id = t2.c_id) as t3
	left join course t4 on t3.c_id = t4.c_id
) as t5
left join student t6 on t5.s_id = t6.s_id
left join teacher t7 on t7.t_id = t5.t_id
where t7.t_name = '张三'

select t1.s_id, t1.s_name from student t1
left join score t2 on t1.s_id = t2.s_id
where t2.c_id in
(
   select t3.c_id from course t3 left join teacher t4 on t3.t_id = t4.t_id where t4.t_name = '张三'
)

-- 8、查询没学过"张三"老师授课的同学的信息
select s_id, s_name from student where s_id not in (
					select t1.s_id from student t1
					left join score t2 on t1.s_id = t2.s_id
					where t2.c_id in
					(
						 select t3.c_id from course t3 left join teacher t4 on t3.t_id = t4.t_id where t4.t_name = '张三'
					)
)

-- 9、查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息
select t1.s_id, t1.s_name from student t1
left join score t2 on t1.s_id = t2.s_id
where t2.c_id = '01' or t2.c_id = '02'
GROUP BY t1.s_id having count(*) > 1

select a.* from
    student a,score b,score c
    where a.s_id = b.s_id  and a.s_id = c.s_id and b.c_id='01' and c.c_id='02';


-- 10、查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息
select t1.s_id, t1.s_name from student t1
left join score t2 on t1.s_id = t2.s_id
where t2.c_id = '01' and t1.s_id not in (select t3.s_id from student t3 left join score t4 on t3.s_id = t4.s_id where t4.c_id = '02')

select s_id, s_name from student
where s_id in (select s_id from score where c_id = '01') and s_id not in (select s_id from score where c_id = '02')








































