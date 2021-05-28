set foreign_key_checks = 0;

truncate table blog_post;
truncate  table author;
truncate table comment;
truncate table author_posts;

insert into blog_post(id, title, content)
values(41, 'title post 1', 'post content 1'),
      (42, 'title post 2', 'post content 2'),
      (43, 'title post 3', 'post content 3'),
      (44, 'title post 4', 'post content 4'),
      (45, 'title post 5', 'post content 5');

set foreign_key_checks = 1;
