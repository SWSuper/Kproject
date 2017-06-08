drop table board purge;
drop sequence boards_seq;
select * from board;
select * from seq;

--TITLE, WRITER, REGDATE, COUNT, CONTENT, REGIP 
CREATE TABLE board(

	board_seq NUMBER,
	TITLE VARCHAR2(20),
	WRITER VARCHAR2(20),
	REGDATE VARCHAR2(20),
	COUNT NUMBER,
	CONTENT VARCHAR2(2000),
	REGIP NUMBER,
	PRIMARY KEY(board_seq)
);

create sequence boards_seq
start with 1
increment by 1
nocache;