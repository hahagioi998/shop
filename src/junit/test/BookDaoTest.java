package junit.test;

import org.junit.Test;

import cn.edu.zhku.jsj.dao.impl.BookDaoImpl;
import cn.edu.zhku.jsj.daomain.Book;

public class BookDaoTest {
	@Test
	public void addTest(){
		Book book = new Book();
		book.setType("书籍");
		book.setAuthor("wowo");
		book.setImages("c://user/1.jpg");
		book.setBookname("java web");
		book.setDescription("这是一本javaweb书");
		book.setISBN("1231231231");
		book.setPress("清华大学出版社");
		book.setPrice(34);
		book.setVersion("用光盘；没光盘");
		book.setStore_id(1);  // 该外键 必须先要  存在 才能插入数据  不然不给插入 因为外键对应信息不存在
		book.setTotalnum(20);
		BookDaoImpl bookdao = new BookDaoImpl();
		int num = bookdao.add(book);
		if(num!=0){
			System.out.println("插入成功");
		}
	}
	
	@Test
	public void deleteTest(){
		BookDaoImpl bookdao = new BookDaoImpl();
		bookdao.delete(2);
	}
}
