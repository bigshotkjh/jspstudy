DROP TABLE book_t;
CREATE TABLE book_t (
  book_no NUMBER             NOT NULL,
  title   VARCHAR2(100 BYTE) NOT NULL,
  author  VARCHAR2(100 BYTE),
  price   NUMBER,
  CONSTRAINT pk_book PRIMARY KEY(book_no)
);

-- 번호표 기계 : 시퀀스
DROP SEQUENCE book_seq;
CREATE SEQUENCE book_seq;

-- 번호표 뽑기 : nextval
-- SELECT book_seq.nextval FROM dual;
-- SELECT book_seq.nextval FROM dual;
-- SELECT book_seq.nextval FROM dual;

-- 신규 책 추가하기
-- INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, '제목', '저자', 10);
-- COMMIT;
