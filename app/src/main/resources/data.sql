delete from taco_order_tacos;
delete from taco_ingredients;
delete from taco;
delete from taco_order;
delete from ingredient;

insert into ingredient (id, name, type)
                values ('FLTO', '面粉玉米饼', 'WRAP');
insert into ingredient (id, name, type)
                values ('COTO', '玉米饼', 'WRAP');
insert into ingredient (id, name, type)
                values ('GRBF', '碎牛肉', 'PROTEIN');
insert into ingredient (id, name, type)
                values ('CARN', '猪肉丝', 'PROTEIN');
insert into ingredient (id, name, type)
                values ('TMTO', '土豆丁', 'VEGGIES');
insert into ingredient (id, name, type)
                values ('LETC', '莴苣', 'VEGGIES');
insert into ingredient (id, name, type)
                values ('CHED', '切达干酪', 'CHEESE');
insert into ingredient (id, name, type)
                values ('JACK', '蒙特雷杰克', 'CHEESE');
insert into ingredient (id, name, type)
                values ('SLSA', '萨尔萨辣酱', 'SAUCE');
insert into ingredient (id, name, type)
                values ('SRCR', '酸奶油', 'SAUCE');

