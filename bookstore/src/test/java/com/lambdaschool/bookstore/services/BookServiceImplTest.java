package com.lambdaschool.bookstore.services;

import com.lambdaschool.bookstore.BookstoreApplication;
import com.lambdaschool.bookstore.exceptions.ResourceNotFoundException;
import com.lambdaschool.bookstore.models.Author;
import com.lambdaschool.bookstore.models.Book;
import com.lambdaschool.bookstore.models.Section;
import com.lambdaschool.bookstore.models.Wrote;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
public class BookServiceImplTest
{

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);

//        List<Book> myList = bookService.findAll();
//
//        for (Book b : myList)
//        {
//            System.out.println(b.getBookid() + " " + b.getTitle());
//        }
//
//        List<Author> authorList = authorService.findAll();
//
//        for (Author a : authorList)
//        {
//            System.out.println(a.getAuthorid());
//        }
    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void a_findAll()
    {
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void b_findBookById()
    {
        assertEquals("The Da Vinci Code", bookService.findBookById(28).getTitle());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void c_notFindBookById()
    {
        assertEquals("The Da Vinci Code", bookService.findBookById(31).getTitle());
    }

    @Test
    public void e_delete()
    {
        bookService.delete(28);
        assertEquals(5, bookService.findAll().size());
    }

    @Test
    public void d_save()
    {
        Author a1 = new Author("John", "Mitchell");
        a1.setAuthorid(15);

        Section s1 = new Section("Technology");
        s1.setSectionid(21);

        Book b1 = new Book("Java Fundamentals", "9780783206752", 2020, s1);
        b1.getWrotes().add(new Wrote(a1, new Book()));

        Book saveb1 = bookService.save(b1);

        assertEquals("Java Fundamentals", saveb1.getTitle());
    }

    @Test
    public void update()
    {
    }

    @Test
    public void f_deleteAll()
    {
        bookService.deleteAll();
        assertEquals(0, bookService.findAll().size());
    }
}