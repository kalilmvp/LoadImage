package br.image.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.image.dao.BookDAO;
import br.image.entity.Book;

/**
 * JUnit Class to test the download and upload of images.
 * 
 * @author kalilmvp
 * @date 19/10/2011
 *
 */
public class TestBook {
	
	private static final String FILE_PATH = "E:\\images\\foto1.jpg";
	private static final String FILE_PATH_SAVED = "E:\\images\\foto2.jpg";
	
	private static BookDAO bookDAO;
	
	private static Book book;
	
	@BeforeClass
	public static void runBeforeClass() {
		bookDAO = new BookDAO();
		book = new Book();
	}
	
	@AfterClass
	public static void runAfterClass() {
		bookDAO = null;
		book = new Book();
	}
	
	/**
	 * Save a {@link Book} test.
	 */
	@Test
	public void saveBook() {
		Path path = Paths.get(FILE_PATH);
		if (Files.exists(path)) {
			try {
				byte [] bytes = Files.readAllBytes(path);
				
				book.setName("TESTING");
				book.setImage(bytes);
				
				bookDAO.save(book);
				
				Assert.assertNotNull(book.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("WRONG PATH");
		}
	}
	
	/**
	 * Get a book test.
	 * Testing if the same image that was stored recently can be uploaded to a new one. 
	 */
	@Test
	public void getBook() {
		Book bookConsulted = bookDAO.load(book.getId());
		
		Assert.assertNotNull(book.getImage());
		
		try {
			Path path = Paths.get(FILE_PATH_SAVED);
			
			Files.write(path, bookConsulted.getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}