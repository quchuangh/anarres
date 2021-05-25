-- 删掉admin的权限
delete from sys_role_ability
where role_id in
( select id  from sys_role where role = 'admin');

-- 设置admin的权限
insert into sys_role_ability (role_id, ability_id, licensable)
(
 select r.id as role_id,  a.id as ability_id, 1 as licensable from sys_role r, sys_ability a where r.role='admin'
)
